package com.dicoding.thenewyorktimespp.ui.nonfiction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.thenewyorktimespp.databinding.FragmentNonfictionBinding

class NonfictionFragment : Fragment() {

    private lateinit var nonfictionViewModel: NonfictionViewModel
    private var _binding: FragmentNonfictionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nonfictionViewModel =
            ViewModelProvider(this).get(NonfictionViewModel::class.java)

        _binding = FragmentNonfictionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        nonfictionViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}