package com.example.dnsService.metrics

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Tags
import io.micrometer.core.instrument.Timer
import org.slf4j.LoggerFactory
import java.time.Duration
import java.time.Instant

class MicrometerConsumerMetrics(
    private val meterRegistry: MeterRegistry,
    private val metricsPrefix: String
) : ConsumerMetrics {

    companion object {
        private val logger = LoggerFactory.getLogger(MicrometerConsumerMetrics::class.java)
    }

    override fun sendMessageReceivedMetric() {
        try {
            meterRegistry.counter("${metricsPrefix}.${ConsumerMetricName.MESSAGE_RECEIVED}").increment()
        } catch (t: Throwable) {
            logger.warn(
                "${metricsPrefix} received {} error while sending received metric {}",
                t.javaClass.name,
                t.message
            )
        }
    }

    override fun sendProcessingTimeMetric(start: Instant, stop: Instant, processorTagValue: String) {
        try {
            Timer.builder("${metricsPrefix}.${ConsumerMetricName.PROCESSING_TIME}")
                .description("how long message processing took")
                .tags(processorTagValue)
                .register(meterRegistry)
                .record(Duration.between(start, stop))
        } catch (t: Throwable) {
            logger.warn(
                "${metricsPrefix} received {} error while sending processing time metric {}",
                t.javaClass.name,
                t.message
            )
        }
    }

    override fun sendFailureMetric() {
        try {
            meterRegistry.counter("${metricsPrefix}.${ConsumerMetricName.PROCESSING_FAILURE}").increment()
        } catch (t: Throwable) {
            logger.warn(
                "${metricsPrefix} received {} error while sending consumer failure metric {}",
                t.javaClass.name,
                t.message
            )
        }
    }

    override fun sendCounterMetric(name: String, vararg tags: String) {
        try {
            meterRegistry.counter("${metricsPrefix}.${name}", *tags).increment()
        } catch (t: Throwable) {
            logger.warn(
                "${metricsPrefix} received {} error while sending ${name} counter metric {}",
                t.javaClass.name,
                t.message
            )
        }
    }

    override fun sendTimerMetric(name: String, start: Instant, stop: Instant, vararg tags: String) {
        try {
            Timer.builder("${metricsPrefix}.${name}")
                .tags(*tags)
                .register(meterRegistry)
                .record(Duration.between(start, stop))
        } catch (t: Throwable) {
            logger.warn(
                "${metricsPrefix} received {} error while sending ${name} timer metric {}",
                t.javaClass.name,
                t.message
            )
        }
    }

    override fun sendGaugeMetric(name: String, number: Number, vararg tags: String) {
        try {
            meterRegistry.gauge(
                "${metricsPrefix}.${name}",
                Tags.of(*tags),
                number
            )
        } catch (t: Throwable) {
            logger.warn(
                "${metricsPrefix} received {} error while sending ${name} gauge metric {}",
                t.javaClass.name,
                t.message
            )
        }
    }
}