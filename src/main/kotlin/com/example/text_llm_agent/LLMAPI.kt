package com.example.text_llm_agent

import io.github.ollama4j.OllamaAPI
import io.github.ollama4j.models.response.OllamaResult
import org.springframework.stereotype.Component

@Component
class LLMAPI {

    fun queryLLM(prompt: String): OllamaResult? {
        val host = "http://localhost:11434/"
        val ollamaAPI = OllamaAPI(host)
        ollamaAPI.setRequestTimeoutSeconds(120)
        val response: OllamaResult? = ollamaAPI.generate(
            "gemma3:4b",
            prompt,
            null
        )
        return response
    }
}