package com.example.wendymenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meal_items = resources.getStringArray(R.array.meal_items)
        val rgMeal = findViewById<RadioGroup>(R.id.rgMeal)

        val burger_number = resources.getStringArray(R.array.burger_number)
        val rgBurger = findViewById<RadioGroup>(R.id.rgNumber)



        for (item in meal_items) {
            val rbMeal = RadioButton(this)
            rbMeal.setText(item)
            rbMeal.setTag(item)
            rgMeal?.addView(rbMeal)
        }

        for (item in burger_number) {
            val rbBurger = RadioButton(this)
            rbBurger.setText(item)
            rbBurger.setTag(item)
            rgBurger?.addView(rbBurger)
        }

        val meal_prices = resources.getStringArray(R.array.meal_prices)
        val burger_prices = resources.getStringArray(R.array.burger_prices)

        val llL = findViewById<LinearLayout>(R.id.llL)
        val llR = findViewById<LinearLayout>(R.id.llR)

        val toppings = resources.getStringArray(R.array.toppings)
        val cb_id_base: Int = 5000

        for (i in 0 until toppings.size) {
            val cbToppings = CheckBox(this)
            cbToppings.setText(toppings[i])
            cbToppings.id = cb_id_base+i
            llL?.addView(cbToppings)
            cbToppings.setOnCheckedChangeListener(myCheckboxListener)
        }

        val addons = resources.getStringArray(R.array.addon)
        val addon_prices = resources.getStringArray(R.array.addon_prices)
        val cb_addid_base: Int = 6000

        for (i in 0 until addons.size) {
            val cbAddons = CheckBox(this)
            cbAddons.setText(addons[i])
            cbAddons.id = cb_addid_base+i
            llR?.addView(cbAddons)
            cbAddons.setOnCheckedChangeListener(myCheckboxListener)
        }

        val btnOrder = findViewById<Button>(R.id.btnOrder)
        val tvOrderResults = findViewById<TextView>(R.id.tvOrderResults)
        val tvToppings = findViewById<TextView>(R.id.tvToppings)
        val tvAddOns = findViewById<TextView>(R.id.tvAddOns)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        var order_index: Int
        var numberBurger: String
        var meal_index: Int
        var meal: String


        var userMealPrice: Double
        var userBurgerPrice: Double
        var userAddonPrice: Double
        var total: Double
        var userToppings: String
        var userAddons: String
        var roundedTotal: String

        btnOrder.setOnClickListener{

            order_index = rgBurger.checkedRadioButtonId
            meal_index = rgMeal.checkedRadioButtonId
            userToppings = ""
            userAddons = ""
            userAddonPrice = 0.00

            for (i in 0 until toppings.size) {

                val toppingsID = cb_id_base+i
                val myCheckBox = findViewById<CheckBox>(toppingsID)

                if (myCheckBox.isChecked) {
                    userToppings += myCheckBox.text.toString() + " "
                }
            }

            for (i in 0 until addons.size) {

                val addonsID = cb_addid_base+i
                val myCheckBox = findViewById<CheckBox>(addonsID)


                if (myCheckBox.isChecked) {
                    userAddons += myCheckBox.text.toString() + " "
                    userAddonPrice = userAddonPrice + addon_prices[i].toDouble()
                }
            }

            if (order_index != -1) {
                if (meal_index != -1) {
                    if (userToppings == "") {
                        Toast.makeText(this,"Please select Toppings or Select Nothing",Toast.LENGTH_SHORT).show()
                    } else {
                        if (userAddons == ""){
                            Toast.makeText(this,"Please select Add Ons or Select None",Toast.LENGTH_SHORT).show()
                        } else {

                            val userBurgerNumber = findViewById<RadioButton>(order_index)
                            numberBurger = userBurgerNumber.tag.toString()

                            val userMeal = findViewById<RadioButton>(meal_index)
                            meal = userMeal.tag.toString()

                            userMealPrice = meal_prices[meal_index - 1].toDouble()
                            userBurgerPrice =
                                burger_prices[order_index - meal_prices.size - 1].toDouble()
                            total = userMealPrice + userBurgerPrice + userAddonPrice

                            roundedTotal = String.format("%.2f", total)

                            tvOrderResults.text = "User Order: " + numberBurger + " " + meal
                            tvToppings.text = "Toppings: " + userToppings
                            tvAddOns.text = "Add Ons: " + userAddons
                            tvTotal.text = "Total: " + roundedTotal
                        }
                    }
                } else {
                    Toast.makeText(this,"Please select a Meal and Number of Burgers",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Please select a Meal and Number of Burgers",Toast.LENGTH_SHORT).show()
            }
        }

        rgBurger.setOnCheckedChangeListener { rg, checked_index ->
            val userRadioButton = findViewById<RadioButton>(checked_index)
            tvOrderResults.text = "Order: -----"
            tvToppings.text = "Toppings: -----"
            tvAddOns.text = "Add Ons: -----"
            tvTotal.text = "Total: -----"
        }

        rgMeal.setOnCheckedChangeListener { rg, checked_index ->
            val userRadioButton = findViewById<RadioButton>(checked_index)
            tvOrderResults.text = "Order: -----"
            tvToppings.text = "Toppings: -----"
            tvAddOns.text = "Add Ons: -----"
            tvTotal.text = "Total: -----"
        }
    }

    var myCheckboxListener: CompoundButton.OnCheckedChangeListener =
        CompoundButton.OnCheckedChangeListener{ buttonView, isChecked ->
            val tvOrderResults = findViewById<TextView>(R.id.tvOrderResults)
            val tvToppings = findViewById<TextView>(R.id.tvToppings)
            val tvAddOns = findViewById<TextView>(R.id.tvAddOns)
            val tvTotal = findViewById<TextView>(R.id.tvTotal)
            tvOrderResults.text = "Order: -----"
            tvToppings.text = "Toppings: -----"
            tvAddOns.text = "Add Ons: -----"
            tvTotal.text = "Total: -----"
        }
}