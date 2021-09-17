package com.defv.pruebamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Pattern;

public class sing_up extends AppCompatActivity {

    private EditText firstname, lastname, email, password, confirm_password;

    private ImageButton btn_atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        btn_atras = findViewById(R.id.btn_back);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
    }

    public void atras(View view) {
        finish();
    }

    public void registrar(View view) {

        String FirstName = firstname.getText().toString();
        String LastName = lastname.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String ConfirPass = confirm_password.getText().toString();
        validarEmail(email);

        if (FirstName.isEmpty()){
            firstname.setError("Campo obligatorio");

        }else if(LastName.isEmpty()){
            lastname.setError("Campo obligatorio");

        }else if(Email.isEmpty()){
            email.setError("Campo obligatorio");

        }else if(Password.isEmpty()){
            password.setError("Campo obligatorio");

        }else if(Password.length() < 8){
            password.setError("Debe contener almenos 8 digitos");

        }else if(ConfirPass.isEmpty()){
            confirm_password.setError("Campo obligatorio");

        }else{

            AdminSQLite admin = new AdminSQLite(this, "registros.db", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String nombre = firstname.getText().toString();
            String apellido = lastname.getText().toString();
            String correo = email.getText().toString();
            String contrasena = password.getText().toString();
            String confirContra = confirm_password.getText().toString();

            if (contrasena.equals(confirContra)){

                ContentValues registro = new ContentValues();
                registro.put("firstname", nombre);
                registro.put("lastname", apellido);
                registro.put("email", correo);
                registro.put("password", contrasena);

                bd.insert("usuarios", null, registro);
                bd.close();

                firstname.setText(null);
                lastname.setText(null);
                email.setText(null);
                password.setText(null);
                confirm_password.setText(null);

                Intent intent = new Intent(sing_up.this, notificacion.class);
                startActivity(intent);
                Toast.makeText(this, "¡Bienvenido " + nombre + "!", Toast.LENGTH_SHORT).show();

            }else{

                confirm_password.setError("Las contraseñas deben coincidir.");

            }
        }
    }

    private boolean validarEmail(EditText email){
        String Email = email.getText().toString();

        if(!Email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            return true;
        }else{
            email.setText(null);
            email.setError("Ingrese una direccion de correo válida");
            return false;
        }
    }
}