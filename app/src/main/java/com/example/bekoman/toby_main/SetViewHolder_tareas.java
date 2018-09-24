package com.example.bekoman.toby_main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by csb on 05/05/17.
 */

public class SetViewHolder_tareas extends RecyclerView.ViewHolder{

    public TextView titulo;
    public TextView fecha;
    public TextView detalles;


    public SetViewHolder_tareas(View vista) {
        super(vista);
        titulo = (TextView) vista.findViewById(R.id.cd_tareas_title);
        fecha = (TextView) vista.findViewById(R.id.cd_tareas_date);
        detalles = (TextView) vista.findViewById(R.id.cd_tareas_details);

    }

}
