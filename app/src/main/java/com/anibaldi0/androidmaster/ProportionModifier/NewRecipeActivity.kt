package com.anibaldi0.androidmaster.ProportionModifier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.anibaldi0.androidmaster.R

class NewRecipeActivity : AppCompatActivity() {

    private lateinit var imageViewButtonArrowBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)

        val textViewTitleTotalNewVolume = findViewById<TextView>(R.id.textViewTotalNewVolume)
        val finalNewVolume: String = intent.extras?.getString("NEW_VOLUME_ROUND").orEmpty()
        textViewTitleTotalNewVolume.text = "$finalNewVolume ml"

        initComponent()
        initListeners()

    }

    private fun initListeners() {
        imageViewButtonArrowBack.setOnClickListener {
            val intent = Intent(this, NewCakePanActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initComponent() {
        imageViewButtonArrowBack = findViewById(R.id.imageViewButtonArrowBack)
    }
}

