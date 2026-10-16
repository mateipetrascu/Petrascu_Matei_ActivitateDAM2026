package com.example.laborator23;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    private int numar1;
    private int numar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });

        Bundle date = getIntent().getExtras();

        if (date != null) {
            String mesaj = date.getString("mesaj");
            numar1 = date.getInt("numar1");
            numar2 = date.getInt("numar2");

            Toast.makeText(
                    this,
                    mesaj + "\nNumere primite: "
                            + numar1 + " și " + numar2,
                    Toast.LENGTH_LONG
            ).show();
        }

        Button butonInapoi = findViewById(R.id.btnTrimiteInapoi);

        butonInapoi.setOnClickListener(v -> {
            int suma = numar1 + numar2;

            Intent rezultat = new Intent();
            rezultat.putExtra(
                    "mesajInapoi",
                    "Am calculat suma numerelor primite."
            );
            rezultat.putExtra("suma", suma);

            setResult(RESULT_OK, rezultat);
            finish();
        });
    }
}