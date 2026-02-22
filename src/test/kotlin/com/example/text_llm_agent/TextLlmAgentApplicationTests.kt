package com.example.text_llm_agent

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(WebClientConfig::class)
class TextLlmAgentApplicationTests {
    @Test
    fun contextLoads() {}
}
