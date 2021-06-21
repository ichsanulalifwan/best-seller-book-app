package com.dicoding.thenewyorktimespp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Nonfiction(
    var primaryIsbn10: String,
    val primaryIsbn13: String,
    val rank: Int,
    val rankLastWeek: Int,
    val title: String,
    val description: String,
    val author: String,
    val contributor: String,
    val publisher: String,
    val bookImage: String,
    val amazonProductUrl: String,
    var isFavorite: Boolean
) : Parcelable