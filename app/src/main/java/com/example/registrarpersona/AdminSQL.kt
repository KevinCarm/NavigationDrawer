package com.example.registrarpersona

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQL(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(dataBase: SQLiteDatabase?) {
        val query: String = "CREATE TABLE ${TablaPersona().nombreTabla} ( " +
                "${TablaPersona().campoId} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${TablaPersona().campoNombre} VARCHAR(40), ${TablaPersona().campoApellido} VARCHAR(40), " +
                "${TablaPersona().campoSueldo } DOUBLE )";
        dataBase?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}