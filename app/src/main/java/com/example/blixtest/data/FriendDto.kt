package com.example.blixtest.data

data class FriendDto(
    val id: Int,
    val name: String,
    val chatHistory: List<ChatMessageDto>
) {
}