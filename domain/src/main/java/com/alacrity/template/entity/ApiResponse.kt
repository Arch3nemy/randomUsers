package com.alacrity.template.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "userId") var userId: Int,
    @Json(name = "id") var id: Int,
    @Json(name = "title") var title: String,
    @Json(name = "completed") var completed: Boolean
)