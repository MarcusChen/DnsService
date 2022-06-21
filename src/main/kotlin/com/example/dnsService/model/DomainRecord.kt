package com.example.dnsService.model

data class DomainRecord(
    val domain: String,
    val values: List<String>,
    val ttl: Int
)
