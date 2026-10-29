package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaMapeActivity extends AppCompatActivity {

    private ArrayList<Mapa> listaMape;
    private ArrayAdapter<Mapa> adapter;
    private TextView tvGol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_mape);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });

        ListView lvMape = findViewById(R.id.lvMape);
        tvGol = findViewById(R.id.tvGol);

        listaMape = (ArrayList<Mapa>) getIntent()
                .getSerializableExtra("listaMape");

        if (listaMape == null) {
            listaMape = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaMape
        );

        lvMape.setAdapter(adapter);
        actualizeazaMesajGol();

        lvMape.setOnItemClickListener((parent, view, position, id) -> {
            Mapa mapa = listaMape.get(position);

            Toast.makeText(
                    this,
                    mapa.toString(),
                    Toast.LENGTH_LONG
            ).show();
        });

        lvMape.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    Mapa mapa = listaMape.get(position);

                    listaMape.remove(position);
                    adapter.notifyDataSetChanged();
                    actualizeazaMesajGol();

                    Toast.makeText(
                            this,
                            "Ai șters mapa: " + mapa.getDenumire(),
                            Toast.LENGTH_SHORT
                    ).show();

                    return true;
                });
    }

    private void actualizeazaMesajGol() {
        if (listaMape.isEmpty()) {
            tvGol.setVisibility(View.VISIBLE);
        } else {
            tvGol.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent rezultat = new Intent();
        rezultat.putExtra("listaMape", listaMape);
        setResult(RESULT_OK, rezultat);

        super.onBackPressed();
    }
}