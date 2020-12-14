package com.mvvm_structure_demo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CommonResponse(
    val status: String,
    val message: String
    )
