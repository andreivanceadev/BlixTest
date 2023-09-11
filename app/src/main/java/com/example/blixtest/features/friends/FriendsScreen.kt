@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.blixtest.features.friends

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blixtest.model.Friend
import com.example.blixtest.ui.theme.BlixTestTheme

@Composable
@Preview(showSystemUi = true)
fun PreviewFriendsScreen() {
    BlixTestTheme {
        FriendsScreen()
    }
}

@Composable
fun FriendsScreen(
    viewModel: FriendsViewModel = hiltViewModel(),
    onFriendClick: (id: Int) -> Unit = {},
) {

    val screenState = viewModel.friendsScreenState

    LaunchedEffect(key1 = null) {
        viewModel.loadFriends()
    }

    Scaffold(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onAddNewFriend() }) {
                Icon(Icons.Filled.Add, contentDescription = "Add new friend")
            }
        }
    ) {
        AddFriendAlertDialog(
            showDialog = screenState.value.showAddNewFriendDialog,
            onDismiss = { viewModel.cancelAddFriend() },
            onAddFriend = { friendName -> viewModel.saveFriend(friendName) }
        )
        if (screenState.value.friends.isEmpty()) {
            EmptyListScreen()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(screenState.value.friends) { friend ->
                    FriendListItem(friend) { friendId ->
                        onFriendClick(friendId)
                    }
                }
            }
        }
    }
}

@Composable
fun AddFriendAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onAddFriend: (String) -> Unit
) {
    if (showDialog) {
        var friendName by remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Add Friend") },
            text = {
                TextField(
                    value = friendName,
                    onValueChange = { friendName = it },
                    label = { Text("Friend Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (friendName.isNotBlank()) {
                            onAddFriend(friendName)
                            onDismiss()
                        }
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun EmptyListScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You have no friends...")
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
