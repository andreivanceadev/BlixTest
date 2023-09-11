package com.example.blixtest.features.chat

import com.example.blixtest.model.ChatMessage
import com.example.blixtest.model.Friend

data class ChatState(
    val friend: Friend? = null,
    val chatHistory: List<ChatMessage> = emptyList()
)
