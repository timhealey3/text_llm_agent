package com.example.text_llm_agent
import com.example.text_llm_agent.dataclass.TelegramData
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class TelegramService(private val webClient: WebClient) {
    private var baseTelegramURL = "https://api.telegram.org/bot"
    private var apiToken: String = "REPLACE TOKEN"
    private var getUpdates: String = "/getUpdates"
    private var sendMessage: String = "/sendMessage"
    fun getUpdates(index: String): TelegramData {
        val url = "$baseTelegramURL$apiToken$getUpdates?offset=$index"

        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(TelegramData::class.java)
            .block()!!
    }

    fun postMessage(chatID: String, message: String): String {
        val url = "$baseTelegramURL$apiToken$sendMessage"

        return webClient.post()
            .uri(url)
            .bodyValue(
                mapOf(
                    "chat_id" to chatID,
                    "text" to message
                )
            )
            .retrieve()
            .bodyToMono(String::class.java)
            .block()!!
    }
}