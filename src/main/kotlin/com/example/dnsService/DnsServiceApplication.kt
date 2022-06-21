package com.example.dnsService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DnsServiceApplication

fun main(args: Array<String>) {
	runApplication<DnsServiceApplication>(*args)
}
