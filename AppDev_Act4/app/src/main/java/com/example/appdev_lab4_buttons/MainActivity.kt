package com.example.appdev_lab4_buttons

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.appdev_lab4_buttons.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var LightOn = false

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.Btn1?.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "THE RED BUTTON IS PRESSED.",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding?.Btn2?.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "THE YELLOW BUTTON IS PRESSED.",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding?.Btn3?.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "THE GREEN BUTTON IS PRESSED.",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding?.RB1?.setOnClickListener { view -> ORBC(view) }
        binding?.RB2?.setOnClickListener { view -> ORBC(view) }
        binding?.RB3?.setOnClickListener { view -> ORBC(view) }
        binding?.Btn4?.setOnClickListener { showLightDialog() }
    }

    private fun ORBC(view: View) {
        var message = ""
        val radioGroup = binding?.RG1

        when (radioGroup?.checkedRadioButtonId) {
            R.id.RB1 -> {
                message = "WROOOOOONG! THE ADOLF BABY IS NOT CORRECT!"
            }
            R.id.RB2 -> {
                message = "WROOOOOONG! THE ALBERT ESTETIK IS NOT CORRECT!"
            }
            R.id.RB3 -> {
                message = "YES, CORRECT, ADOLF HITLER IS CORRECT! SHEESH KADONG!"
            }
            else -> {
                message = "Please select an option"
            }
        }

        showSnackbar(message)
    }

    private fun showSnackbar(message: String) {
        Log.d("Snackbar", "Showing Snackbar with message: $message")
        val snackBuilder = Snackbar.make(binding?.root!!, message, Snackbar.LENGTH_INDEFINITE)
        snackBuilder.setAction("CLOSE") { snackBuilder.dismiss() }
        snackBuilder.show()
    }

    private fun showLightDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("WANT TO YOU WANT TO BULB?")
        builder.setPositiveButton(
            "TURN ON THE BULB"
        ) { dialog: DialogInterface?, which: Int ->
            if (!LightOn) {
                binding?.ImgSet?.setImageResource(R.drawable.baby)
                LightOn = true
            } else {
                Toast.makeText(this, "THE BULB IS ON!", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton(
            "TURN OFF THE BULB"
        ) { dialog: DialogInterface?, which: Int ->
            if (LightOn) {
                binding?.ImgSet?.setImageResource(R.drawable.heart)
                LightOn = false
            } else {
                Toast.makeText(this, "THE BULB IS OFF!", Toast.LENGTH_SHORT).show()
            }
        }
        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null // Avoid potential memory leaks
    }
}
