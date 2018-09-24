package com.example.bekoman.toby_main;

import android.util.Log;

/**
 * Created by csb on 05/05/17.
 */

public class item_tarea {

    private String materia;

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getMateria() {

        return materia;
    }

    private String titulo;
    private String fecha;
    private String detalles;

    public item_tarea(String title, String date, String details){
        titulo = title;
        fecha = date;
        detalles = details;    }

    public item_tarea(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public void imprimir_item_tarea(){
        Log.d("@Cristian", "Item: " + titulo + " " + fecha + " " + detalles);
    }
}
