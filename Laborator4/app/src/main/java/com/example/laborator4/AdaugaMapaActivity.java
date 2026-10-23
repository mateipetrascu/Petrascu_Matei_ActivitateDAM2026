package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaMapaActivity extends AppCompatActivity {

    private EditText etDenumire;
    private EditText etNumarFoi;
    private CheckBox cbBuzunar;
    private RadioButton rbAlbastra;
    private Spinner spMaterial;
    private RatingBar rbImportanta;
    private Switch swImpermeabila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_mapa);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });

        etDenumire = findViewById(R.id.etDenumire);
        etNumarFoi = findViewById(R.id.etNumarFoi);
        cbBuzunar = findViewById(R.id.cbBuzunar);
        rbAlbastra = findViewById(R.id.rbAlbastra);
        spMaterial = findViewById(R.id.spMaterial);
        rbImportanta = findViewById(R.id.rbImportanta);
        swImpermeabila = findViewById(R.id.swImpermeabila);

        String[] materiale = {"Carton", "Plastic", "Textil"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                materiale
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spMaterial.setAdapter(adapter);

        Button btnSalveaza = findViewById(R.id.btnSalveazaMapa);

        btnSalveaza.setOnClickListener(v -> salveazaMapa());
    }

    private void salveazaMapa() {
        String denumire = etDenumire.getText().toString().trim();
        String textFoi = etNumarFoi.getText().toString().trim();

        if (denumire.isEmpty() || textFoi.isEmpty()) {
            Toast.makeText(
                    this,
                    "Completează denumirea și numărul de foi.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        int numarFoi;

        try {
            numarFoi = Integer.parseInt(textFoi);
        } catch (NumberFormatException e) {
            Toast.makeText(
                    this,
                    "Numărul de foi trebuie să fie valid.",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        String culoare = rbAlbastra.isChecked()
                ? "Albastră" : "Roșie";

        String material = spMaterial.getSelectedItem().toString();

        Mapa mapa = new Mapa(
                denumire,
                numarFoi,
                cbBuzunar.isChecked(),
                culoare,
                material,
                rbImportanta.getRating(),
                swImpermeabila.isChecked()
        );

        Intent rezultat = new Intent();
        Bundle date = new Bundle();

        date.putString("denumire", mapa.getDenumire());
        date.putInt("numarFoi", mapa.getNumarFoi());
        date.putBoolean("areBuzunar", mapa.isAreBuzunar());
        date.putString("culoare", mapa.getCuloare());
        date.putString("material", mapa.getMaterial());
        date.putFloat("importanta", mapa.getImportanta());
        date.putBoolean("impermeabila", mapa.isImpermeabila());

        rezultat.putExtras(date);
        setResult(RESULT_OK, rezultat);
        finish();
    }
}