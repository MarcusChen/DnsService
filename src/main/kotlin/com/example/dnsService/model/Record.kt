package com.example.dnsService.model

data class Record (
    val type: RecordType,
    val response: DomainRecord
)