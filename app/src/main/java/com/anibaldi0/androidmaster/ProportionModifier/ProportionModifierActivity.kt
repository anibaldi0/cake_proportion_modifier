package com.anibaldi0.androidmaster.ProportionModifier

import android.hardware.camera2.params.MeteringRectangle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    private fun initListeners() {
        cardViewRectangle.setOnClickListener {
            changeMoldForm()
            setMoldFormColor()
        }
        cardViewRound.setOnClickListener {
            changeMoldForm()
            setMoldFormColor()
        }
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
            R.color.background_second_component_selected
        }else{
            R.color.background_second_component
        }
        return  ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setMoldFormColor()
    }


}