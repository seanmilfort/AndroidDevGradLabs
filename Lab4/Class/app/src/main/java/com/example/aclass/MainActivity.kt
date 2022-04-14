package com.example.aclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spPokemonData = findViewById<Spinner>(R.id.spPokeData)

        val Pokemon = arrayListOf<Pokemon>()

        val input_stream = baseContext.resources.openRawResource(R.raw.pokemon_data)
        val buffer = input_stream.bufferedReader()

        val lines = buffer.readLines()
        val pokemonNames: MutableList<String> = ArrayList()

        val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
        val tvPokemonNumber = findViewById<TextView>(R.id.tvPokemonNumber)
        val tvPokemonType = findViewById<TextView>(R.id.tvPokemonType)
        val tvEvolution = findViewById<TextView>(R.id.tvEvolution)

        for(item in lines){
            val tokens = item.split("/")  //break into tokens based on delimiter
            pokemonNames.add(tokens[0]) // add names to list to use to populate spinner
            val aPokemon = Pokemon(tokens[0], tokens[1], tokens[2], tokens[3] ) //instantiate a dog
            Pokemon.add(aPokemon)  // add dog to the arraylist of dog objects
        }

        pokemonNames.add("Error Pokemon")

        val aaPokemonData = ArrayAdapter(this, android.R.layout.simple_spinner_item, pokemonNames)
        spPokemonData.adapter = aaPokemonData

        spPokemonData.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                try {
                    val tvPokemonItem = view as TextView

                    var drawable_resource_name =
                        tvPokemonItem.text.toString().lowercase().replace(" ", "_")
                    val drawable_resouce_id = getResources().getIdentifier(
                        drawable_resource_name,
                        "drawable", getPackageName()
                    )
                    ivPokemon.setImageResource(drawable_resouce_id)

                    tvPokemonNumber.text = "Number: " + Pokemon[i].pokenumber
                    tvPokemonType.text = "Type: " + Pokemon[i].type
                    tvEvolution.text = "Evolution: " + Pokemon[i].evolution
                } catch(e: Exception) {
                    ivPokemon.setImageResource(R.drawable.select_pokemon)
                    tvPokemonNumber.text = "Number: " + Pokemon[0].pokenumber
                    tvPokemonType.text = "Type: " + Pokemon[0].type
                    tvEvolution.text = "Evolution: " + Pokemon[0].evolution
                    Toast.makeText(applicationContext, "There is no information for this selection.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
    }
}