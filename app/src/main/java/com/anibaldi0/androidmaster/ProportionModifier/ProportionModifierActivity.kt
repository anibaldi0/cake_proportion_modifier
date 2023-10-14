package com.anibaldi0.androidmaster.ProportionModifier

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.anibaldi0.androidmaster.R
import android.content.SharedPreferences

class ProportionModifierActivity : AppCompatActivity() {

    private var isRectangleSelected: Boolean = true
    private var isRoundSelected: Boolean = false
    private var currentVolume: String = ""

    private lateinit var cardViewRectangle: CardView
    private lateinit var cardViewRound: CardView
    private lateinit var rectangleImage: ImageView
    private lateinit var rectangleText: TextView
    private lateinit var roundImage: ImageView
    private lateinit var roundText: TextView
    private lateinit var editTextNumberCakePans: TextView
    private lateinit var textViewCakePan: TextView
    private lateinit var textViewMesureHeight: TextView
    private lateinit var editTextNumberHeight: EditText
    private lateinit var textViewMesureDiameter: TextView
    private lateinit var editTextNumberDiameter: EditText
    private lateinit var textViewMesureWidth: TextView
    private lateinit var editTextNumberWidth: EditText
    private lateinit var textViewMesureLength: TextView
    private lateinit var editTextNumberLength: EditText

    private lateinit var cardViewButtonNext: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proportion_modifier)

        initComponents()
        initListeners()
        initUI()

        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        val  savedHeight = sharedPreferences.getString("height", "")
        val  savedWidth = sharedPreferences.getString("width", "")
        val  savedLength = sharedPreferences.getString("length", "")
        val  savedDiameter = sharedPreferences.getString("diameter", "")
        val  savedQuantityCakePans = sharedPreferences.getString("quantity_cake_pans", "")

        editTextNumberHeight.setText(savedHeight)
        editTextNumberWidth.setText(savedWidth)
        editTextNumberLength.setText(savedLength)
        editTextNumberDiameter.setText(savedDiameter)
        editTextNumberCakePans.setText(savedQuantityCakePans)

