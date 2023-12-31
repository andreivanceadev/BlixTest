package com.example.blixtest.model

import java.util.Date

data class ChatMessage(
    val authorId: Int? = null, //no author ID = sent by me
    val authorName: String = "You",
    val message: String,
    val dateTime: Date
)