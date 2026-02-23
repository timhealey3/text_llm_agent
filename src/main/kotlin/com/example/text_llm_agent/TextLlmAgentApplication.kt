package com.example.text_llm_agent

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TextLlmAgentApplication(private val sqlUtil: SQLUtil) {

    @PostConstruct
    fun init() {
        sqlUtil.initDb()
    }
}

fun main(args: Array<String>) {
    runApplication<TextLlmAgentApplication>(*args)
}
