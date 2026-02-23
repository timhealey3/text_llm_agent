package com.example.text_llm_agent

import io.github.ollama4j.OllamaAPI
import io.github.ollama4j.models.response.OllamaResult

class LLMAPI {

    fun queryLLM(prompt: String): OllamaResult? {
        val host = "http://localhost:11434/"
        val ollamaAPI = OllamaAPI(host)
        val response: OllamaResult? = ollamaAPI.generate(
            "gemma3:4b",
            prompt,
            null
        )
        return response
    }
}