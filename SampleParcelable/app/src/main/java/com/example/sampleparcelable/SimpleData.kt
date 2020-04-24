package com.example.sampleparcelable

import android.os.Parcel
import android.os.Parcelable

class SimpleData : Parcelable {
    var number: Int? = null
    var message: String? = null

    constructor(num: Int, msg: String) {
        number = num
        message = msg
    }

    constructor(src: Parcel) {
        number = src.readInt()
        message = src.readString()
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(number!!)
        dest.writeString(message)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<*> = object : Parcelable.Creator<Any?> {
            override fun createFromParcel(`in`: Parcel): SimpleData? {
                return SimpleData(`in`)
            }

            override fun newArray(size: Int): Array<SimpleData?> {
                return arrayOfNulls(size)
            }
        }
    }
}