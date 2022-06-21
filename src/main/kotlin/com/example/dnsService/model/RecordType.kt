package com.example.dnsService.model

enum class RecordType {
//    from https://docs.oracle.com/javase/8/docs/technotes/guides/jndi/jndi-dns.html#PROP
    A,
    NS,
    CNAME,
    SOA,
    PTR,
    MX,
    TXT,
    HINFO,
    AAAA,
    NAPTR,
    SRV
}