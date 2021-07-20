package com.example.segundoparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button ir_menu;
SharedPreferences preferences;
String nombre2, genero2;
Integer edad2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ir_menu = (Button)findViewById(R.id.irmenu);
LeerDatos();
        ir_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mostrarDialogo();
            }
        });

    }
    private void mostrarDialogo()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        final View customlayout = getLayoutInflater().inflate(R.layout.dialog_layout,null);
         alert.setView(customlayout);
        alert.setCancelable(false);
         EditText nombre= customlayout.findViewById(R.id.name);
        EditText edad1 = customlayout.findViewById(R.id.edad);
        EditText genero1 = customlayout.findViewById(R.id.genero);
        alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                  String name = nombre.getText().toString();
                  Integer edad = edad1.getText().length();
                String genero = genero1.getText().toString();
                Toast.makeText(getApplicationContext(),"Los datos guardados son:"+name+"  "+edad+" "+genero, Toast.LENGTH_LONG).show();
              GuardarCredenciales(nombre.getText().toString(),edad1.getText().length(),genero1.getText().toString());
               Intent i= new Intent(getApplicationContext(),Menu.class);
                startActivity(i);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    private void LeerDatos() {
        preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        nombre2=preferences.getString("nombre","");
        edad2=preferences.getInt("edad",0);
        genero2=preferences.getString("genero","");
    }

    private void GuardarCredenciales(String nombreuser,Integer edaduser,String generouser) {
        preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("nombre",nombreuser);
        editor.putInt("edad",edaduser);
        editor.putString("genero",generouser);
        editor.commit();
    }
}