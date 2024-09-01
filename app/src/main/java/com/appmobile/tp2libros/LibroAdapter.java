package com.appmobile.tp2libros;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.ViewHolderLibro>
{

    private List<Libro> libros;

    private LayoutInflater li;


    public LibroAdapter(List<Libro> libros, LayoutInflater li){
        this.libros = libros;
        this.li = li;
    }



    @NonNull
    @Override
    public ViewHolderLibro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item, parent, false);
        return new ViewHolderLibro(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLibro holder, int position) {
    Libro libro = libros.get(position);
    holder.tvTitulo.setText(libro.getTitulo());
    holder.tvAutor.setText(libro.getAutor());
    holder.tvAnio.setText(libro.getAnio());
    // Convertir la lista de géneros a un string separado por comas
        String generos = TextUtils.join(", ", libro.getGenero());
        holder.tvGenero.setText(generos);
    holder.imagen.setImageResource(libro.getFoto());

    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    /* class puente entre adaptar y xml*/
    public class ViewHolderLibro extends RecyclerView.ViewHolder {

        TextView tvAutor;
        TextView tvTitulo;
        TextView tvAnio;
        TextView tvGenero;
        EditText etDescripcion;
        ImageView imagen;


        /*constructor que recibe view con todos los componentes*/
        public ViewHolderLibro(@NonNull View itemView) {
            super(itemView);

            tvAutor = itemView.findViewById(R.id.autor);
            tvTitulo = itemView.findViewById(R.id.titulo);
            tvAnio = itemView.findViewById(R.id.anio);
            tvGenero = itemView.findViewById(R.id.genero);
            etDescripcion = itemView.findViewById(R.id.descripcion);
            imagen = itemView.findViewById(R.id.portada);
        }
    }



}
