package com.example.registrarpersona

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_registrar.*
import kotlinx.android.synthetic.main.fragment_registrar.view.*
import java.lang.Exception

class fragment_registro : Fragment() {

    lateinit var registrar: Button
    lateinit var vista: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_registrar, container, false)
        registrar = vista.findViewById(R.id.btnregistrarPersona)
        registrar.setOnClickListener {
            agregar();
        }
        return vista
    }

    // METODOS
    private fun agregar() {
        try {
            val admin: AdminSQL = AdminSQL(context, "Registro", null, 1)
            val base: SQLiteDatabase = admin.writableDatabase
            val query: String =
                "INSERT INTO ${TablaPersona().nombreTabla} (" +
                        "${TablaPersona().campoNombre}, ${TablaPersona().campoApellido}, ${TablaPersona().campoSueldo} ) " +
                        "VALUES( '${txtnombre_registro.text.toString()}','${txtapellido_registro.text.toString()}','${txtSueldo_registrar.text.toString()}') ";
            base.execSQL(query)
            Toast.makeText(context, "Registro completo", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        } finally {
            txtSueldo_registrar.setText("")
            txtapellido_registro.setText("")
            txtnombre_registro.setText("")
        }
    }

}