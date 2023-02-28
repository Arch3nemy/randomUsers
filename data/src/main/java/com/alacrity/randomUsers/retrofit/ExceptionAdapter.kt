package com.alacrity.randomUsers.retrofit

import com.alacrity.randomUsers.exceptions.RandomUsersException
import com.squareup.moshi.*

class ExceptionAdapter : JsonAdapter<RandomUsersException>() {

    @FromJson
    override fun fromJson(reader: JsonReader): RandomUsersException? {
        return try {
            val dateAsString = reader.nextString()
            RandomUsersException(dateAsString)
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: RandomUsersException?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }
}