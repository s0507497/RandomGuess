package com.student.randomguess

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var number = (1..100000).random()

        Toast.makeText (this, "Number to remember: $number", Toast.LENGTH_LONG).show()
        val txtGuess = findViewById<EditText>(R.id.txtGuess)
        val btnGuess = findViewById<Button>(R.id.btnGuess)
        val btnToast = findViewById<Button>(R.id.btnToast)

        btnGuess.setOnClickListener {
            if (txtGuess.text.toString() != ""){
                val guess = txtGuess.text.toString().toInt()
                if (number == guess) {
                    Toast.makeText(this, "Correct, Great Job Remembering.", Toast.LENGTH_LONG).show()
                    txtGuess.setText("")
                } else {
                    Toast.makeText(this, "Incorrect, off by ${difference(number, guess)}", Toast.LENGTH_LONG).show()
                    txtGuess.setText("")
                }
                hideKeyboard()
            }
        }
        btnToast.setOnClickListener {
            number = (1..100000).random()
            println(number)
            Toast.makeText(this, "Number to remember: $number", Toast.LENGTH_LONG).show()
        }


    }

    fun hideKeyboard(){
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            //Todo: Handle Exception
        }
    }

    fun difference(num1 : Int, num2 : Int) = (num1 - num2).absoluteValue
}
