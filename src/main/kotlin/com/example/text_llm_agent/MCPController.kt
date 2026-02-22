package com.example.text_llm_agent
import com.example.text_llm_agent.dataclass.TelegramData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MCPController(
    private var telegramService: TelegramService,
) {

    @GetMapping("/text-llm-agent")
    fun textEndPoint(): TelegramData {
        // TODO pass in index
        val sqlUtil = SQLUtil()
        val index = sqlUtil.queryIndex()
        val returnData: TelegramData = telegramService.getUpdates((index.toLong() + 1).toString())
        println(returnData)
        var prompt = ""
        var lastIndex = index
        for (x in returnData.result) {
            prompt += x.message?.text.toString() + " "
            lastIndex = x.update_id.toString()
        }
        sqlUtil.addIndex(lastIndex)
        return returnData
    }
}