package com.alacrity.randomUsers.room

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "users")
data class UserTableItem(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "largeImage") val largeImage: String,
    @ColumnInfo(name = "age") val age: Int,
    @PrimaryKey val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "gender") val gender: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(largeImage)
        parcel.writeInt(age)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserTableItem> {
        override fun createFromParcel(parcel: Parcel): UserTableItem {
            return UserTableItem(parcel)
        }

        override fun newArray(size: Int): Array<UserTableItem?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String = Uri.encode(Gson().toJson(this))

}
