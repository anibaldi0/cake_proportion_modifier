package com.anibaldi0.androidmaster.ProportionModifier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.anibaldi0.androidmaster.R

class OriginalRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_original_recipe)

        val textViewTotalCurrentVolume = findViewById<TextView>(R.id.textViewTotalCurrentVolume)
        val currentVolume: String = intent.extras?.getString("VOLUME_ROUND").orEmpty()
        textViewTotalCurrentVolume.text = "$currentVolume ml"

    }
}