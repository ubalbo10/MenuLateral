package com.consiti.menulateral.ui.home

import android.content.Context
import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.consiti.menulateral.MainActivity
import com.consiti.menulateral.R
import com.consiti.menulateral.WebViewClientImpl


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val fb=root.findViewById<WebView>(R.id.fb)

        fb.loadUrl("https://m.facebook.com/login/?locale2=es_LA")
        //fb.settings.javaScriptEnabled=true
        fb.settings.javaScriptEnabled = true
        fb.settings.loadWithOverviewMode = true
        fb.settings.useWideViewPort = true
        fb.settings.domStorageEnabled = true
        fb.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
        fb.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {

            }
        }


        homeViewModel.text.observe(this, Observer {
            //fb.loadUrl("www.facebook.com")
        })
        return root
    }

}


