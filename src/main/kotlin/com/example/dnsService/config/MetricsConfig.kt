package com.example.dnsService.config

import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MetricsConfig {

    @Bean
    fun simpleMeterRegistry() = SimpleMeterRegistry()

}