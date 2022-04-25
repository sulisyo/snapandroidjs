package com.aku.snapandroidjs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aku.snapandroidjs.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        val snapToken = arguments?.get("snap_token")
        val webview = binding.webview
        webview.settings.javaScriptEnabled = true
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
        webview.settings.setSupportMultipleWindows(true)
        webview.settings.domStorageEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.settings.builtInZoomControls = true
        webview.settings.displayZoomControls = false
        webview.settings.setSupportZoom(true);
        webview.settings.setDefaultTextEncodingName("utf-8");
        webview.webChromeClient = WebChromeClient()
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                webview.loadUrl("javascript:snap.pay('$snapToken')")
            }
        }
        webview.loadUrl("file:///android_asset/web.html");
//        webview.evaluateJavascript("snap:pay($snapToken)", null)
//        binding.webview.loadUrl("javascript:snap.pay('$snapToken')")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}