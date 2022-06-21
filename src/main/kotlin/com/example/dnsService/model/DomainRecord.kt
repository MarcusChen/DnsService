package com.example.dnsService.model

import java.math.BigInteger

data class DomainRecord(
    val domain: String,
    val values: List<String>,
    val ttl: BigInteger
)
