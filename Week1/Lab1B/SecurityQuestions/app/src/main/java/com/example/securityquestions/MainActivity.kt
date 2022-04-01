package com.example.securityquestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etSQ1 = findViewById<EditText>(R.id.etSQ1)
        val etSQ2 = findViewById<EditText>(R.id.etSQ2)
        val etSQ3 = findViewById<EditText>(R.id.etSQ3)

        var SQ1: String
        var SQ2: String
        var SQ3: String

        val tvAnswers = findViewById<TextView>(R.id.tvAnswers)

        val btnEnter = findViewById<Button>(R.id.btnEnter)
        val btnClear = findViewById<Button>(R.id.btnClear)

        btnEnter.setOnClickListener { v ->
            SQ1 = etSQ1.text.toString()
            SQ2 = etSQ2.text.toString()
            SQ3 = etSQ3.text.toString()

            tvAnswers.text = "Pet's Name: $SQ1 \nStreet: $SQ2 \nSports Team: $SQ3"

            Log.d("RESULTS_TAG", "Pet's Name: $SQ1 \nStreet: $SQ2 \nSports Team: $SQ3")
            Toast.makeText(this, "Pet's Name: $SQ1 \nStreet: $SQ2 \nSports Team: $SQ3", Toast.LENGTH_LONG).show()

            v.isClickable = false
        }

        btnClear.setOnClickListener {
            etSQ1.setText("")
            etSQ2.setText("")
            etSQ3.setText("")

            tvAnswers.text = ""

            btnEnter.isClickable = true
        }

        etSQ1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                tvAnswers.text = ""
                btnEnter.isClickable = true
            }


        })

        etSQ2.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                tvAnswers.text = ""
                btnEnter.isClickable = true
            }
        })

        etSQ3.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                tvAnswers.text = ""
                btnEnter.isClickable = true
            }
        })
    }
}