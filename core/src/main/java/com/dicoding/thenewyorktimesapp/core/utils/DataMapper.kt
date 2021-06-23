package com.dicoding.thenewyorktimesapp.core.utils

import com.dicoding.thenewyorktimesapp.core.data.source.local.entity.FictionEntity
import com.dicoding.thenewyorktimesapp.core.data.source.local.entity.NonfictionEntity
import com.dicoding.thenewyorktimesapp.core.data.source.remote.response.fiction.FictionItem
import com.dicoding.thenewyorktimesapp.core.data.source.remote.response.nonfiction.NonfictionItem
import com.dicoding.thenewyorktimesapp.core.domain.model.Book

object DataMapper {

    fun mapResponsesToFictionEntities(input: List<FictionItem>): List<FictionEntity> {
        val fictionList = ArrayList<FictionEntity>()
        input.map {
            val fiction =
                FictionEntity(
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

    fun mapEntitiesToFictionDomain(input: List<FictionEntity>): List<Book> =
        input.map {
            Book(
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
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToFictionEntity(input: Book) =
        FictionEntity(
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
            isFavorite = input.isFavorite
        )

    fun mapResponsesToNonfictionEntities(input: List<NonfictionItem>): List<NonfictionEntity> {
        val nonfictionList = ArrayList<NonfictionEntity>()
        input.map {
            val nonfiction =
                NonfictionEntity(
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

    fun mapEntitiesToNonfictionDomain(input: List<NonfictionEntity>): List<Book> =
        input.map {
            Book(
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
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToNonfictionEntity(input: Book) =
        NonfictionEntity(
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
            isFavorite = input.isFavorite
        )
}