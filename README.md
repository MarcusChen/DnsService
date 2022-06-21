# DnsService
JSON-over-HTTP API for performing DNS Lookup

## How Does it Work

This service provides one endpoint to obtain the DNS records of a url with a POST. The POST request must have a valid url and a list of DNS record types. 
The valid DNS record types are: "A", "TXT", "CNAME", "SRV", "NS", "SOA", "PTR", "MX", "HINFO", "AAAA", "NAPTR" (see usage section below fo details)

## Setup

Run the app with these environment variables:
java.naming.factory.initial=com.sun.jndi.dns.DnsContextFactory
java.naming.provider.url=dns://8.8.8.8

## Usage

Here are examples for the endpoint:

```text
curl --location --request POST 'http://localhost:8000' \
--header 'Content-Type: application/json' \
--data-raw '{
    "lookup": "google.com", 
    "recordTypes": ["A", "TXT", "CNAME", "SRV", "NS", "SOA", "PTR", "MX", "HINFO", "AAAA", "NAPTR"]}'

```

## Metrics

Count info can be access at this endpoint: http://localhost:8000/actuator/prometheus

search for "message_received_total", "processing_success_total", and "processing_failure_total"


