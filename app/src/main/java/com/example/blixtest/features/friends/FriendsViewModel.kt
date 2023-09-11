package com.example.blixtest.features.friends

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blixtest.data.FriendsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    val friendsRepository: FriendsRepositoryImpl
) : ViewModel() {

    private val _friendsScreenState = mutableStateOf(FriendsScreenState())
    val friendsScreenState: State<FriendsScreenState> = _friendsScreenState
    fun loadFriends() {
        viewModelScope.launch(Dispatchers.IO) {
            val friends = friendsRepository.getFriends()
            val lastState = _friendsScreenState.value
            _friendsScreenState.value = lastState.copy(friends = friends)
        }
    }

    fun onAddNewFriend() {
        val lastState = _friendsScreenState.value
        _friendsScreenState.value = lastState.copy(showAddNewFriendDialog = true)
    }

    fun cancelAddFriend() {
        val lastState = _friendsScreenState.value
        _friendsScreenState.value = lastState.copy(showAddNewFriendDialog = false)
    }

    fun saveFriend(friendName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            friendsRepository.saveFriend(friendName)
            val lastState = _friendsScreenState.value
            _friendsScreenState.value = lastState.copy(friends = friendsRepository.getFriends())
        }
    }

}