package com.example.jeudupendu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {


    private ImageView ivTout;
    private ImageView ivGeek;
    private ImageView ivCinema;
    private ImageView ivSerie;
    private ImageView iv5;
    private ImageView iv6;
    private ImageView iv7;
    private ImageView iv8;
    private ImageView iv9;
    private ImageView iv10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivTout = (ImageView) findViewById(R.id.ivTout);
        ivGeek = (ImageView) findViewById(R.id.ivGeek);
        ivCinema = (ImageView) findViewById(R.id.ivCinema);
        ivSerie = (ImageView) findViewById(R.id.ivSerie);

        /*ATTENTION A DECLARER DANS LE LAYOUT
        iv5 = (ImageView) findViewById(R.id.iv5);
        iv6 = (ImageView) findViewById(R.id.iv6);
        iv7 = (ImageView) findViewById(R.id.iv7);
        iv8 = (ImageView) findViewById(R.id.iv8);
        iv9 = (ImageView) findViewById(R.id.iv8);
        iv10= (ImageView) findViewById(R.id.iv10);*/


        ivTout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fenetreJeux = new Intent(MainActivity.this, FenetrePendu.class);
                startActivity(fenetreJeux);
            }
        });

        ivGeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fenetreJeux = new Intent(MainActivity.this, FenetrePenduGeek.class);
                startActivity(fenetreJeux);
            }
        });


        ivCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fenetreJeux = new Intent(MainActivity.this, FenetrePenduCinema.class);
                startActivity(fenetreJeux);
            }
        });

        ivSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fenetreJeux = new Intent(MainActivity.this, FenetrePenduSeries.class);
                startActivity(fenetreJeux);
            }
        });


    }
}