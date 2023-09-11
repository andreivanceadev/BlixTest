@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.blixtest.features.friends

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blixtest.model.Friend
import com.example.blixtest.ui.theme.BlixTestTheme

@Composable
@Preview(showSystemUi = true)
fun PreviewFriendsScreen() {
    BlixTestTheme {
        FriendsScreen({}, {})
    }
}

val mockFriends = listOf(
    Friend(1, "Radu"),
    Friend(2, "Mircea"),
    Friend(3, "Cristian"),
)

@Composable
fun FriendsScreen(
    onFriendClick: (id: Int) -> Unit,
    onAddFriend: () -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = onAddFriend) {
                Icon(Icons.Filled.Add, contentDescription = "Add new friend")
            }
        }
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(mockFriends) { friend ->
                FriendListItem(friend) { friendId ->
                    onFriendClick(friendId)
                }
            }
        }
    }
}

@Composable
private fun FriendListItem(
    friend: Friend,
    onClick: (id: Int) -> Unit
) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick(friend.id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = friend.name)
        }
    }

}