//        isRectangleSelected = sharedPreferences.getBoolean("isRectangleSelected", true)
//        isRoundSelected = sharedPreferences.getBoolean("isRoundSelected", true)

        if (isRectangleSelected) {
            cardViewRectangle.setCardBackgroundColor(getBackgroundColorSelected(true))
            cardViewRound.setCardBackgroundColor(getBackgroundColorSelected(false))
        }else{
            cardViewRound.setCardBackgroundColor(getBackgroundColorSelected(true))
            cardViewRectangle.setCardBackgroundColor(getBackgroundColorSelected(false))
        }

    }


    private fun initComponents() {
        cardViewRectangle = findViewById(R.id.cardViewRectangle)
        cardViewRound = findViewById(R.id.cardViewRound)
        rectangleImage = findViewById(R.id.rectangleImage)
        rectangleText = findViewById(R.id.rectangleText)
        roundImage = findViewById(R.id.roundImage)
        roundText = findViewById(R.id.roundText)
        textViewCakePan = findViewById(R.id.textViewCakePan)
        editTextNumberCakePans = findViewById(R.id.editTextNumberCakePans)
        textViewMesureDiameter = findViewById(R.id.textViewMesureDiameter)
        editTextNumberDiameter = findViewById(R.id.editTextNumberDiameter)
        textViewMesureWidth = findViewById(R.id.textViewMesureWidth)
        editTextNumberWidth = findViewById(R.id.editTextNumberWidth)
        textViewMesureLength = findViewById(R.id.textViewMesureLength)
        editTextNumberLength = findViewById(R.id.editTextNumberLength)
        textViewMesureHeight = findViewById(R.id.textViewMesureHeight)
        editTextNumberHeight = findViewById(R.id.editTextNumberHeight)

        cardViewButtonNext = findViewById((R.id.cardViewButtonNext))
    }

    private fun initListeners() {

        cardViewRectangle.setOnClickListener {
            isRectangleSelected = true
            isRoundSelected = false
            rectangleSelected()
            setCakePanFormColor()

        }
        cardViewRound.setOnClickListener {
            isRoundSelected = true
            isRectangleSelected = false
            roundSelected()
            setCakePanFormColor()

        }
        cardViewButtonNext.setOnClickListener {
            val intent = Intent(this, CurrentRecipeActivity::class.java)
            if (isRoundSelected){
                currentVolume = currentVolumeRound().toString()
                Log.i("NibalDev", "Boton Pulsado para Round: $currentVolume")
            } else {
                currentVolume = currentVolumeRectangle().toString()
                Log.i("NibalDev", "Boton Pulsado para Rectangular $currentVolume")
            }

            val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("height", editTextNumberHeight.text.toString())
            editor.putString("width", editTextNumberWidth.text.toString())
            editor.putString("length", editTextNumberLength.text.toString())
            editor.putString("diameter", editTextNumberDiameter.text.toString())
            editor.putString("quantity_cake_pans", editTextNumberCakePans.text.toString())

            editor.putBoolean("isRectangleSelected", isRectangleSelected)
            editor.putBoolean("isRoundSelected", isRoundSelected)

            editor.apply()

            intent.putExtra("CURRENT_VOLUME", currentVolume)
            startActivity(intent)

        }
    }

    private fun roundSelected() {
        isRoundSelected = true
        isRectangleSelected = false
        textViewCakePan.text = "Round"
        textViewMesureDiameter.setTextColor(ContextCompat.getColor(this, R.color.black))
        textViewMesureWidth.setTextColor(ContextCompat.getColor(this, R.color.gray_600))
        textViewMesureLength.setTextColor(ContextCompat.getColor(this, R.color.gray_600))
        editTextNumberWidth.isEnabled = false
        editTextNumberLength.isEnabled = false
        editTextNumberWidth.text.clear()
        editTextNumberLength.text.clear()
        editTextNumberDiameter.isEnabled = true
        editTextNumberHeight.setHintTextColor(ContextCompat.getColor(this, R.color.gray_300))
        editTextNumberDiameter.setHintTextColor(ContextCompat.getColor(this, R.color.gray_300))
        editTextNumberCakePans.setHintTextColor(ContextCompat.getColor(this, R.color.gray_300))
        editTextNumberWidth.setHintTextColor(ContextCompat.getColor(this, R.color.gray_600))
        editTextNumberLength.setHintTextColor(ContextCompat.getColor(this, R.color.gray_600))

    }

    private fun rectangleSelected() {
        isRoundSelected = true
        isRoundSelected = false
        textViewCakePan.text = "Rectangular"
        textViewMesureDiameter.setTextColor(ContextCompat.getColor(this, R.color.gray_600))
        textViewMesureWidth.setTextColor(ContextCompat.getColor(this, R.color.black))
        textViewMesureLength.setTextColor(ContextCompat.getColor(this, R.color.black))
        editTextNumberDiameter.isEnabled = false
        editTextNumberDiameter.text.clear()
        editTextNumberWidth.isEnabled = true
        editTextNumberLength.isEnabled = true
        textViewMesureDiameter.setTextColor(ContextCompat.getColor(this, R.color.gray_600))
        editTextNumberHeight.setHintTextColor(ContextCompat.getColor(this, R.color.gray_300))
        editTextNumberDiameter.setHintTextColor(ContextCompat.getColor(this, R.color.gray_600))
        editTextNumberCakePans.setHintTextColor(ContextCompat.getColor(this, R.color.gray_300))
        editTextNumberWidth.setHintTextColor(ContextCompat.getColor(this, R.color.gray_300))
        editTextNumberLength.setHintTextColor(ContextCompat.getColor(this, R.color.gray_300))

    }

    private fun currentVolumeRound(): Double {
        val currentDiameter = editTextNumberDiameter.text.toString().toDoubleOrNull() ?: 1.0
        val currentHeight = editTextNumberHeight.text.toString().toDoubleOrNull() ?: 1.0
        val currentNumberCakePans = editTextNumberCakePans.text.toString().toDoubleOrNull()  ?: 1.0
        val currentVolumeRound = (currentHeight * ((currentDiameter / 2) * (currentDiameter / 2))) * 3.14 * currentNumberCakePans
        return currentVolumeRound
    }

    private fun currentVolumeRectangle(): Double {
        val currentHeight = editTextNumberHeight.text.toString().toDoubleOrNull() ?: 1.0
        val currentWidth = editTextNumberWidth.text.toString().toDoubleOrNull() ?: 1.0
        val currentLength = editTextNumberLength.text.toString().toDoubleOrNull() ?: 1.0
        val currentNumberCakePans = editTextNumberCakePans.text.toString().toDoubleOrNull() ?: 1.0
        val currentVolumeRectangle = currentHeight * currentWidth * currentLength * currentNumberCakePans
        return currentVolumeRectangle
    }

    private fun setCakePanFormColor() {
        cardViewRectangle.setCardBackgroundColor(getBackgroundColorSelected(isRectangleSelected))
        cardViewRound.setCardBackgroundColor(getBackgroundColorSelected(isRoundSelected))
        rectangleImage.setColorFilter(getImageColorSelected(isRectangleSelected))
        roundImage.setColorFilter(getImageColorSelected(isRoundSelected))
        rectangleText.setTextColor(getTextColorSelected(isRectangleSelected))
        roundText.setTextColor(getTextColorSelected(isRoundSelected))

        if (isRectangleSelected) {
            // Configurar el fondo del cardViewRectangle como presionado
            cardViewRectangle.setCardBackgroundColor(getBackgroundColorSelected(true))
            editTextNumberDiameter.setHintTextColor(ContextCompat.getColor(this, R.color.gray_600))
            textViewMesureDiameter.setTextColor(ContextCompat.getColor(this, R.color.gray_600))
        } else {
            // Configurar el fondo del cardViewRectangle como no presionado
            cardViewRectangle.setCardBackgroundColor(getBackgroundColorSelected(false))
            // Configurar otros elementos según el estado
            //...
        }
    }

    private fun getImageColorSelected(isSelectedComponent: Boolean): Int {
        return ContextCompat.getColor(this, if (isSelectedComponent) R.color.white else R.color.gray_600)
    }

    private fun getTextColorSelected(isSelectedComponent: Boolean): Int {
        return ContextCompat.getColor(this, if (isSelectedComponent) R.color.white else R.color.gray_600)
    }

    private fun getBackgroundColorSelected(isSelectedComponent: Boolean): Int {
        return ContextCompat.getColor(this, if (isSelectedComponent) R.color.teal_200 else R.color.teal_700)
    }

    private fun initUI() {
        setCakePanFormColor()
    }


}