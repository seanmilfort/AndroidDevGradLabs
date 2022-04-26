package com.example.json_url

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spPokemon = findViewById<Spinner>(R.id.spPokemon)

        val input_stream = baseContext.resources.openRawResource(R.raw.pokemon)
        var pokemon_text = input_stream.readBytes().toString(Charset.defaultCharset())

        val myJSON_object = JSONObject(pokemon_text)
        var myJSON_array = myJSON_object.getJSONArray("pokemon")

        val pokemon_items: MutableList<String> = ArrayList()

        for (i in 0.. (myJSON_array.length() - 1 )) {
            val pokemon_name = myJSON_array.getJSONObject(i).getString("pokeName")
            pokemon_items.add("$pokemon_name")
        }

        val aaPokemon = ArrayAdapter(this, android.R.layout.simple_spinner_item, pokemon_items)
        aaPokemon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPokemon.adapter = aaPokemon

        val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
        val tvPokemonNumber = findViewById<TextView>(R.id.tvPokemonNumber)
        val tvPokemonType = findViewById<TextView>(R.id.tvPokemonType)
        val tvEvolution = findViewById<TextView>(R.id.tvEvolution)


        spPokemon.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, i: Int, id: Long) {
                val pokeImage = myJSON_array.getJSONObject(i).getString("pokeImage")
                Picasso.get().load(pokeImage).placeholder(R.drawable.pokemon).error(R.drawable.ic_launcher_foreground).into(ivPokemon);

                tvPokemonNumber.text = "Number: " + myJSON_array.getJSONObject(i).getString("pokeNumber")
                tvPokemonType.text = "Type: " + myJSON_array.getJSONObject(i).getString("pokeType")
                tvEvolution.text = "Evolution: " + myJSON_array.getJSONObject(i).getString("pokeEvolution")

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }
}