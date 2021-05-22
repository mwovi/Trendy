package com.agesadev.trendymobile

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayWebView()
    }

    private fun displayWebView() {
        if (isNetworkAvailbale()) {

            trendy_web.apply {
                this.settings.loadsImagesAutomatically = true
                this.settings.javaScriptEnabled = true
                trendy_web.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                trendy_web.webViewClient = WebViewClient()
                trendy_web.loadUrl("https://trendymobile.co.ke")
            }
        } else {
            image_no_net.visibility = View.VISIBLE
            Toast.makeText(applicationContext, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && trendy_web.canGoBack()) {
            trendy_web.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun isNetworkAvailbale(): Boolean {
        val conManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetInfo = conManager.activeNetworkInfo
        return internetInfo != null && internetInfo.isConnected
    }

}