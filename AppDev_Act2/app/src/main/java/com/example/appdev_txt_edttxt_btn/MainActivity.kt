package com.example.appdev_txt_edttxt_btn

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appdev_txt_edttxt_btn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private var greetings = "HELLO!" // AUTOMATIC OUTPUT IF CLICK SEND TO TEXTVIEW.

    override fun onCreate(savedInstanceState: Bundle?)

    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root

        setContentView(view)

        binding!!.Btn1.setOnClickListener {

            // VARIABLES

            val FN = binding!!.EdtP1.text.toString()
            val LN = binding!!.EdtP2.text.toString()
            val MI = binding!!.EdtP3.text.toString()

            // OPTION, IF PLAINTEXT WITH NO INSERT TEXT PROCEED TO CONTEXT POP UP

            if (FN.isEmpty() || LN.isEmpty() || MI.isEmpty()) {
                Toast.makeText(
                    this@MainActivity,
                    "PLEASE FILL THE BLANK, THANK YOU!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else
            {

            // BINDING FOR PLAIN-TEXT SEN IT TO TEXTVIEW.


                binding!!.EdtTxt4.text = "$greetings\n $LN $FN $MI"
                binding!!.EdtP1.text.clear()
                binding!!.EdtP2.text.clear()
                binding!!.EdtP3.text.clear()
            }
        }

        // BINDING THE BUTTON TO CLICKER FOR CHANGING COLOR.

        binding!!.Btn2.setOnClickListener { changeColor(Color.RED) }
        binding!!.Btn3.setOnClickListener { changeColor(Color.GREEN) }
        binding!!.Btn4.setOnClickListener { changeColor(Color.YELLOW) }

        // BINDING CLICKER FOR CHANGE THE SIZE.

        binding!!.Btn5.setOnClickListener { CTS(34f) }
        binding!!.Btn6.setOnClickListener { CTS(24f) }
        binding!!.Btn7.setOnClickListener { CTS(20f) }

        // BINDING CLICKER FOR CHANGE GREETINGS

        binding!!.Btn8.setOnClickListener { CGST("WELCOME!") }
        binding!!.Btn9.setOnClickListener { CGST("CONGRATULATIONS!") }
        binding!!.Btn10.setOnClickListener { CGST("HAVE A NICE DAY!") }
    }

    private fun CGST(prgreetings: String)

    {
        greetings = prgreetings
        val CT = binding!!.EdtTxt4.text.toString()
        if (!CT.isEmpty()) {

            val prr = CT.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

            if (prr.size > 1) {
                binding!!.EdtTxt4.text = """$greetings!    
            ${prr[1]}"""

            }
        }
    }

    private fun CTS(size: Float) {
        binding!!.EdtTxt4.textSize = size
    }

    private fun changeColor(color: Int) {
        binding!!.EdtTxt4.setTextColor(color)
    }
}