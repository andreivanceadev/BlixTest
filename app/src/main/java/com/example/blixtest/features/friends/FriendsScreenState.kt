package com.example.blixtest.features.friends

import com.example.blixtest.model.Friend

data class FriendsScreenState(
    val friends: List<Friend> = emptyList(),
    val showAddNewFriendDialog: Boolean = false
)