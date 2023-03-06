package com.example.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences (context: Context) {


    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String,str: String){
        security.edit().putString(key, str).apply() //metodo que salva
    }

    fun getString(key: String): String {


        //se "security.getString(key, "")" for nulo, ele usa a outra expressão '?: ""'
        //isso é chamado de 'operador elvis'
        //ele está pegando a informação. Mas caso for nulo, ele retornará vazio.
        return security.getString(key, "") ?: ""

    }
}