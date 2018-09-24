package com.example.bekoman.toby_main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;

import static com.example.bekoman.toby_main.toby_main.dataBaseHelper;

/**
 * Created by csb on 04/05/17.
 */

public class enviar_notificacion extends Activity {

    Button boton_enviar;
    Button btn_ok;
    TextView tv_aviso_texto;
    View v;
    Intent i;
    LayoutInflater inflador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.enviar_notificacion);

        boton_enviar = (Button) findViewById(R.id.btn_enviar_notificacion);
        boton_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(enviar_notificacion.this);
                View vista = getLayoutInflater().inflate(R.layout.enviado_dialog, null);

                final TextView texto = (TextView) vista.findViewById(R.id.tv_aviso_texto);
                texto.setText("SE ENVIÓ EL AVISO");

                b.setView(vista);
                AlertDialog dialog = b.create();
                b.show();

                btn_ok = (Button) vista.findViewById(R.id.btn_aviso_ok);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i = new Intent(getApplicationContext(), toby_main.class);
                        startActivity(i);
                        finish();
                    }
                });



            }
        });
    }





}
