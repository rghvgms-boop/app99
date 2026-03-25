package com.example.photowidget

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Simple UI to show instructions
        val textView = TextView(this).apply {
            text = "Photo Widget App\n\nTo use the widget:\n1. Go to your home screen.\n2. Long press on an empty space.\n3. Select 'Widgets'.\n4. Find 'Photo Widget' and drag it to your home screen.\n\nThe image will refresh every hour automatically."
            textSize = 18f
            setPadding(32)
        }
        setContentView(textView)
    }
}
