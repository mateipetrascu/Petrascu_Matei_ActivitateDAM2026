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

public class SecondActivity extends AppCompatActivity {

    private static final int CERERE_REZULTAT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });

        Button buton = findViewById(R.id.btnDeschideThird);

        buton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    SecondActivity.this, ThirdActivity.class);

            Bundle date = new Bundle();
            date.putString("mesaj", "Salut din activitatea a doua!");
            date.putInt("numar1", 12);
            date.putInt("numar2", 8);

            intent.putExtras(date);

            startActivityForResult(intent, CERERE_REZULTAT);
        });
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CERERE_REZULTAT
                && resultCode == RESULT_OK
                && data != null) {

            String mesaj = data.getStringExtra("mesajInapoi");
            int suma = data.getIntExtra("suma", 0);

            Toast.makeText(
                    this,
                    mesaj + "\nSuma numerelor este: " + suma,
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}