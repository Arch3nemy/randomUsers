package com.alacrity.template.util

import android.os.Bundle
import androidx.navigation.NavType
import com.alacrity.template.entity.ParcelableApiResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun getApiResponseNavType(gson: Gson): NavType<ParcelableApiResponse> =
    object : NavType<ParcelableApiResponse>(false) {
        override val name: String
            get() = "uniqueApiResponse"

        override fun get(bundle: Bundle, key: String): ParcelableApiResponse? {
            return bundle.getParcelable(key)
        }

        override fun parseValue(value: String): ParcelableApiResponse {
            return gson.fromJson(value, object : TypeToken<ParcelableApiResponse>() {}.type)
        }

        override fun put(bundle: Bundle, key: String, value: ParcelableApiResponse) {
            bundle.putParcelable(key, value)
        }
    }
