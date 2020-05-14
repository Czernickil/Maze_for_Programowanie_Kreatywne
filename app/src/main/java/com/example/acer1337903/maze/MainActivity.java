package com.example.acer1337903.maze;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tx;
    static int wielkosc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Pobbieramy wielkość naszego labiryntu
        tx= (EditText) findViewById(R.id.wielkosc);
    }

    public void labirynth(View view) {
        if(tx.length()>0){
            //sprawdzamy czy wielkość naszego labiryntu nie jest nullem
        wielkosc = Integer.valueOf(tx.getText().toString());
        if(wielkosc>0 && wielkosc<19){
            //sprawdzamy czy wielkość naszego labiryntu jest wieksza od 0
        Intent intent = new Intent(this, Maze.class);
        startActivity(intent);}else
        {
            AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setMessage("Podaj poprawna wartosc");
            alertDialog.show();
        }
        }else
        {
            AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setMessage("Podaj poprawna wartosc");
            alertDialog.show();
        }
    }

    public void quit(View view) {
        System.exit(0);
    }
}
