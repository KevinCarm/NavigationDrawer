package com.example.registrarpersona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class mostrar_informacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_informacion)
        val recibido: Bundle? = intent.extras
        val persona: Persona = recibido?.getSerializable("Objeto") as Persona
        Toast.makeText(applicationContext,persona.nombre,Toast.LENGTH_LONG).show()
    }
}