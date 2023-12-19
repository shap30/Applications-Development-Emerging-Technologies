package com.example.appdev_act7_bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appdev_act7_bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var choiceGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        binding!!.maleBtn.setOnClickListener { view ->
            if (binding!!.maleBtn.isChecked) {
                choiceGender = "MR."
                binding!!.FemaleBtn.isChecked = false
            } else {
                binding!!.maleBtn.isChecked = true
            }
        }

        binding!!.FemaleBtn.setOnClickListener { view ->
            if (binding!!.FemaleBtn.isChecked) {
                choiceGender = "MRS."
                binding!!.maleBtn.isChecked = false
            } else {
                binding!!.FemaleBtn.isChecked = true
            }
        }


        binding!!.subBtn.setOnClickListener { view ->
            val firstName = binding!!.editFirst.text.toString()
            val middleInitial = binding!!.editMI.text.toString()
            val lastName = binding!!.editLast.text.toString()
            val heightStr = binding!!.editCM.text.toString()
            val weightStr = binding!!.editKG.text.toString()

            if (!firstName.isEmpty() && !lastName.isEmpty() && !middleInitial.isEmpty() &&
                !heightStr.isEmpty() && !weightStr.isEmpty()
            ) {
                val height = heightStr.toFloat()
                val weight = weightStr.toFloat()

                if (choiceGender == null) {
                    Toast.makeText(
                        applicationContext,
                        "SELECT GENDER, THANK YOU!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                val compbmi = computeBMI(height, weight)

                val intent: Intent = if (compbmi < 18.5) {
                    Intent(this, UnderweightActivity::class.java)
                } else if (compbmi < 25) {
                    Intent(this, HealthyActivity::class.java)
                } else if (compbmi < 30) {
                    Intent(this, OverweightActivity::class.java)
                } else {
                    Intent(this, ObeseActivity::class.java)
                }


                intent.putExtra("FIRSTNAME", firstName)
                intent.putExtra("MIDDLE INITIAL", middleInitial)
                intent.putExtra("LASTNAME", lastName)
                intent.putExtra("GENDER", choiceGender)
                intent.putExtra("BMI", compbmi)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(
                    applicationContext,
                    "PLEASE INSERT CORRECTLY!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun computeBMI(height: Float, weight: Float): Float {
        val heightInMeters = height / 100
        return if (heightInMeters > 0) weight / (heightInMeters * heightInMeters) else 0.0f
    }
}