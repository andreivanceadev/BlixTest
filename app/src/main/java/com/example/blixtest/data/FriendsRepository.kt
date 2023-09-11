package com.example.blixtest.data

import com.example.blixtest.model.Friend

interface FriendsRepository {
    fun getFriends(): List<Friend>
    fun saveFriend(friendName: String)
}
