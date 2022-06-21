package com.example.dnsService

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class LookupControllerTest {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Test
    fun testGetDnsRecordsNotFound() {
        mockMvc!!.perform(
            post("/")
                .contentType("application/json")
                .content("{\"lookup\": \"google.com\", \"recordTypes\": [\"A\", \"TT\"]}")
        )
            .andExpect(status().isBadRequest)
    }
}
