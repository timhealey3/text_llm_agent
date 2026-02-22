package com.example.text_llm_agent
import com.example.text_llm_agent.dataclass.TelegramData
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class TelegramService(private val webClient: WebClient) {
    private var baseTelegramURL = "https://api.telegram.org/bot"
    private var apiToken: String = "REPLACE ME"
    private var getUpdates: String = "/getUpdates"
    fun getUpdates(index: String): TelegramData {
        val url = "$baseTelegramURL$apiToken$getUpdates?offset=$index"

        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(TelegramData::class.java)
            .block()!!
    }
}