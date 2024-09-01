package com.appmobile.tp2libros;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LibroEncontradoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro_encontrado);

        // Recibir los libros encontrados desde el Intent
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("libros");
        ArrayList<Libro> librosEncontrados = (ArrayList<Libro>) bundle.getSerializable("libros");

        // Inicializar el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.lista);
        GridLayoutManager glm = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(glm);

        // Configurar el Adapter
        LibroAdapter adapter = new LibroAdapter(librosEncontrados, getLayoutInflater());
        recyclerView.setAdapter(adapter);

        // Configurar el botón "Volver" para cerrar la actividad
        findViewById(R.id.btnVolver).setOnClickListener(view -> finish());
    }
}
