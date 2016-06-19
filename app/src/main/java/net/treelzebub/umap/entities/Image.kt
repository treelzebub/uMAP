package net.treelzebub.umap.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Tre Murillo on 6/18/16
 */

class Image(val title: String,
            val url: String) : Parcelable {

    companion object {

        val CREATOR: Parcelable.Creator<Image> = object : Parcelable.Creator<Image> {
            override fun createFromParcel(source: Parcel): Image {
                return Image(source)
            }

            override fun newArray(size: Int): Array<Image> {
                return arrayOf()
            }
        }
    }

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString())

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
