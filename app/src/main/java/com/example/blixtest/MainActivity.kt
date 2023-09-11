package com.example.blixtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.blixtest.features.chat.ChatScreen
import com.example.blixtest.features.friends.FriendsScreen
import com.example.blixtest.ui.theme.BlixTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlixTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "friends") {
                        composable("friends") {
                            FriendsScreen(onFriendClick = {
                                navController.navigate("chat/$it")
                            })
                        }
                        composable(
                            "chat/{friendId}",
                            arguments = listOf(navArgument("friendId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            backStackEntry.arguments?.getInt("friendId")?.let { ChatScreen(it) }
                        }
                    }
                }
            }
        }
    }
}
