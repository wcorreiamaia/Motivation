package com.example.motivation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationContants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationContants.FILTER.ALL


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide() //elimina a barra com o nome do aplicativo que aparece na tela.

        handleUserName()
        handlerFilter(R.id.iv_all)
        handleNextPhrase()


        binding.btNewPhrase.setOnClickListener(this)
        binding.ivAll.setOnClickListener(this)
        binding.ivHappy.setOnClickListener(this)
        binding.ivSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.bt_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.iv_all, R.id.iv_sunny, R.id.iv_happy)){
            handlerFilter(view.id)

        }
    }

    private fun handleNextPhrase(){
        binding.tvPhrase.text = Mock().getPhrase(categoryId).toString()

    }
    private fun handlerFilter(id: Int){
        binding.ivAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.ivHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.ivSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {

            R.id.iv_all -> {
                binding.ivAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationContants.FILTER.ALL
            }
            R.id.iv_happy -> {
                binding.ivHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationContants.FILTER.HAPPY
            }
            R.id.iv_sunny -> {
                binding.ivSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationContants.FILTER.SUNNY
            }
        }

    }


    private fun handleUserName() {
        //Aqui ele está pegando o nome que foi posto na tela anterior.
        //Então ele instancia a classe de segurança, pegando a chave "USER NAME que foi inventanda no método
        //"handleSave" na UserActivity (linha 35)

        val name = SecurityPreferences(this).getString(MotivationContants.KEY.USER_NAME)
        binding.tvUserName.text = "Olá, $name"
    }
}