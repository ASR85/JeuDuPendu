package com.example.jeudupendu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.usage.StorageStatsManager;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FenetrePenduCinema extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mot;
    private Button boutonE;
    private TextView lettreTap;
    private ImageView imPendu;
    private EditText lettre;
    private List<Character> liste = new ArrayList<>();
    private String word;
    private int trouve;
    private int erreur;
    private List<String> listeMots = new ArrayList<>();

    private boolean resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenetre_pendu_cinema);

        mot = (LinearLayout) findViewById(R.id.mot);
        boutonE = (Button) findViewById(R.id.boutonE);
        lettre = (EditText) findViewById(R.id.lettre);
        imPendu = (ImageView) findViewById(R.id.imPendu);
        lettreTap = (TextView) findViewById(R.id.lettreTap);

        initJeu();

        boutonE.setOnClickListener(this);

    }

    public void initJeu() {

        word = genererMot();
        //word ="ITALIE";
        resultat = false;
        erreur = 0;
        trouve = 0;
        lettreTap.setText("");
        imPendu.setBackgroundResource(R.drawable.first);
        liste = new ArrayList<>();
        mot.removeAllViews();

        for (int i = 0; i < word.length(); i++) {

            TextView oneLetter = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            mot.addView(oneLetter);

        }

    }

    @Override
    public void onClick(View v) {
        String lettreFromInput = lettre.getText().toString().toUpperCase();
        lettre.setText("");

        if (lettreFromInput.length() > 0) {

            if (!lettreDejaUtilisee(lettreFromInput.charAt(0), liste)) {

                liste.add(lettreFromInput.charAt(0));
                verificationLettreMot(lettreFromInput, word);
            }
            if (trouve == word.length()) {
                resultat=true;
                dialogue(resultat);
            }

            if (!word.contains(lettreFromInput)) {

                erreur++;
                setImage(erreur);
            }

            if (erreur == 6) {

                resultat = false;
                dialogue(resultat);
            }
            affichageLettre(liste);
        }
    }

    public boolean lettreDejaUtilisee(char a, List<Character> liste) {

        for (int i = 0; i < liste.size(); i++) {

            if (liste.get(i) == a) {

                Toast.makeText(getApplicationContext(), "Vous avez déjà entré cette lettre", Toast.LENGTH_SHORT).show();
                return true;
            }

        }
        return false;
    }

    public void verificationLettreMot(String lettre, String word) {

        for (int i = 0; i < word.length(); i++) {

            if (lettre.equals(String.valueOf(word.charAt(i)))) {
                TextView tv = (TextView) mot.getChildAt(i);
                tv.setText(String.valueOf(word.charAt(i)));
                trouve++;
            }
        }

    }

    public void affichageLettre(List<Character> liste) {
        String chaine = "";
        for (int i = 0; i < liste.size(); i++) {

            chaine += liste.get(i) + "\n";

        }
        if (!chaine.equals("")) {

            lettreTap.setText(chaine);
        }
    }

    public void setImage(int erreur) {

        switch (erreur) {

            case 1:
                imPendu.setBackgroundResource(R.drawable.second);
                break;

            case 2:
                imPendu.setBackgroundResource(R.drawable.third);
                break;

            case 3:
                imPendu.setBackgroundResource(R.drawable.fourth);
                break;

            case 4:
                imPendu.setBackgroundResource(R.drawable.fifth);
                break;

            case 5:
                imPendu.setBackgroundResource(R.drawable.sixth);
                break;

            case 6:
                imPendu.setBackgroundResource(R.drawable.seventh);
                break;
        }

    }

    public void dialogue(boolean resultat) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.trophgagne);
        builder.setTitle("Bravo tu as gagné !");
        if(!resultat){
            builder.setIcon(R.drawable.perdu);
            builder.setTitle("Tu as perdu !");
            builder.setMessage("Le mot à trouver était : " + word);
        }

        builder.setPositiveButton(getResources().getString(R.string.rejouer), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initJeu();
            }
        });
        builder.create().show();
    }

    public List<String> donnerlisteMots(){

        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(getAssets().open("cinema.txt")));
            String ligne;
            while((ligne = buffer.readLine())!= null) {
                listeMots.add(ligne);
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listeMots;
    }

    public String genererMot(){

        listeMots = donnerlisteMots();
        int random = (int) (Math.floor(Math.random()*listeMots.size()));
        String word = listeMots.get(random);
        return word;
    }

}

