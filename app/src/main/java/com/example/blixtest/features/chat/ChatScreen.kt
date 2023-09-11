@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.blixtest.features.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blixtest.model.ChatMessage
import com.example.blixtest.model.Friend
import com.example.blixtest.toPrettyString
import com.example.blixtest.ui.theme.BlixTestTheme
import java.util.Date

@Composable
@Preview(showSystemUi = true)
private fun PreviewChatScreen() {
    BlixTestTheme {
        ChatScreen(1)
    }
}

@Composable
fun ChatScreen(friendId: Int) {
    val mockChat =
        listOf(
            ChatMessage(
                authorId = 1,
                authorName = "Radu",
                message = "How are you?",
                dateTime = Date()
            ),
            ChatMessage(
                message = "Good, how about you?",
                dateTime = Date()
            ),
            ChatMessage(
                authorId = 1,
                authorName = "Radu",
                message = "I'm good just writing this long damn message jigkasdgfnkas asdkfnask fnasdkfj naskdfjn asdkfjnaskdfnasdkfn askfjn askdfjn aksdjnfkasdjnf",
                dateTime = Date()
            )

        )
    val mockFriend = Friend(1, "Radu", mockChat)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = mockFriend.name, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(mockFriend.chatHistory) { message ->
                    ChatListItem(chatMessage = message)
                }
            }
        }
    }
}


@Composable
private fun ChatListItem(chatMessage: ChatMessage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (chatMessage.authorId == null) Alignment.Start else Alignment.End
    ) {
        Column {
            Text(text = chatMessage.authorName)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = chatMessage.dateTime.toPrettyString(),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        ElevatedCard {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = chatMessage.message)
            }
        }

    }
}

