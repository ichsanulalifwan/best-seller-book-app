package com.dicoding.thenewyorktimespp.core.domain.repository

import androidx.lifecycle.LiveData
import com.dicoding.thenewyorktimespp.core.data.Resource
import com.dicoding.thenewyorktimespp.core.domain.model.Fiction
import com.dicoding.thenewyorktimespp.core.domain.model.Nonfiction

interface IBookRepository {

    fun getAllFiction(): LiveData<Resource<List<Fiction>>>

    fun getFavoriteFiction(): LiveData<List<Fiction>>

    fun setFavoriteFiction(fiction: Fiction, state: Boolean)

    /*fun getAllNonfiction(): LiveData<Resource<List<Nonfiction>>>

    fun getFavoriteNonfiction(): LiveData<List<Nonfiction>>

    fun setFavoriteNonfiction(nonfiction: Nonfiction, state: Boolean)*/
}