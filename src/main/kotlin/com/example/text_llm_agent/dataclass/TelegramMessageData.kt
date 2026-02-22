package com.example.text_llm_agent.dataclass

data class TelegramMessageData(
    val message_id: Long,
    val chat: TelegramChatData?,
    val from: TelegramUserInfoData?,
    val text: String?
)
