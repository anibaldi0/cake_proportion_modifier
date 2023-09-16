package com.anibaldi0.androidmaster.ProportionModifier

import android.hardware.camera2.params.MeteringRectangle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.anibaldi0.androidmaster.R

class ProportionModifierActivity : AppCompatActivity() {

    private var isRectangleSelected: Boolean = true
    private var isRoundSelected: Boolean = false

    private var originalHeight: Int = 1
    private var originalWidth: Int = 1
    private var originalLength: Int =  1
    private var originalDiameter: Int = 1

    private  var originalQuantityMolds: Int = 1

    private lateinit var cardViewRectangle: CardView
    private lateinit var cardViewRound: CardView
    private lateinit var rectangleImage: ImageView
    private lateinit var rectangleText: TextView
    private lateinit var roundImage: ImageView
    private lateinit var roundText: TextView
    private lateinit var textViewCakePan: TextView
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
        textViewMesureDiameter = findViewById(R.id.textViewMesureDiameter)
        editTextNumberDiameter = findViewById(R.id.editTextNumberDiameter)
        textViewMesureWidth = findViewById(R.id.textViewMesureWidth)
        editTextNumberWidth = findViewById(R.id.editTextNumberWidth)
        textViewMesureLength = findViewById(R.id.textViewMesureLength)
        editTextNumberLength = findViewById(R.id.editTextNumberLength)

        cardViewButtonNext = findViewById((R.id.cardViewButtonNext))
    }

    private fun initListeners() {
        cardViewRectangle.setOnClickListener {
            changeMoldForm()
            setMoldFormColor()
            textViewCakePan.text = "Rectangular"
            textViewMesureDiameter.setTextColor(ContextCompat.getColor(this, R.color.gray))
            textViewMesureWidth.setTextColor(ContextCompat.getColor(this, R.color.black))
            textViewMesureLength.setTextColor(ContextCompat.getColor(this, R.color.black))
            editTextNumberDiameter.isEnabled = false
            editTextNumberDiameter.text.clear()
            editTextNumberWidth.isEnabled = true
            editTextNumberLength.isEnabled = true
        }
        cardViewRound.setOnClickListener {
            changeMoldForm()
            setMoldFormColor()
            textViewCakePan.text = "Round"
            textViewMesureDiameter.setTextColor(ContextCompat.getColor(this, R.color.black))
            textViewMesureWidth.setTextColor(ContextCompat.getColor(this, R.color.gray))
            textViewMesureLength.setTextColor(ContextCompat.getColor(this, R.color.gray))
            editTextNumberWidth.isEnabled = false
            editTextNumberLength.isEnabled = false
            editTextNumberWidth.text.clear()
            editTextNumberLength.text.clear()
            editTextNumberDiameter.isEnabled = true


        }
        cardViewButtonNext.setOnClickListener{
            volumeCalculate()
        }
    }



    private fun volumeCalculate() {
        TODO("Not yet implemented")
    }

    private fun changeMoldForm(){
        isRectangleSelected = !isRectangleSelected
        isRoundSelected = !isRoundSelected
    }

    private fun setMoldFormColor() {
        cardViewRectangle.setCardBackgroundColor(getBackgroundColor(isRectangleSelected))
        cardViewRound.setCardBackgroundColor(getBackgroundColor(isRoundSelected))
        rectangleImage.setColorFilter(getImageColor(isRectangleSelected))
        rectangleText.setTextColor(getTextColor(isRectangleSelected))
        roundImage.setColorFilter(getImageColor(isRoundSelected))
        roundText.setTextColor(getTextColor(isRoundSelected))
    }

    private fun getImageColor(isSelectedComponent: Boolean): Int{
        val colorImageReference = if(isSelectedComponent){
            R.color.white
        }else{
            R.color.gray
        }
        return ContextCompat.getColor(this, colorImageReference)
    }

    private fun getTextColor(isSelectedComponent: Boolean): Int{
        val colorTextReference = if(isSelectedComponent){
            R.color.white
        }else{
            R.color.gray
        }
        return ContextCompat.getColor(this, colorTextReference)
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean): Int {
        val colorReference = if(isSelectedComponent){
            R.color.teal_200
        }else{
            R.color.teal_700
        }
        return  ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setMoldFormColor()
    }


}