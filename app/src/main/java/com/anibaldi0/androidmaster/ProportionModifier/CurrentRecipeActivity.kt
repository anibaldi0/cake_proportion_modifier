package com.anibaldi0.androidmaster.ProportionModifier

import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.anibaldi0.androidmaster.R

class CurrentRecipeActivity : AppCompatActivity() {

    private lateinit var imageViewButtonArrowBack: ImageView
    private lateinit var cardViewButtonCalculate: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_recipe)



        val textViewTotalCurrentVolume = findViewById<TextView>(R.id.textViewTotalCurrentVolume)
        val currentVolume: String = intent.extras?.getString("CURRENT_VOLUME").orEmpty()
        textViewTotalCurrentVolume.text = "$currentVolume ml"

        val autoCompleteTextView = arrayOf(
            R.id.ingredient01,
            R.id.ingredient02,
            R.id.ingredient03,
            R.id.ingredient04,
            R.id.ingredient05,
            R.id.ingredient06,
            R.id.ingredient07,
            R.id.ingredient08,
            R.id.ingredient09
        )
        for (i in autoCompleteTextView.indices) {
            val autoCompleteTextView = findViewById<AutoCompleteTextView>(autoCompleteTextView[i])
            val suggestions = when (i) {
                0 -> resources.getStringArray(R.array.suggestions)
                1 -> resources.getStringArray(R.array.suggestions)
                2 -> resources.getStringArray(R.array.suggestions)
                3 -> resources.getStringArray(R.array.suggestions)
                4 -> resources.getStringArray(R.array.suggestions)
                5 -> resources.getStringArray(R.array.suggestions)
                6 -> resources.getStringArray(R.array.suggestions)
                7 -> resources.getStringArray(R.array.suggestions)
                8 -> resources.getStringArray(R.array.suggestions)
                else -> emptyArray() // Manejar cualquier otro caso
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions)
            autoCompleteTextView.setAdapter(adapter)
        }

        initComponent()
        initListeners()

    }

    private fun initListeners() {
        imageViewButtonArrowBack.setOnClickListener {
            val intent = Intent(this, ProportionModifierActivity::class.java)
            startActivity(intent)
        }

        cardViewButtonCalculate.setOnClickListener {
            val intent = Intent(this, NewCakePanActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initComponent() {
        imageViewButtonArrowBack = findViewById(R.id.imageViewButtonArrowBack)
        cardViewButtonCalculate = findViewById(R.id.cardViewButtonCalculate)

    }

}

class MySharedPreferences (context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)


    fun saveCurrentVolume (volume: String) {
        val editor = sharedPreferences.edit()
        editor.putString("currentVolume", volume)
        editor.apply()
    }

    fun getCurrentVolume (): String? {
        return sharedPreferences.getString("currentVolume", "")
    }
}