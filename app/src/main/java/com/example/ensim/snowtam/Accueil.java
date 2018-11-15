package com.example.ensim.snowtam;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        ImageButton addChamps = (ImageButton) this.findViewById(R.id.addChamps);
        FloatingActionButton valide = (FloatingActionButton) this.findViewById(R.id.valide);
        final EditText champs1=(EditText) this.findViewById(R.id.champs1);
        final EditText champs2=(EditText) this.findViewById(R.id.champs2);
        final EditText champs3=(EditText) this.findViewById(R.id.champs3);
        final EditText champs4=(EditText) this.findViewById(R.id.champs4);
        champs2.setVisibility(View.GONE);
        champs3.setVisibility(View.GONE);
        champs4.setVisibility(View.GONE);
        final ImageButton sup2 = (ImageButton) this.findViewById(R.id.sup2);
        final ImageButton sup3 = (ImageButton) this.findViewById(R.id.sup3);
        final ImageButton sup4 = (ImageButton) this.findViewById(R.id.sup4);
        sup2.setVisibility(View.GONE);
        sup3.setVisibility(View.GONE);
        sup4.setVisibility(View.GONE);

        final ArrayList<String> airports = new ArrayList<String>();



        addChamps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (champs2.getVisibility() == View.GONE) {
                    champs2.setVisibility(View.VISIBLE);
                    sup2.setVisibility(View.VISIBLE);
                } else if (champs3.getVisibility() == View.GONE) {
                    champs3.setVisibility(View.VISIBLE);
                    sup3.setVisibility(View.VISIBLE);
                } else if (champs4.getVisibility() == View.GONE) {
                    champs4.setVisibility(View.VISIBLE);
                    sup4.setVisibility(View.VISIBLE);
                }

            }
        });

        //quand on suprime 2 ou 3, 4 devient 3 pour changer de place..

        sup2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                champs2.setVisibility(View.GONE);
                sup2.setVisibility(View.GONE);
                champs2.setText("");
            }
        });

        sup3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                champs3.setVisibility(View.GONE);
                sup3.setVisibility(View.GONE);
                champs3.setText("");
            }
        });

        sup4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                champs4.setVisibility(View.GONE);
                sup4.setVisibility(View.GONE);
                champs4.setText("");
            }
        });


        valide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                airports.clear();

                if(champs1.getText().toString()!=""){
                    airports.add(champs1.getText().toString());
                }
                if (champs2.getVisibility() == View.VISIBLE) {
                    airports.add(champs2.getText().toString());
                }
                if (champs3.getVisibility() == View.VISIBLE) {
                    airports.add(champs3.getText().toString());
                }
                if (champs4.getVisibility() == View.VISIBLE) {
                    airports.add(champs4.getText().toString());
                }


                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;

                boolean OK=true;
                int i;
                //on verifie ici que les champs visibles sont remplis
                for(i=0; i<airports.size(); i++){
                    if(airports.get(i).length()==0)OK=false;
                }

                if (OK) {
                    Intent intent = new Intent(Accueil.this, Results.class);
                    intent.putExtra("airports", airports);
                    champs1.setText("");
                    champs2.setText("");
                    champs3.setText("");
                    champs4.setText("");

                    startActivity(intent);
                }
                else{
                    Toast.makeText(context, "Vous devez remplir les champs", duration).show();
                }


            }
        });




    }


}
