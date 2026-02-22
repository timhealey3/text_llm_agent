package com.example.text_llm_agent

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TextLlmAgentApplication {

    @PostConstruct
    fun init() {
        val sqlUtil = SQLUtil()
        sqlUtil.initDb()
    }
}

fun main(args: Array<String>) {
    runApplication<TextLlmAgentApplication>(*args)
}
