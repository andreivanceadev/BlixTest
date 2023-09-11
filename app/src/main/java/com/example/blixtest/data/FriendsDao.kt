package com.example.blixtest.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class FriendsDao @Inject constructor(
    private val sharedPrefs: SharedPreferences,
    private val gson: Gson
) {
    private fun getAllFriends(): List<FriendDto> {
        return sharedPrefs.getString(friendsKey, null)?.toFriendsDto() ?: emptyList()
    }

    fun getFriends(): List<FriendDto> {
        return getAllFriends()
    }

    fun saveFriend(friendName: String) {
        val friends = getAllFriends().toMutableList()
        val lastFriendId = friends.lastOrNull()?.id
        friends.add(
            FriendDto(
                id = lastFriendId?.let { it + 1 } ?: 1,
                name = friendName,
                chatHistory = emptyList()
            )
        )
        sharedPrefs.edit().putString(friendsKey, friends.toJson()).apply()
    }

    companion object {
        private const val friendsKey = "FRIENDS"
    }

    private fun String.toFriendsDto(): List<FriendDto> {
        val listType = object : TypeToken<List<FriendDto>>() {}.type
        return gson.fromJson(this, listType)
    }

    private fun List<FriendDto>.toJson() = gson.toJson(this)

}