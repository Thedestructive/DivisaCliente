package com.example.appmodenaclient

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class MainActivity : AppCompatActivity() {
    lateinit var spnMonedas : Spinner

    val mLoaderCallbacks = object : LoaderManager.LoaderCallbacks<Cursor>{
        override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
            return CursorLoader(
                applicationContext,
                Uri.parse("content://com.example.appmonedaserver/cambios"),
                arrayOf<String>("_ID", "codeMonedaCambio","cambio","fechaActualizacion","fechaConsulta"),
                null, null, null)
        }

        override fun onLoaderReset(loader: Loader<Cursor>) {}

        override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
            val adapter = SimpleCursorAdapter(applicationContext,
                R.layout.layoutcustom,data,
                arrayOf<String>("_ID","cambio","codeMonedaCambio","fechaActualizacion","fechaConsulta"),
                IntArray(5).apply {
                    set(1, R.id.text1)
                    set(2, R.id.text2)
                    set(3, R.id.text3)
                    set(4, R.id.text4)
                },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
            )
            spnMonedas.adapter =adapter
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spnMonedas = findViewById(R.id.spinner)

        LoaderManager.getInstance(this)
            .initLoader<Cursor>(1001, null, mLoaderCallbacks)


    }
}