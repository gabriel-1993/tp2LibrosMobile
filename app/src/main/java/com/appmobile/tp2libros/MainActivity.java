package com.appmobile.tp2libros;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appmobile.tp2libros.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    /*referencia viewModel*/
    private MainActivityViewModel mv;

    /*referencia binding*/
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.ivImgIndex.setImageResource(R.drawable.libro);




        mv.getmMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMsjsError.setText(s);
            }
        });

        mv.getmLibros().observe(this, new Observer<ArrayList<Libro>>() {
            @Override
            public void onChanged(ArrayList<Libro> libros) {
                LibroAdapter la = new LibroAdapter( libros, getLayoutInflater());
                GridLayoutManager glm = new GridLayoutManager(MainActivity.this, 1, GridLayoutManager.VERTICAL, false);


            }
        });



        binding.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = binding.etBuscarLibro.getText().toString().trim().toLowerCase();
                mv.validarYbuscarLibro(titulo);
            }
        });


    }
}