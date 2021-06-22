package com.dicoding.thenewyorktimespp.fiction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.thenewyorktimespp.R
import com.dicoding.thenewyorktimespp.core.data.Resource
import com.dicoding.thenewyorktimespp.core.ui.BookAdapter
import com.dicoding.thenewyorktimespp.core.ui.ViewModelFactory
import com.dicoding.thenewyorktimespp.databinding.FragmentFictionBinding

class FictionFragment : Fragment() {

    private lateinit var fictionViewModel: FictionViewModel
    private lateinit var bookAdapter: BookAdapter
    private var _binding: FragmentFictionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = ViewModelFactory.getInstance(requireActivity())
        fictionViewModel =
            ViewModelProvider(this, factory)[FictionViewModel::class.java]

        _binding = FragmentFictionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            bookAdapter = BookAdapter()

            fictionViewModel.fiction.observe(viewLifecycleOwner, { fiction ->
                if (fiction != null) {
                    when (fiction) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            bookAdapter.setData(fiction.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                fiction.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            setupRecyclerView()
            onFictionSelected()
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvFiction) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = bookAdapter
        }
    }

    private fun onFictionSelected() {
        bookAdapter.onItemClick = { selectedData ->
            /*val intent = Intent(activity, DetailTourismActivity::class.java)
            intent.putExtra(DetailTourismActivity.EXTRA_DATA, selectedData)
            startActivity(intent)*/
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}