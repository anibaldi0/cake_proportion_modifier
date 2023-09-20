package com.anibaldi0.androidmaster.ProportionModifier

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

class NewCakePanActivity : AppCompatActivity() {

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
    private lateinit var imageViewButtonArrowBack: ImageView

    private lateinit var cardViewButtonNext: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_cake_pan)

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
        imageViewButtonArrowBack = findViewById(R.id.imageViewButtonArrowBack)

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
            val intent = Intent(this, CurrentRecipeActivity::class.java)
            if (isRoundSelected){
                val finalNewVolumeRound = newVolumeRound().toString()
                intent.putExtra("NEW_VOLUME_ROUND", finalNewVolumeRound)
                startActivity(intent)
                Log.i("NibalDev", "Boton Pulsado para Round: $finalNewVolumeRound")
            } else {
                val finalNewVolumeRectangle = newVolumeRectangle().toString()
                intent.putExtra("NEW_VOLUME_ROUND", finalNewVolumeRectangle)
                startActivity(intent)
                Log.i("NibalDev", "Boton Pulsado para Rectangular $finalNewVolumeRectangle")
            }

        }

        imageViewButtonArrowBack.setOnClickListener {
            val intent = Intent(this, ProportionModifierActivity::class.java)
            startActivity(intent)
        }

    }

    private fun newVolumeRound(): Double {
        val newDiameter = editTextNumberDiameter.text.toString().toDoubleOrNull() ?: 1.0
        val newHeight = editTextNumberHeight.text.toString().toDoubleOrNull() ?: 1.0
        val newNumberCakePans = editTextNumberCakePans.text.toString().toDoubleOrNull()  ?: 1.0
        val newVolumeRound = (newHeight * ((newDiameter / 2) * (newDiameter / 2))) * 3.14 * newNumberCakePans
        return newVolumeRound
    }

    private fun newVolumeRectangle(): Double {
        val newHeight = editTextNumberHeight.text.toString().toDoubleOrNull() ?: 1.0
        val newWidth = editTextNumberWidth.text.toString().toDoubleOrNull() ?: 1.0
        val newLength = editTextNumberLength.text.toString().toDoubleOrNull() ?: 1.0
        val newNumberCakePans = editTextNumberCakePans.text.toString().toDoubleOrNull() ?: 1.0
        val newVolumeRectangle = newHeight * newWidth * newLength * newNumberCakePans
        return newVolumeRectangle
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