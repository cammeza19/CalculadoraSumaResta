package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText ET1, ET2;
    private TextView TV1;
    private Button CMDCALCULAR, CMDBORRAR;
    private CheckBox CBSUMA, CBRESTA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Enlazar variables con los IDs del XML
        ET1 = findViewById(R.id.ET1);
        ET2 = findViewById(R.id.ET2);
        TV1 = findViewById(R.id.TV1);
        CMDCALCULAR = findViewById(R.id.CMDCALCULAR);
        CMDBORRAR = findViewById(R.id.CMDBORRAR);
        CBSUMA = findViewById(R.id.CBSUMA);
        CBRESTA = findViewById(R.id.CBRESTA);

        // 2. Acción del botón Calcular
        CMDCALCULAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val1 = ET1.getText().toString();
                String val2 = ET2.getText().toString();

                if (!val1.isEmpty() && !val2.isEmpty()) {
                    double n1 = Double.parseDouble(val1);
                    double n2 = Double.parseDouble(val2);
                    String resultado = "";

                    // Evaluar si el CheckBox de Suma está marcado
                    if (CBSUMA.isChecked()) {
                        double suma = n1 + n2;
                        resultado += "Suma: " + suma + "\n";
                    }

                    // Evaluar si el CheckBox de Resta está marcado
                    if (CBRESTA.isChecked()) {
                        double resta = n1 - n2;
                        resultado += "Resta: " + resta + "\n";
                    }

                    // Validar si no se seleccionó ninguna casilla
                    if (!CBSUMA.isChecked() && !CBRESTA.isChecked()) {
                        Toast.makeText(MainActivity.this, "Selecciona al menos una operación", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Mostrar el resultado en el TextView
                    TV1.setText(resultado.trim());

                } else {
                    Toast.makeText(MainActivity.this, "Por favor ingresa ambos números", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 3. Acción del botón Borrar
        CMDBORRAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ET1.setText("");
                ET2.setText("");
                TV1.setText("");
                CBSUMA.setChecked(false); // Desmarca la casilla de suma
                CBRESTA.setChecked(false); // Desmarca la casilla de resta
            }
        });
    }
}