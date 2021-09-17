package com.defv.pruebamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class notificacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);
    }

    public void irHome(View view) {
        Intent intent = new Intent(notificacion.this, home.class);
        startActivity(intent);
    }
}