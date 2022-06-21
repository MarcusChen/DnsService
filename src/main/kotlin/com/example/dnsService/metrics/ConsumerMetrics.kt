package com.example.dnsService.metrics

import java.time.Instant

interface ConsumerMetrics {

    /**
     * Increment the message received counter metric
     */
    fun sendMessageReceivedMetric()

    /**
     * Send the processing time metric for the given [processorTagValue] using the [Duration]
     * between [start] and [stop]
     */
    fun sendProcessingTimeMetric(start: Instant, stop: Instant, processorTagValue: String)

    /**
     * Increment the consumer failure counter metric
     */
    fun sendFailureMetric()

    /**
     * Send a custom counter metric with [name] and [tags]
     */
    fun sendCounterMetric(name: String, vararg tags: String)

    /**
     * Send a custom timer metric with [name] and [tags] using the [Duration]
     * between [start] and [stop]
     */
    fun sendTimerMetric(name: String, start: Instant, stop: Instant, vararg tags: String)

    /**
     * Send a custom gauge metric with [name] and [tags] for value [number]
     */
    fun sendGaugeMetric(name: String, number: Number, vararg tags: String)


}