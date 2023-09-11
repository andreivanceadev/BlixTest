package com.example.blixtest.model

data class Friend(
    val id: Int,
    val name: String,
    val chatHistory: List<ChatMessage>
)