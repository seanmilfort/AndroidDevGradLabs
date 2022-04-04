package com.example.potatocounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    val ounces = arrayOf(5, 6, 7, 8, 9, 10, 11, 12)
    val toppings = arrayOf("Nothing", "Butter", "Sour Cream", "Chili")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spOunces = findViewById<Spinner>(R.id.spOunces)
        val spToppings = findViewById<Spinner>(R.id.spToppings)

        val aaOunces = ArrayAdapter(this, android.R.layout.simple_spinner_item, ounces)
        spOunces.adapter = aaOunces
        val aaToppings = ArrayAdapter(this, android.R.layout.simple_spinner_item, toppings)
        spToppings.adapter = aaToppings

        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvCalories = findViewById<TextView>(R.id.tvCalories)

        spOunces.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                tvCalories.text = "----------"
            }
            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }

        spToppings.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                tvCalories.text = "----------"
            }
            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }



        btnCalculate.setOnClickListener {

            val userOunces = spOunces.selectedItem.toString().toDouble() * 25
            val userToppingsIndex = spToppings.selectedItemId
            var totalUserCalories: Double

            if (userToppingsIndex == 1L) {
                totalUserCalories = userOunces.toDouble() + 100

            } else if (userToppingsIndex == 2L) {
                totalUserCalories = userOunces.toDouble() + 60

            } else if (userToppingsIndex == 3L) {
                totalUserCalories = userOunces.toDouble() + 75

            } else {
                totalUserCalories = userOunces.toDouble() + 0
            }

            tvCalories.text = "Total Calories:  " + String.format("%.0f", totalUserCalories)
        }



    }
}