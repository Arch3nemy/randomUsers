package com.alacrity.template.entity

import android.os.Parcel
import android.os.Parcelable

data class ParcelableApiResponse(
    val userId: Int,
    val id: Int,
    val title: String?,
    val completed: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeByte(if (completed) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableApiResponse> {
        override fun createFromParcel(parcel: Parcel): ParcelableApiResponse {
            return ParcelableApiResponse(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableApiResponse?> {
            return arrayOfNulls(size)
        }
    }
}

