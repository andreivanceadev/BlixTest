package com.example.blixtest.features.chat

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blixtest.data.FriendsRepository
import com.example.blixtest.model.ChatMessage
import com.example.blixtest.model.Friend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val friendsRepository: FriendsRepository) :
    ViewModel() {

    private val _chatState = mutableStateOf(ChatState())
    val chatState: State<ChatState> = _chatState
    fun getChatHistory(friendId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val friend = friendsRepository.getFriends().find { it.id == friendId }
            val lastState = _chatState.value
            _chatState.value = lastState.copy(
                friend = friend,
                chatHistory = mockChat(friend!!)
            )
        }
    }

    private fun mockChat(friend: Friend) =
        listOf(
            ChatMessage(
                authorId = friend.id,
                authorName = friend.name,
                message = "How are you?",
                dateTime = Date()
            ),
            ChatMessage(
                message = "Good, how about you?",
                dateTime = Date()
            ),
            ChatMessage(
                authorId = friend.id,
                authorName = friend.name,
                message = "I'm good just writing this long message jigkasdgfnkas asdkfnask fnasdkfj naskdfjn asdkfjnaskdfnasdkfn askfjn askdfjn aksdjnfkasdjnf",
                dateTime = Date()
            )

        )
}