package com.dicoding.thenewyorktimespp.core.utils

import com.dicoding.thenewyorktimespp.core.data.source.local.entity.FictionEntity
import com.dicoding.thenewyorktimespp.core.data.source.local.entity.NonfictionEntity
import com.dicoding.thenewyorktimespp.core.data.source.remote.response.BooksItem
import com.dicoding.thenewyorktimespp.core.domain.model.Fiction
import com.dicoding.thenewyorktimespp.core.domain.model.Nonfiction

object DataMapper {

    fun mapResponsesToFictionEntities(input: List<BooksItem>): List<FictionEntity> {
        val fictionList = ArrayList<FictionEntity>()
        input.map {
            val fiction = FictionEntity(
                primaryIsbn10 = it.primaryIsbn10,
                primaryIsbn13 = it.primaryIsbn13,
                rank = it.rank,
                rankLastWeek = it.rankLastWeek,
                title = it.title,
                description = it.description,
                author = it.author,
                contributor = it.contributor,
                publisher = it.publisher,
                bookImage = it.bookImage,
                amazonProductUrl = it.amazonProductUrl,
                isFavorite = false
            )
            fictionList.add(fiction)
        }
        return fictionList
    }

    fun mapEntitiesToFictionDomain(input: List<FictionEntity>): List<Fiction> =
        input.map {
            Fiction(
                primaryIsbn10 = it.primaryIsbn10,
                primaryIsbn13 = it.primaryIsbn13,
                rank = it.rank,
                rankLastWeek = it.rankLastWeek,
                title = it.title,
                description = it.description,
                author = it.author,
                contributor = it.contributor,
                publisher = it.publisher,
                bookImage = it.bookImage,
                amazonProductUrl = it.amazonProductUrl,
                isFavorite = false
            )
        }

    fun mapDomainToFictionEntity(input: Fiction) = FictionEntity(
        primaryIsbn10 = input.primaryIsbn10,
        primaryIsbn13 = input.primaryIsbn13,
        rank = input.rank,
        rankLastWeek = input.rankLastWeek,
        title = input.title,
        description = input.description,
        author = input.author,
        contributor = input.contributor,
        publisher = input.publisher,
        bookImage = input.bookImage,
        amazonProductUrl = input.amazonProductUrl,
        isFavorite = false
    )

    fun mapResponsesToNonfictionEntities(input: List<BooksItem>): List<NonfictionEntity> {
        val nonfictionList = ArrayList<NonfictionEntity>()
        input.map {
            val nonfiction = NonfictionEntity(
                primaryIsbn10 = it.primaryIsbn10,
                primaryIsbn13 = it.primaryIsbn13,
                rank = it.rank,
                rankLastWeek = it.rankLastWeek,
                title = it.title,
                description = it.description,
                author = it.author,
                contributor = it.contributor,
                publisher = it.publisher,
                bookImage = it.bookImage,
                amazonProductUrl = it.amazonProductUrl,
                isFavorite = false
            )
            nonfictionList.add(nonfiction)
        }
        return nonfictionList
    }

    fun mapEntitiesToNonfictionDomain(input: List<NonfictionEntity>): List<Nonfiction> =
        input.map {
            Nonfiction(
                primaryIsbn10 = it.primaryIsbn10,
                primaryIsbn13 = it.primaryIsbn13,
                rank = it.rank,
                rankLastWeek = it.rankLastWeek,
                title = it.title,
                description = it.description,
                author = it.author,
                contributor = it.contributor,
                publisher = it.publisher,
                bookImage = it.bookImage,
                amazonProductUrl = it.amazonProductUrl,
                isFavorite = false
            )
        }

    fun mapDomainToNonfictionEntity(input: Nonfiction) = NonfictionEntity(
        primaryIsbn10 = input.primaryIsbn10,
        primaryIsbn13 = input.primaryIsbn13,
        rank = input.rank,
        rankLastWeek = input.rankLastWeek,
        title = input.title,
        description = input.description,
        author = input.author,
        contributor = input.contributor,
        publisher = input.publisher,
        bookImage = input.bookImage,
        amazonProductUrl = input.amazonProductUrl,
        isFavorite = false
    )
}