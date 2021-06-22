package com.dicoding.thenewyorktimespp.nonfiction

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
import com.dicoding.thenewyorktimespp.databinding.FragmentNonfictionBinding

class NonfictionFragment : Fragment() {

    private lateinit var nonfictionViewModel: NonfictionViewModel
    private lateinit var bookAdapter: BookAdapter
    private var _binding: FragmentNonfictionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = ViewModelFactory.getInstance(requireActivity())
        nonfictionViewModel =
            ViewModelProvider(this, factory)[NonfictionViewModel::class.java]

        _binding = FragmentNonfictionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            bookAdapter = BookAdapter()

            nonfictionViewModel.nonfiction.observe(viewLifecycleOwner, { nonfiction ->
                if (nonfiction != null) {
                    when (nonfiction) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            bookAdapter.setData(nonfiction.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                nonfiction.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            setupRecyclerView()
            onFictionSelected()
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvNonfiction) {
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