package com.example.dnsService

import com.example.dnsService.metrics.ConsumerMetricName
import com.example.dnsService.model.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.micrometer.core.instrument.Metrics
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.naming.directory.Attributes
import javax.naming.directory.InitialDirContext

@RestController
class LookupController {

    private val mapper = jacksonObjectMapper()
    private val callCounter = Metrics.counter(ConsumerMetricName.MESSAGE_RECEIVED)
    private val successCounter = Metrics.counter(ConsumerMetricName.PROCESSING_SUCCESS)
    private val failureCounter = Metrics.counter(ConsumerMetricName.PROCESSING_FAILURE)

    @PostMapping()
    @ResponseBody
    fun getDnsRecords(@RequestBody request: Request): ResponseEntity<String> {
        callCounter.increment()
        val allEnvs = System.getenv()
        val context = InitialDirContext(allEnvs.toProperties())
        var requestRecordTypes = mutableListOf<String>()
        request.recordTypes.forEach{requestRecordTypes.add(it.name)}
        val attributes = try {
            context.getAttributes(request.lookup, requestRecordTypes.toTypedArray())
        } catch (e: Exception) {
            failureCounter.increment()
            return ResponseEntity<String>(mapper.writeValueAsString(ErrorResponse(e.message)), HttpStatus.BAD_REQUEST)
        }

        val records = parseRecords(attributes, request)
        successCounter.increment()
        return ResponseEntity<String>(mapper.writeValueAsString(Response(records)), HttpStatus.OK)
    }

    private fun parseRecords(
        attributes: Attributes,
        request: Request
    ): MutableList<Record> {
        val attributeRecords = attributes.all
        val records = mutableListOf<Record>()
        while (attributeRecords.hasMore()) {
            val attributeRecord = attributeRecords.next()
            val id = attributeRecord.id
            val values = mutableListOf<String>()
            val attributeValues = attributeRecord.all
            while (attributeValues.hasMore()) {
                values.add(attributeValues.next().toString())
            }
            val domainRecord = DomainRecord(request.lookup, values, "0".toBigInteger()) //don't see ttl in data returned so set it to '0' for now
            val record = Record(RecordType.valueOf(id), domainRecord)
            records.add(record)
        }
        return records
    }
}