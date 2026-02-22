package com.example.text_llm_agent.dataclass

data class TelegramUserInfoData(
    val id: Long,
    val username: String?,
    val first_name: String?,
    val is_bot: Boolean?
)
