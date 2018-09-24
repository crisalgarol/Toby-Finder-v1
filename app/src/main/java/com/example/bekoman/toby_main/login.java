package com.example.bekoman.toby_main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.bekoman.toby_main.toby_main.dataBaseHelper;

/**
 * Created by csb on 04/05/17.
 */

public class login  extends Activity {

    Button btn_entrar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btn_entrar = (Button) findViewById(R.id.btn_entrar);
        btn_entrar.setOnClickListener(pasar);

    }

    private View.OnClickListener pasar = new View.OnClickListener() {
        public void onClick(View v) {

            i = new Intent(v.getContext(), enviar_notificacion.class);
            startActivity(i);
            finish();

        }
    };
}
