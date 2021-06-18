package com.dicoding.thenewyorktimespp.fiction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.thenewyorktimespp.databinding.FragmentFictionBinding

class FictionFragment : Fragment() {

    private lateinit var fictionViewModel: FictionViewModel
    private var _binding: FragmentFictionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fictionViewModel =
            ViewModelProvider(this).get(FictionViewModel::class.java)

        _binding = FragmentFictionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        fictionViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}