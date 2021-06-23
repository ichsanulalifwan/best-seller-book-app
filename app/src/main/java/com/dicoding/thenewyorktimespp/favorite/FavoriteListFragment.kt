package com.dicoding.thenewyorktimespp.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.thenewyorktimespp.core.ui.BookAdapter
import com.dicoding.thenewyorktimespp.databinding.FragmentFavoriteListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteListFragment : Fragment() {

    private lateinit var bookAdapter: BookAdapter
    private val favoriteListViewModel: FavoriteListViewModel by viewModel()
    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)

            bookAdapter = BookAdapter()

            setupRecyclerView()
            showLoading(true)

            when (index) {
                0 -> showFavFiction()
                1 -> showFavNonfiction()
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvFav) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = bookAdapter
        }
    }

    private fun showFavFiction() {
        favoriteListViewModel.favoriteFiction.observe(viewLifecycleOwner, { fiction ->
            bookAdapter.setData(fiction)
            binding.viewEmpty.root.visibility =
                if (fiction.isNotEmpty()) View.GONE else View.VISIBLE
        })

        showLoading(false)

        bookAdapter.onItemClick = { selectedData ->
            val action =
                FavoriteFragmentDirections.actionNavigationFavoriteToDetailBookActivity(
                    selectedData,
                    0
                )
            action.let {
                findNavController().navigate(it)
            }
        }
    }

    private fun showFavNonfiction() {
        favoriteListViewModel.favoriteNonfiction.observe(viewLifecycleOwner, { nonfiction ->
            bookAdapter.setData(nonfiction)
            binding.viewEmpty.root.visibility =
                if (nonfiction.isNotEmpty()) View.GONE else View.VISIBLE
        })

        showLoading(false)

        bookAdapter.onItemClick = { selectedData ->
            val action =
                FavoriteFragmentDirections.actionNavigationFavoriteToDetailBookActivity(
                    selectedData,
                    1
                )
            action.let {
                findNavController().navigate(it)
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int): FavoriteListFragment {
            val fragment = FavoriteListFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            fragment.arguments = bundle
            return fragment
        }
    }
}