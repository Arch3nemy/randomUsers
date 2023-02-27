package com.alacrity.template.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import com.alacrity.template.entity.ApiResponse
import com.alacrity.template.entity.ParcelableApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//Returns pair of screen width and height
@Composable
inline fun <reified T> getScreenSize(): Pair<T, T> {
    val configuration = LocalConfiguration.current
    with(configuration) {
        return when (T::class) {
            Int::class -> Pair(screenWidthDp as T, screenHeightDp as T)
            Dp::class -> Pair(screenWidthDp.dp as T, screenHeightDp.dp as T)
            else -> Pair(
                with(LocalDensity.current) { screenWidthDp.dp.toPx() } as T,
                with(LocalDensity.current) { screenHeightDp.dp.toPx() } as T)
        }
    }
}

val ApiResponseNavType: NavType<ParcelableApiResponse> = object : NavType<ParcelableApiResponse>(false) {
    override val name: String
        get() = "uniqueApiResponse"

    override fun get(bundle: Bundle, key: String): ParcelableApiResponse? {
        return bundle.getParcelable(key)
    }

    override fun parseValue( value: String): ParcelableApiResponse {
        return Gson().fromJson(value, object : TypeToken<ParcelableApiResponse>() {}.type)
    }

    override fun put(bundle: Bundle, key: String, value: ParcelableApiResponse) {
        bundle.putParcelable(key, value)
    }
}