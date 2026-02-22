package com.example.text_llm_agent.dataclass

data class TelegramResultData(
    val update_id: Long,
    val message: TelegramMessageData?   // chat is inside message
)