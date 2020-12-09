package com.example.registrarpersona

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.fragment_buscar.*
import java.lang.Exception

class fragment_buscar : Fragment() {

    lateinit var vista: View
    lateinit var lista_per: ListView
    var listaPersona: ArrayList<Persona> = ArrayList()
    var listaNombre: ArrayList<String> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_buscar, container, false)
        lista_per = vista.findViewById(R.id.lista_personas)
        llenarList()

        lista_per.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Toast.makeText(context, listaPersona.get(position).nombre, Toast.LENGTH_LONG).show()
                val person: Persona = listaPersona.get(position)
                val intent: Intent = Intent(context,mostrar_informacion::class.java).apply {
                    val bundle: Bundle = Bundle()
                    bundle.putSerializable("Objeto",person)
                    putExtras(bundle)
                }
                startActivity(intent)

            }

        return vista
    }
    private fun llenarList() {
        val admin: AdminSQL = AdminSQL(context, "Registro", null, 1)
        val base: SQLiteDatabase = admin.readableDatabase
        val query: String = "SELECT * FROM ${TablaPersona().nombreTabla}"
        val cursor: Cursor = base.rawQuery(query, null)
        try {
            while (cursor.moveToNext()) {
                val persona: Persona = Persona(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3)
                )
                listaPersona.add(persona)
            }
            llenarNombre()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun llenarNombre() {
        listaPersona.forEach { item: Persona ->
            listaNombre.add(item.nombre)
        }
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(context!!, R.layout.item_lista, listaNombre)
        lista_per.adapter = adapter
    }

}