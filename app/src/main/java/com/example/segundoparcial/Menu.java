package com.example.segundoparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {
    SharedPreferences preferences;
    String nombre;
    int edad;
    String genero;
   // int edadv;
    TextView saludo,caricaturatext,terrortext,acciontext;
    ImageView caricatura, terror, accion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        caricatura =(ImageView) findViewById(R.id.caricaturaimg);
        terror =(ImageView) findViewById(R.id.terrorimg);
        accion =(ImageView) findViewById(R.id.accionimg);
        caricaturatext = (TextView) findViewById(R.id.caricaturatxt);
        terrortext = (TextView) findViewById(R.id.terrortxt);
        acciontext = (TextView) findViewById(R.id.acciontxt);
        LeerDatos();
//        saludo.setText("Hola"+"De acuerdo a tu edad"+"Las categorias disponibles son:");

        verCategorias();
        caricatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),reproductor.class);
                startActivity(i);
            }
        });
        terror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),reproductor.class);
                startActivity(i);
            }
        });
        accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),reproductor.class);
                startActivity(i);
            }
        });
    }

    private void verCategorias() {
        if(edad < 12)
        {
            caricatura.setVisibility(View.VISIBLE);
            terror.setVisibility(View.INVISIBLE);
            accion.setVisibility(View.INVISIBLE);
            caricaturatext.setVisibility(View.VISIBLE);
            terrortext.setVisibility(View.INVISIBLE);
            acciontext.setVisibility(View.INVISIBLE);
        }
        if(edad <18 && edad > 12) {
            caricatura.setVisibility(View.VISIBLE);
            terror.setVisibility(View.INVISIBLE);
            accion.setVisibility(View.VISIBLE);
            caricaturatext.setVisibility(View.VISIBLE);
            terrortext.setVisibility(View.INVISIBLE);
            acciontext.setVisibility(View.VISIBLE);
        }
        if(edad > 18 ) {
            caricatura.setVisibility(View.VISIBLE);
            terror.setVisibility(View.VISIBLE);
            accion.setVisibility(View.VISIBLE);
            caricaturatext.setVisibility(View.VISIBLE);
            terrortext.setVisibility(View.VISIBLE);
            acciontext.setVisibility(View.VISIBLE);
        }

    }

    private void LeerDatos() {
        preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        nombre=preferences.getString("nombre","");
        edad=preferences.getInt("edad",0);
        genero=preferences.getString("genero","");
    }
}