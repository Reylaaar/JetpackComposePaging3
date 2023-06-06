package com.reylar.composepaging3.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Movie(
    val id: Int,
    val overview: String,
    val popularity: Double,
    val poster_path: String = "",
    val title: String = "",
    val page: Int
)
