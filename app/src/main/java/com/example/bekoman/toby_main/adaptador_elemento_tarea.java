package com.example.bekoman.toby_main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by csb on 05/05/17.
 */

public class adaptador_elemento_tarea extends RecyclerView.Adapter<SetViewHolder> {

    //FALTA AGREGAR Y VER ESTE PEDO DEL HOLDER

    private Activity actividad;
    List<item_tarea> lista_tareas = Collections.emptyList();
    private OnTapListener onTapListener;
    Context contexto;
    Intent i;

    public adaptador_elemento_tarea(Activity actividad, List<item_tarea> lista_tareas, Context contexto) {
        this.actividad = actividad;
        this.contexto = contexto;
        this.lista_tareas = lista_tareas;
    }


    public adaptador_elemento_tarea (Context contexto){
        this.contexto = contexto;
    }

    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarea_cardview,parent, false);
        return new SetViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(SetViewHolder holder, int position) {

        holder.titulo.setText(lista_tareas.get(position).getTitulo());
        holder.fecha.setText(lista_tareas.get(position).getFecha());
        holder.detalles.setText(lista_tareas.get(position).getDetalles());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v){
            }

        });

    }

    @Override
    public int getItemCount() {
        return lista_tareas.size();
    }

    public void setOnTapListener(OnTapListener tap){
        onTapListener = tap;
    }
}
