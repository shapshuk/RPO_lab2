package com.example.lab2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.lab2.R
import com.example.lab2.databinding.FragmentWebViewBinding

class WebViewFragment(private val url: String) : Fragment() {

    private val binding : FragmentWebViewBinding by lazy {
        FragmentWebViewBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)

        val webView = view.findViewById<WebView>(binding.webView.id)
        webView.loadUrl(url)
        return view
    }
}