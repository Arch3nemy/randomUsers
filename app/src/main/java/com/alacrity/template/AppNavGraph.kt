package com.alacrity.template

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alacrity.template.Destinations.HOME_ROUTE
import com.alacrity.template.Destinations.SECOND_SCREEN
import com.alacrity.template.Destinations.THIRD_SCREEN
import com.alacrity.template.entity.ParcelableApiResponse
import com.alacrity.template.ui.main.MainViewModel
import com.alacrity.template.util.ApiResponseNavType

object Destinations {
    const val HOME_ROUTE = "home"
    const val SECOND_SCREEN = "details"
    const val THIRD_SCREEN = "details_v2"
}

@Composable
fun AppNavGraph(
    context: Context,
    homeViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(HOME_ROUTE) {
            MainScreen(
                context = context,
                viewModel = homeViewModel,
            )
        }

        composable(
            "${SECOND_SCREEN}/{sId}",
            arguments = listOf(navArgument("sId") {
                type = NavType.StringType
            })
        ) {
            SecondScreen(homeViewModel, it.arguments?.getString("tId") ?: "")
        }

        composable(
            route = "${THIRD_SCREEN}/{apiResponse}",
            arguments = listOf(
                navArgument("apiResponse") {
                    type = ApiResponseNavType
                }
            )
        ) { backStackEntry ->
            val apiResponse = backStackEntry.arguments?.getParcelable<ParcelableApiResponse>("apiResponse")
            ThirdScreen(viewModel = homeViewModel, arg = apiResponse.toString())
        }


    }
}

@Composable
fun ThirdScreen(viewModel: MainViewModel, arg: String) {

}

@Composable
fun SecondScreen(viewModel: MainViewModel, arg: String) {

}

/**
 * navHostController.navigate("userPage?apiResponse=${Uri.encode(gson.toJson(apiResponse))}")
 */