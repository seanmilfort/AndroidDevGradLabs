package com.example.aclass

class Pokemon {

    var pokename: String
    var pokenumber: String
    var type: String
    var evolution: String

    constructor(pokename_data: String, pokenumber_data: String, type_data: String, evolution_data: String) {
        this.pokename = pokename_data
        this.pokenumber = pokenumber_data
        this.type = type_data
        this.evolution = evolution_data
    }
}