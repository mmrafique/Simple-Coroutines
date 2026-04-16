package com.example.simplecoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var statusText: TextView
    private lateinit var downloadBtn: Button
    private var isDownloading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        downloadBtn = findViewById(R.id.downloadBtn)

        downloadBtn.setOnClickListener {
            if (!isDownloading) {
                startDownload()
            }
        }
    }

    private fun startDownload() {
        isDownloading = true
        downloadBtn.isEnabled = false
        statusText.text = "Descàrregant..."

        lifecycleScope.launch {
            try {
                val randomDelay = Random.nextLong(2000, 5000)

                kotlinx.coroutines.withContext(Dispatchers.IO) {
                    delay(randomDelay)
                }

                statusText.text = "Descarregat correctament!"
                downloadBtn.isEnabled = true
                isDownloading = false
            } catch (e: Exception) {
                statusText.text = "Error en la descàrrega"
                downloadBtn.isEnabled = true
                isDownloading = false
            }
        }
    }
}
