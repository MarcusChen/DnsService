package com.example.dnsService.model

data class Request(
    val lookup: String,
    val recordTypes: List<RecordType>
)
