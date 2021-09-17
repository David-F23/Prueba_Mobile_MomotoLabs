package com.defv.pruebamobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity implements View.OnClickListener {

    PrimerFragmento primero = new PrimerFragmento();
    SegundoFragmento segundo = new SegundoFragmento();
    TercerFragmento tercero = new TercerFragmento();
    CuartoFragmento cuarto = new CuartoFragmento();

    private ImageView market, people, settings, jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       /* BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/

        market = findViewById(R.id.market);
        people = findViewById(R.id.people);
        settings = findViewById(R.id.settings);
        jobs = findViewById(R.id.jobs);

        market.setOnClickListener(this);
        people.setOnClickListener(this);
        settings.setOnClickListener(this);
        jobs.setOnClickListener(this);

        loadFragment(primero);
    }

   /* private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.market:
                    loadFragment(primero);
                    return true;

                case R.id.jobs:
                    loadFragment(segundo);
                    return true;

                case R.id.settings:
                    loadFragment(tercero);
                    return true;

                case R.id.people:
                    loadFragment(cuarto);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.market:
                loadFragment(primero);
                break;

            case R.id.jobs:
                loadFragment(segundo);
                break;

            case R.id.settings:
                loadFragment(tercero);
                break;

            case R.id.people:
                loadFragment(cuarto);
                break;
        }

    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_con, fragment);
        transaction.commit();
    }

    public void cerrarSesion(View view) {

            AlertDialog.Builder alert = new AlertDialog.Builder(home.this);
            alert.setIcon(android.R.drawable.ic_dialog_alert);
            alert.setTitle("Cerrar sesión");
            alert.setMessage("¿Desea cerrar sesión?");
            alert.setCancelable(false);
            alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(home.this, login.class);
                    startActivity(intent);
                    finishAffinity();
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        }
}