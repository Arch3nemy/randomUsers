package com.alacrity.randomUsers

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.alacrity.randomUsers.ui.main.MainViewModel
import com.alacrity.randomUsers.ui.history.HistoryViewModel
import com.alacrity.randomUsers.ui.userinfo.UserInfoViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var historyViewModel: HistoryViewModel

    @Inject
    lateinit var userInfoViewModel: UserInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContent {
            RandomUsersApp(
                homeViewModel = mainViewModel,
                historyViewModel = historyViewModel,
                userInfoViewModel = userInfoViewModel
            )
        }
    }

}