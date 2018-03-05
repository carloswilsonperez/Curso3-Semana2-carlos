package com.example.carlo.material2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    // Declaring the Views
    private TextView tvNombre;
    private TextView tvFecha;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;

    // Declaring variables for user data
    private String nombre;
    private String descripcion;
    private String email;
    private String telefono;
    private ArrayList<Integer> fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Get references for Views
        tvNombre = (TextView)findViewById(R.id.tvNombre);
        tvFecha = (TextView)findViewById(R.id.tvFecha);
        tvTelefono = (TextView)findViewById(R.id.tvTelefono);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvDescripcion = (TextView)findViewById(R.id.tvDescripción);

        // Button for "Editar Datos"
        Button btnEditarDatos = (Button) findViewById(R.id.btnEditarDatos);

        // Get data from MainActivity
        Bundle parametros = getIntent().getExtras();
        nombre = parametros.getString(getResources().getString(R.string.p_nombre));
        telefono = parametros.getString(getResources().getString(R.string.p_telefono));
        email = parametros.getString(getResources().getString(R.string.p_email));
        descripcion = parametros.getString(getResources().getString(R.string.p_descripcion));

        // Get the ArrayList date
        fecha = (ArrayList<Integer>) getIntent().getSerializableExtra(getResources().getString(R.string.p_fecha));

        // Set the current activity view values
        tvNombre.setText(nombre);
        tvTelefono.setText(getResources().getString(R.string.texto_telefono) + " " + telefono);
        tvEmail.setText(getResources().getString(R.string.texto_email) + " " + email);
        tvDescripcion.setText(getResources().getString(R.string.texto_descripcion) + " " + descripcion);
        tvFecha.setText(getResources().getString(R.string.texto_fecha) + " " + fecha.get(0) + "/" + (fecha.get(1) + 1) + "/" + fecha.get(2));

        // When pressing the "Editar Datos" button
        btnEditarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retornaDatos();
            }
        });

    }

    // Build Intent for MainActivity
    public void retornaDatos(){
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        intent.putExtra(getResources().getString(R.string.p_nombre), nombre);
        intent.putExtra(getResources().getString(R.string.p_fecha), fecha);
        intent.putExtra(getResources().getString(R.string.p_telefono), telefono);
        intent.putExtra(getResources().getString(R.string.p_email), email);
        intent.putExtra(getResources().getString(R.string.p_descripcion), descripcion);
        startActivity(intent);
        finish();
    }

    // Overwrite the onKeyDown() method, in case the user press the Back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        // If the 'Back' button is pressed
        if (keyCode == KeyEvent.KEYCODE_BACK){
            retornaDatos();
        }
        return super.onKeyDown(keyCode, event);
    }
}