package com.picker.moth6_2_dz.models

import android.os.Parcel
import android.os.Parcelable
import kotlin.String

data class ImageModel(
    var image: String?,
    var isSelected: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.write(image)
        parcel.write(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageModel> {
        override fun createFromParcel(parcel: Parcel): ImageModel {
            return ImageModel(parcel)
        }

        override fun newArray(size: Int): Array<ImageModel?> {
            return arrayOfNulls(size)
        }
    }

    private fun Parcel.write(value: Any?) {
        when (value) {
            is String -> writeString(value)
            is Int -> writeInt(value)
            is Boolean -> writeByte(1)
            is Float -> writeFloat(value)
        }
    }
}


