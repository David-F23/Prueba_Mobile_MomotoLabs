package com.defv.pruebamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class login extends AppCompatActivity {

    private EditText usuarioE, contras;
    private Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioE = findViewById(R.id.emailUsuario);
        contras = findViewById(R.id.passUsuario);
        switch1 = findViewById(R.id.switch1);

            SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
            usuarioE.setText(prefe.getString("mail",""));
            contras.setText(prefe.getString("contra",""));


    }

    public void irNuevaCuenta(View view) {
        Intent intent = new Intent(login.this, sing_up.class);
        startActivity(intent);
    }

    public void ingresar(View view) {

        String usuario = usuarioE.getText().toString();
        String contra = contras.getText().toString();

        if (usuario.isEmpty()){
            usuarioE.setError("Campo obligatorio");

        }else if(contra.isEmpty()){
            contras.setError("Campo obligatorio");

        }else{

            AdminSQLite admin = new AdminSQLite(this, "registros.db", null, 1);
            SQLiteDatabase bd = admin.getReadableDatabase();

            Cursor cr = bd.query("usuarios", new String[]{"firstname", "lastname", "email", "password"},
                    "email like '" + usuario + "'" + "and password like '" + contra + "'",
                    null,null,null,null);

            if (cr.getCount() > 0){

                if (switch1.isChecked()){
                    SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencias.edit();
                    editor.putString("mail", usuarioE.getText().toString());
                    editor.putString("contra", contras.getText().toString());
                    editor.commit();
                }

                Intent intent = new Intent(login.this, home.class);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Correo o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        }
    }


}