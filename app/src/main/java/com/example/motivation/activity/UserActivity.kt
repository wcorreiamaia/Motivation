package com.example.motivation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.infra.MotivationContants
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btSave.setOnClickListener(this)

        verifyUserName()

    }

    override fun onClick(view: View) {

        if (view.id == R.id.bt_save) {
            handleSave()
        }
    }

    private fun verifyUserName(){
        //Verifica se o nome já é existente. Caso seja, ele já vai direto para a tela "mainActivity"
        val name = SecurityPreferences(this).getString(MotivationContants.KEY.USER_NAME)
        if (name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    private fun handleSave() {

        //pegando o texto que será inserido no campo "nome"
        val name = binding.etName.text.toString()
        if (name != "") {
            SecurityPreferences(this).storeString(MotivationContants.KEY.USER_NAME, name)
            //linha responsável por carrega a activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}