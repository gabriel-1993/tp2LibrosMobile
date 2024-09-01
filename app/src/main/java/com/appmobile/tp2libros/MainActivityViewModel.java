package com.appmobile.tp2libros;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
public class MainActivityViewModel extends AndroidViewModel {

private ArrayList<Libro> libros = new ArrayList<>();


    private MutableLiveData<String> mMensaje;
    private MutableLiveData<ArrayList<Libro>> mLibros;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mLibros = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Libro>> getmLibros() {
        if(mLibros == null) {
            mLibros = new MutableLiveData<>();
        }
        return mLibros;
    }

    public void cargarLista(){

        libros.add(new Libro("Relato de un Naufrago", "Gabriel García Márquez", "1955",
                Arrays.asList("Aventura", "Narrativa", "Realismo Mágico"), R.drawable.rdun,
                "Historia de un marinero que sobrevive a un naufragio en el Caribe."));

        libros.add(new Libro("Diez Negritos", "Agatha Christie", "1939",
                Arrays.asList("Misterio", "Suspense"), R.drawable.dn,
                "Diez personas atrapadas en una isla, acusadas de crímenes, son asesinadas una por una."));

        libros.add(new Libro("La Casa Torcida", "Agatha Christie", "1949",
                Arrays.asList("Misterio", "Crimen"), R.drawable.lct,
                "Un asesinato ocurre en una casa llena de secretos y familiares sospechosos."));

        libros.add(new Libro("Angeles y Demonios", "Dan Brown", "2000",
                Arrays.asList("Thriller", "Suspense", "Ficción"), R.drawable.ayd,
                "El simbologista Robert Langdon investiga una conspiración en el Vaticano."));

        libros.add(new Libro("martin fierro", "José Hernández", "1872",
                Arrays.asList("Poesía", "Literatura Gauchesca"), R.drawable.mf,
                "Epopeya argentina que narra la vida y las luchas del gaucho Martín Fierro."));


        /*SETEAR AL MUTABLE*/
        mLibros.setValue(libros);
    }

    public LiveData<String> getmMensaje() {
        if (mMensaje == null) {
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void buscarTitulo(String textoCapturado) {
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : libros) {
            // Comparar sin considerar mayúsculas y minúsculas
            if (libro.getTitulo().toLowerCase().contains(textoCapturado.toLowerCase())) {
                librosEncontrados.add(libro);

                System.out.println(libro);
            }
        }
        mLibros.setValue(librosEncontrados);
    }

    public void validarYbuscarLibro(String textoIngresado) {
        // Validar que el texto ingresado no esté vacío
        if (textoIngresado.trim().isEmpty()) {
            mMensaje.setValue("Título vacío, puede filtrar por el Título o por Letras Ingresadas...");
        } else {
            buscarTitulo(textoIngresado);
            if (mLibros.getValue() != null || !mLibros.getValue().isEmpty()) {
                Intent intent = new Intent(getApplication(), LibroEncontradoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("libros", mLibros.getValue());
                intent.putExtra("libros", bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplication().startActivity(intent);
            } else {
                mMensaje.setValue("No se encontró ninguna coincidencia para: " + textoIngresado);
            }
        }
    }


}
