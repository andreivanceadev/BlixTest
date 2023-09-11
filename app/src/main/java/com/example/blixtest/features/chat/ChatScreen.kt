@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.blixtest.features.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blixtest.model.ChatMessage
import com.example.blixtest.toPrettyString
import com.example.blixtest.ui.theme.BlixTestTheme

@Composable
@Preview(showSystemUi = true)
private fun PreviewChatScreen() {
    BlixTestTheme {
        ChatScreen(1)
    }
}

@Composable
fun ChatScreen(
    friendId: Int,
    viewModel: ChatViewModel = hiltViewModel()
) {

    val state = viewModel.chatState

    LaunchedEffect(key1 = null) {
        viewModel.getChatHistory(friendId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = state.value.friend?.name ?: "", fontWeight = FontWeight.Bold)
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
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.value.chatHistory) { message ->
                        ChatListItem(chatMessage = message)
                    }
                }

                ChatInput(modifier = Modifier.align(Alignment.BottomCenter),
                    onMessageSent = {})
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

@Composable
fun ChatInput(
    modifier: Modifier = Modifier,
    onMessageSent: (String) -> Unit
) {
    var message by remember { mutableStateOf("") }

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Input text field
            BasicTextField(
                value = message,
                onValueChange = { message = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        if (message.isNotBlank()) {
                            onMessageSent(message)
                            message = ""
                        }
                    }
                ),
                modifier = Modifier
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.surface),
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                singleLine = true,
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Send button
            IconButton(
                onClick = {
                    if (message.isNotBlank()) {
                        onMessageSent(message)
                        message = ""
                    }
                }
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
