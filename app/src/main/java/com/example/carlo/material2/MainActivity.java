package com.example.carlo.material2;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // MainActivity views
    private Button btnSiguiente;
    private DatePicker dpFecha;
    private EditText editTextNombre;
    private EditText editTextTelefono;
    private EditText editTextEmail;
    private EditText editTextDescripcion;

    // Variables
    private String nombre;
    private String email;
    private String telefono;
    private String descripcion;
    private ArrayList<Integer> fecha = new ArrayList(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference for Views in MainActivity
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        dpFecha= (DatePicker) findViewById(R.id.dpFecha);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextDescripcion = (EditText) findViewById(R.id.editTextDescripcion);

        // Hide keyboard
        editTextNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(editTextNombre.getWindowToken(), 0);
            }
            }
        });

        editTextDescripcion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                InputMethodManager input = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(editTextDescripcion.getWindowToken(), 0);
            }
            }
        });

        // Check for Bundle values
        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            // Get variable values from Activity 2
            nombre = parametros.getString(getResources().getString(R.string.p_nombre));
            telefono = parametros.getString(getResources().getString(R.string.p_telefono));
            email = parametros.getString(getResources().getString(R.string.p_email));
            descripcion = parametros.getString(getResources().getString(R.string.p_descripcion));
            fecha = (ArrayList<Integer>) getIntent().getSerializableExtra(getResources().getString(R.string.p_fecha));

            // Fill in the corresponding fields
            editTextNombre.setText(nombre);
            editTextTelefono.setText(telefono);
            editTextEmail.setText(email);
            editTextDescripcion.setText(descripcion);
            dpFecha.updateDate(fecha.get(2),fecha.get(1),fecha.get(0));
        }

        // When clickin the "Siguiente" button
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Read the user values
            nombre = editTextNombre.getText().toString();
            telefono = editTextTelefono.getText().toString();
            email = editTextEmail.getText().toString();
            descripcion = editTextDescripcion.getText().toString();
            fecha.add(0, dpFecha.getDayOfMonth());
            fecha.add(1, dpFecha.getMonth());
            fecha.add(2 , dpFecha.getYear());

            // Build Intent and send data
            Intent  intent = new Intent(MainActivity.this, Main2Activity.class);
            intent.putExtra(getResources().getString(R.string.p_nombre), nombre);
            intent.putExtra(getResources().getString(R.string.p_fecha), fecha);
            intent.putExtra(getResources().getString(R.string.p_telefono), telefono);
            intent.putExtra(getResources().getString(R.string.p_email), email);
            intent.putExtra(getResources().getString(R.string.p_descripcion), descripcion);
            startActivity(intent);

            // Destroy current Activity
            //finish();
            }
        });
    }
}
