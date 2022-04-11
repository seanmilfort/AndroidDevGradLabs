package com.example.spinners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spPokemon = findViewById<Spinner>(R.id.spPokemon)
        val aaPokemon = ArrayAdapter.createFromResource(this, R.array.pokemon, android.R.layout.simple_spinner_item)
        spPokemon.adapter = aaPokemon

        val ivPokemon = findViewById<ImageView>(R.id.ivImage)

        val tvPokeNumber = findViewById<TextView>(R.id.tvPokeNumber)
        val pokemon_number = resources.getStringArray(R.array.pokemon_number)

        val tvPokeType = findViewById<TextView>(R.id.tvPokeType)
        val pokemon_type = resources.getStringArray(R.array.pokemon_type)

        val btnReset = findViewById<Button>(R.id.btnReset)

        spPokemon.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val tvUserItem = view as TextView


                var drawable_resource_name = tvUserItem.text.toString()
                drawable_resource_name = drawable_resource_name.lowercase()
                drawable_resource_name = drawable_resource_name.replace(' ', '_')

                val context = ivPokemon.getContext()
                val id = context.getResources().getIdentifier(drawable_resource_name, "drawable", context.getPackageName())
                ivPokemon.setImageResource(id)

                tvPokeNumber.text = "Pokemon #: " + pokemon_number[i]
                tvPokeType.text = "Pokemon Type: " + pokemon_type[i]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        btnReset.setOnClickListener{
            spPokemon.setSelection(0)
        }

    }
}