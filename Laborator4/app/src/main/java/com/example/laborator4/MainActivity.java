package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int CERERE_ADAUGARE_MAPA = 10;
    private static final int CERERE_LISTA_MAPE = 20;

    private TextView tvRezultat;
    private ArrayList<Mapa> listaMape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });

        tvRezultat = findViewById(R.id.tvRezultat);
        listaMape = new ArrayList<>();

        Button btnAdaugaMapa = findViewById(R.id.btnAdaugaMapa);
        Button btnVeziMape = findViewById(R.id.btnVeziMape);

        btnAdaugaMapa.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AdaugaMapaActivity.class
            );

            startActivityForResult(intent, CERERE_ADAUGARE_MAPA);
        });

        btnVeziMape.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    ListaMapeActivity.class
            );

            intent.putExtra("listaMape", listaMape);

            startActivityForResult(intent, CERERE_LISTA_MAPE);
        });
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CERERE_ADAUGARE_MAPA
                && resultCode == RESULT_OK
                && data != null) {

            Bundle date = data.getExtras();

            if (date != null) {
                Mapa mapa = new Mapa(
                        date.getString("denumire"),
                        date.getInt("numarFoi"),
                        date.getBoolean("areBuzunar"),
                        date.getString("culoare"),
                        date.getString("material"),
                        date.getFloat("importanta"),
                        date.getBoolean("impermeabila")
                );

                listaMape.add(mapa);
                afiseazaUltimaMapa();

                Toast.makeText(
                        this,
                        "Mapa a fost adăugată: "
                                + mapa.getDenumire(),
                        Toast.LENGTH_LONG
                ).show();
            }
        }

        if (requestCode == CERERE_LISTA_MAPE
                && resultCode == RESULT_OK
                && data != null) {

            ArrayList<Mapa> listaActualizata =
                    (ArrayList<Mapa>) data.getSerializableExtra(
                            "listaMape"
                    );

            if (listaActualizata != null) {
                listaMape = listaActualizata;
                afiseazaUltimaMapa();
            }
        }
    }

    private void afiseazaUltimaMapa() {
        if (listaMape.isEmpty()) {
            tvRezultat.setText(
                    "Încă nu ai adăugat nicio mapă."
            );
        } else {
            Mapa ultimaMapa = listaMape.get(
                    listaMape.size() - 1
            );

            tvRezultat.setText(ultimaMapa.toString());
        }
    }
}