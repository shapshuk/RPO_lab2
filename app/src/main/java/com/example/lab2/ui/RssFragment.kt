package com.example.lab2.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.R
import com.example.lab2.databinding.FragmentRssBinding
import com.example.lab2.ui.mainactivity.appComponent
import javax.inject.Inject

class RssFragment : Fragment(R.layout.fragment_rss) {

    private val binding : FragmentRssBinding by lazy {
        FragmentRssBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModel: RssViewModel

    private val rvAdapter = RecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().appComponent.inject(this)

        val view = inflater.inflate(R.layout.fragment_rss, container, false)
        val recyclerView = view.findViewById<RecyclerView>(binding.recyclerView.id)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity as Context)
            adapter = rvAdapter
        }

        view.findViewById<Button>(binding.searchButton.id).setOnClickListener {
            viewModel.fetchRssData(view.findViewById<EditText>(binding.urlEditText.id).text.toString())
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.rssItems.observe(viewLifecycleOwner) {
            rvAdapter.updateRssItems(it)
        }
        viewModel.fetchingError.observe(viewLifecycleOwner) {
            handleFetchingError(it)
        }
    }

    private fun handleFetchingError(response: String) {
        Log.e("Retrofit", response)
        Toast.makeText(
            requireContext(),
            R.string.retrofit_error,
            Toast.LENGTH_LONG
        ).show()
    }
}