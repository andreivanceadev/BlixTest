package com.example.blixtest.data

data class ChatMessageDto(
    val authorId: Int? = null, //no author ID = sent by me
    val authorName: String = "You",
    val message: String,
    val dateTime: String
)
