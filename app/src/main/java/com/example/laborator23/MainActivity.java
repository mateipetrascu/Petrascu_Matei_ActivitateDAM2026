package com.example.laborator23;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CicluViata";

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

        afiseazaLoguri("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        afiseazaLoguri("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        afiseazaLoguri("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        afiseazaLoguri("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        afiseazaLoguri("onStop");
    }

    private void afiseazaLoguri(String metoda) {
        String mesaj = "MainActivity - " + metoda;

        Log.e(TAG, mesaj + " - error (demonstratie)");
        Log.w(TAG, mesaj + " - warning (demonstratie)");
        Log.d(TAG, mesaj + " - debug");
        Log.i(TAG, mesaj + " - info");
        Log.v(TAG, mesaj + " - verbose");
    }
}