package com.alacrity.randomUsers.ui.history.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alacrity.randomUsers.entity.User
import com.alacrity.randomUsers.ui.main.screens.UserView

@Composable
fun HistoryScreen(users: List<User>, onUserClick: (User) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(users) { user ->
            UserView(user = user) {
                onUserClick(user)
            }
        }
    }
}