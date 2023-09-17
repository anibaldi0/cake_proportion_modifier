package com.anibaldi0.androidmaster.ProportionModifier

import android.content.Intent
import android.hardware.camera2.params.MeteringRectangle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.anibaldi0.androidmaster.R

class ProportionModifierActivity : AppCompatActivity() {

    private var isRectangleSelected: Boolean = true
    private var isRoundSelected: Boolean = false

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
            changeCakePan()
            setCakePanFormColor()
            textViewCakePan.text = "Rectangular"
            textViewMesureDiameter.setTextColor(ContextCompat.getColor(this, R.color.gray_300))
            textViewMesureWidth.setTextColor(ContextCompat.getColor(this, R.color.black))
            textViewMesureLength.setTextColor(ContextCompat.getColor(this, R.color.black))
            editTextNumberDiameter.isEnabled = false
            editTextNumberDiameter.text.clear()
            editTextNumberWidth.isEnabled = true
            editTextNumberLength.isEnabled = true

        }
        cardViewRound.setOnClickListener {
            changeCakePan()
            setCakePanFormColor()
            textViewCakePan.text = "Round"
            textViewMesureDiameter.setTextColor(ContextCompat.getColor(this, R.color.black))
            textViewMesureWidth.setTextColor(ContextCompat.getColor(this, R.color.gray_300))
            textViewMesureLength.setTextColor(ContextCompat.getColor(this, R.color.gray_300))
            editTextNumberWidth.isEnabled = false
            editTextNumberLength.isEnabled = false
            editTextNumberWidth.text.clear()
            editTextNumberLength.text.clear()
            editTextNumberDiameter.isEnabled = true

        }
        cardViewButtonNext.setOnClickListener {
            val intent = Intent(this, OriginalRecipeActivity::class.java)
            if (isRoundSelected){
                val volumeRound = currentVolumeRound().toString()
                intent.putExtra("VOLUME_ROUND", volumeRound)
                startActivity(intent)
                Log.i("NibalDev", "Boton Pulsado para Round: $volumeRound")
            } else {
                val volumeRectangle = currentVolumeRectangle().toString()
                intent.putExtra("VOLUME_ROUND", volumeRectangle)
                startActivity(intent)
                Log.i("NibalDev", "Boton Pulsado para Rectangular $volumeRectangle")
            }

        }
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

    private fun calculateVolumeRound() {
        Log.i("NibalDev", "Boton Pulsado ${currentVolumeRound()}")
    }


    private fun changeCakePan() {
        isRectangleSelected = !isRectangleSelected
        isRoundSelected = !isRoundSelected
    }

    private fun setCakePanFormColor() {
        cardViewRectangle.setCardBackgroundColor(getBackgroundColor(isRectangleSelected))
        cardViewRound.setCardBackgroundColor(getBackgroundColor(isRoundSelected))
        rectangleImage.setColorFilter(getImageColor(isRectangleSelected))
        rectangleText.setTextColor(getTextColor(isRectangleSelected))
        roundImage.setColorFilter(getImageColor(isRoundSelected))
        roundText.setTextColor(getTextColor(isRoundSelected))
    }

    private fun getImageColor(isSelectedComponent: Boolean): Int {
        val colorImageReference = if (isSelectedComponent) {
            R.color.white
        } else {
            R.color.gray_600
        }
        return ContextCompat.getColor(this, colorImageReference)
    }

    private fun getTextColor(isSelectedComponent: Boolean): Int {
        val colorTextReference = if (isSelectedComponent) {
            R.color.white
        } else {
            R.color.gray_600
        }
        return ContextCompat.getColor(this, colorTextReference)
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.teal_200
        } else {
            R.color.teal_700
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setCakePanFormColor()
    }


}