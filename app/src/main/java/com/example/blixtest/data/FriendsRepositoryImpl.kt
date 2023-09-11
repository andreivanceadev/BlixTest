package com.example.blixtest.data

import com.example.blixtest.model.ChatMessage
import com.example.blixtest.model.Friend
import com.example.blixtest.toDate
import javax.inject.Inject

class FriendsRepositoryImpl @Inject constructor(private val friendsDao: FriendsDao) :
    FriendsRepository {
    override fun getFriends(): List<Friend> {
        return friendsDao.getFriends().map { it.toDataModel() }
    }

    override fun saveFriend(friendName: String) {
        friendsDao.saveFriend(friendName)
    }

}

private fun FriendDto.toDataModel() =
    Friend(
        id = this.id,
        name = this.name,
        chatHistory = this.chatHistory.map { it.toDataModel() }
    )

private fun ChatMessageDto.toDataModel() =
    ChatMessage(
        authorId = this.authorId,
        authorName = this.authorName,
        message = this.message,
        dateTime = this.dateTime.toDate()
    )
