package com.example.text_llm_agent
import com.example.text_llm_agent.dataclass.TelegramData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import io.github.ollama4j.OllamaAPI
import io.github.ollama4j.models.response.OllamaResult

@RestController
class MCPController(
    private var telegramService: TelegramService,
) {

    @GetMapping("/text-llm-agent")
    fun textEndPoint(): Boolean {
        val sqlUtil = SQLUtil()
        val index = sqlUtil.queryIndex()
        val returnData: TelegramData = telegramService.getUpdates((index.toLong() + 1).toString())
        var prompt = ""
        var lastIndex = index
        var lastChatID = ""
        for (x in returnData.result) {
            prompt += x.message?.text.toString() + " "
            lastIndex = x.update_id.toString()
            lastChatID = x.message?.chat?.id.toString()
        }
        val llm = LLMAPI()
        //val llmResponse: OllamaResult? = llm.queryLLM(prompt)
        sqlUtil.addIndex(lastIndex)
        return try {
            telegramService.postMessage(lastChatID, prompt)
            true
        } catch (e: Exception) {
            false
        }
    }
}