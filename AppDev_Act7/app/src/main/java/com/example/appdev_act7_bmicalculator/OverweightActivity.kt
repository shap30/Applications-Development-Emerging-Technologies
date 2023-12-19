package com.example.appdev_act7_bmicalculator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OverweightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.overweight)

        val intent = intent
        val firstName = intent.getStringExtra("FIRSTNAME")
        val lastName = intent.getStringExtra("LASTNAME")
        val middleInitial = intent.getStringExtra("MIDDLE INITIAL")
        val gender = intent.getStringExtra("GENDER")
        val bmi = intent.getFloatExtra("BMI", 0.0f)

        val nameView = findViewById<TextView>(R.id.nameView)
        val genderView = findViewById<TextView>(R.id.genderView)
        val BMIView = findViewById<TextView>(R.id.BMIView)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val fullName = "$firstName $middleInitial $lastName"
        val backButton = findViewById<Button>(R.id.btnBack)

        backButton.setOnClickListener {
            // Create an Intent to go back to the main page
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        nameView.text = fullName
        genderView.text = gender
        BMIView.text = String.format("%.2f", bmi)

        if ("MR" == gender) {
            imageView.setImageResource(R.drawable.male)
        } else if ("MRS." == gender) {
            imageView.setImageResource(R.drawable.female)
        }
    }

    fun goToMainActivity(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}