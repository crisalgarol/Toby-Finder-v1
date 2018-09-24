package com.example.bekoman.toby_main;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import static com.example.bekoman.toby_main.toby_main.dataBaseHelper;

/**
 * Created by csb on 04/05/17.
 */

public class tareas extends Activity {

    private TextView mate;
    private FloatingActionButton fab_add_tarea;
    private String agregar_tarea_sql;

    private RecyclerView reciclador_tareas;
    private ArrayList<item_tarea> lista_tareas = new ArrayList<item_tarea>();
    private Cursor cursor = null;
    private static adaptador_elemento_tarea adaptador;
    private int focused;
    private LinearLayout ly_tareas;
    private RecyclerView reciclador;

    private AlertDialog.Builder b;
    private View vista;

    private AlertDialog dialog;


    private int dia, mes, año;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dataBaseHelper = new DataBaseHelper(this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas);
        final Bundle info = getIntent().getExtras();

        mate = (TextView) findViewById(R.id.tareas_materia);
        mate.setText(info.getString("materia"));

        b = new AlertDialog.Builder(tareas.this);
        vista = getLayoutInflater().inflate(R.layout.agregar_tarea_dialog, null);

        final EditText titulo = (EditText) vista.findViewById(R.id.ed_titletarea);
        final EditText detalles = (EditText) vista.findViewById(R.id.ed_detallestarea);
        final EditText fecha = (EditText) vista.findViewById(R.id.ed_date);
        final Button btn_agregar = (Button) vista.findViewById(R.id.btn_agregar_tarea);

        titulo.setFilters( new InputFilter[] { new InputFilter.AllCaps()});

        //Reciclador

        reciclador = (RecyclerView) findViewById(R.id.reciclador_tareas);
        reciclador.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager lmanager = new LinearLayoutManager(this);
        lista_tareas = cargar_tarea_base(info.getString("materia"));


        adaptador = new adaptador_elemento_tarea(this, lista_tareas, this);
        adaptador.setOnTapListener(new OnTapListener() {
            @Override
            public void OnTapView(int position) {

            }
        });


        //SELECTOR DE FECHA

        fecha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(v == fecha){

                    final Calendar c = Calendar.getInstance();

                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    año = c.get(Calendar.YEAR);

                    Log.d("@Cristian", "Fecha: " + dia + "/ " + mes + "/ " + año);

                    DatePickerDialog picker = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            fecha.setText(dayOfMonth + " de " + convertir_mes(month) + " del " + year);
                        }

                    },dia, mes, año);

                    picker.updateDate(año, mes, dia);
                    picker.show();

            }


            }
        });

        reciclador.setLayoutManager(lmanager);
        reciclador.setAdapter(adaptador);

        fab_add_tarea = (FloatingActionButton) findViewById(R.id.fab_agregar_tarea);
        fab_add_tarea.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(vista.getParent()!=null){
                    ((ViewGroup) vista.getParent()).removeView(vista);
                }

                titulo.setText("");
                fecha.setText("");
                detalles.setText("");


                b.setView(vista);
                dialog = b.create();
                dialog.show();



                }

            });

        btn_agregar.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View v) {

                String to_up_details;

                agregar_tarea_sql = "INSERT INTO 'TAREAS' ('MATERIA', 'TITULO', 'FECHA', 'DETALLES') VALUES (" + info.getString("materia") + ", " + titulo.getText().toString() +"," + fecha.getText().toString() + ", " + detalles.getText().toString() +");";
                //Toast.makeText(tareas.this, agregar_tarea_sql, Toast.LENGTH_LONG).show();

                try{

                    try {
                        to_up_details = detalles.getText().toString().substring(0, 1).toUpperCase() + detalles.getText().toString().substring(1);
                    }catch (Exception e){
                        to_up_details = "";
                    }

                    Boolean no_vacio = 


                    dataBaseHelper.agregar_tarea(info.getString ("materia"), titulo.getText().toString(), fecha.getText().toString(), to_up_details);
                    Log.d("@Cristian", "SE ESTA INSERTANDO " + agregar_tarea_sql);
                }catch (SQLiteException e){
                    Log.d("@Cristian", "No se pudo agregar " + agregar_tarea_sql);
                }

                lista_tareas.clear();
                lista_tareas = cargar_tarea_base(info.getString("materia"));
                adaptador = new adaptador_elemento_tarea(tareas.this, lista_tareas, tareas.this);
                LinearLayoutManager lmanager = new LinearLayoutManager(tareas.this);
                reciclador.setAdapter(adaptador);
                dialog.dismiss();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }

        });


    }

    public ArrayList<item_tarea> cargar_tarea_base(String materia){


        try {
            cursor = dataBaseHelper.cargar_tareas(materia);
            Log.d("@Salomóns", "Tamaño received " + cursor.getCount());

            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {

                            item_tarea item = new item_tarea();

                            item.setMateria(cursor.getString(0));
                            item.setTitulo(cursor.getString(1));
                            item.setFecha(cursor.getString(2));
                            item.setDetalles(cursor.getString(3));
                            lista_tareas.add(item);

                    } while (cursor.moveToNext());

                }
            }
        }catch (SQLiteException e) {
            e.printStackTrace();
            Log.d("@Salomón", "Couldn't charge the base cargar_tarea_base");
        }

        Log.d("@Salomón", "Se está regresando:  " + lista_tareas.size());
        return lista_tareas;


    }

    public String convertir_mes(int mes){

        switch (mes){

            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Septiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";

            default:
                return "Error de mes";


        }
    }
}
