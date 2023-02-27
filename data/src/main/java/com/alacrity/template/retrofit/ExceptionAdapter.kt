package com.alacrity.template.retrofit

import com.alacrity.template.exceptions.TemplateException
import com.squareup.moshi.*

class ExceptionAdapter : JsonAdapter<TemplateException>() {

    @FromJson
    override fun fromJson(reader: JsonReader): TemplateException? {
        return try {
            val dateAsString = reader.nextString()
            TemplateException(dateAsString)
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: TemplateException?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }
}