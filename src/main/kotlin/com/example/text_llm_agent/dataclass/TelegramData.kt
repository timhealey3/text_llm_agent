package com.example.text_llm_agent.dataclass

data class TelegramData(
    val ok: Boolean,
    val result: List<TelegramResultData>,
)
