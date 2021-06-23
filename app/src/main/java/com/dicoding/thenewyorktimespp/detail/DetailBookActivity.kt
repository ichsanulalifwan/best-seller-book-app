package com.dicoding.thenewyorktimespp.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.thenewyorktimespp.R
import com.dicoding.thenewyorktimesapp.core.domain.model.Book
import com.dicoding.thenewyorktimespp.databinding.ActivityDetailBookBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBookBinding
    private val detailBookViewModel: DetailBookViewModel by viewModel()
    private val args by navArgs<DetailBookActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val selectedBook = args.book
        val type = args.type
        showDetailBook(selectedBook, type)
    }

    private fun showDetailBook(detailBook: Book, type: Int) {

        supportActionBar?.title = detailBook.title

        binding.apply {
            tvTitleBook.text = detailBook.title
            tvDescription.text = detailBook.description
            tvRank.text = detailBook.rank.toString()
            tvAuthor.text = detailBook.author
            tvContributor.text = detailBook.contributor
            tvPublisher.text = detailBook.publisher
            tvIsbn10.text = detailBook.primaryIsbn10
            tvIsbn13.text = detailBook.primaryIsbn13

            Glide.with(this@DetailBookActivity)
                .load(detailBook.bookImage)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.icons_book_64))
                .into(imgCoverBook)

            btnAmazon.setOnClickListener {
                val urlIntent = Intent(Intent.ACTION_VIEW)
                urlIntent.data = Uri.parse(detailBook.amazonProductUrl)
                startActivity(urlIntent)
            }
        }

        var favoriteState = detailBook.isFavorite
        setFavouriteState(favoriteState)

        when (type) {
            0 -> {
                binding.btnFav.setOnClickListener {
                    favoriteState = !favoriteState
                    detailBookViewModel.setFavoriteFiction(detailBook, favoriteState)
                    showToast(detailBook)
                    setFavouriteState(favoriteState)
                }
            }

            1 -> {
                binding.btnFav.setOnClickListener {
                    favoriteState = !favoriteState
                    detailBookViewModel.setFavoriteNonfiction(detailBook, favoriteState)
                    showToast(detailBook)
                    setFavouriteState(favoriteState)
                }
            }
        }
    }

    private fun showToast(data: Book) {
        if (!data.isFavorite) Toast.makeText(
            this,
            data.title + " " + "Added to Favorite",
            Toast.LENGTH_SHORT
        ).show()
        else Toast.makeText(
            this,
            data.title + " " + "Deleted from Favorite",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setFavouriteState(state: Boolean) {
        if (state) {
            binding.btnFav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.btnFav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}