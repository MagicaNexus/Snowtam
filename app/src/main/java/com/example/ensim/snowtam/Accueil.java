package com.example.ensim.snowtam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import Model.ListAirportLocation;

public class Accueil extends AppCompatActivity {

    final ArrayList<Airport> listAirport = new ArrayList<Airport>();

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

        final ArrayList<String> airportsCode = new ArrayList<String>();


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
                airportsCode.clear();

                if(champs1.getText().toString()!=""){
                    airportsCode.add(champs1.getText().toString());
                }
                if (champs2.getVisibility() == View.VISIBLE) {
                    airportsCode.add(champs2.getText().toString());
                }
                if (champs3.getVisibility() == View.VISIBLE) {
                    airportsCode.add(champs3.getText().toString());
                }
                if (champs4.getVisibility() == View.VISIBLE) {
                    airportsCode.add(champs4.getText().toString());
                }


                //requete a l'api

                for (String codeICAO:airportsCode) {

                    final Airport ap=new Airport();

                    Response.Listener<ListAirportLocation> responseListener = new Response.Listener<ListAirportLocation>() {
                        @Override
                        public void onResponse(ListAirportLocation response) {
                            ap.setLatitude(response.getData().get(0).getLatitude());
                            ap.setLongitude(response.getData().get(0).getLongitude());
                            ap.setName(response.getData().get(0).getAirport_name());
                            listAirport.add(ap);
                        }
                    };
                    Response.ErrorListener errorListener = new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Airporterreur", error.toString());
                        }
                    };
                    APIService.INSTANCE.searchLocation(codeICAO, responseListener, errorListener, v.getContext());

                }

                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;

                boolean OK=true;
                int i;

                //on verifie ici que les champs visibles sont remplis

                //!!!!!!!!!!il faudra aussi verifier que ce n'est que 4 lettres VALIDES
                for(i=0; i<airportsCode.size(); i++){
                    if(airportsCode.get(i).length()==0)OK=false;
                }

                if (OK) {

                    Intent intent = new Intent(Accueil.this, Results.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("airports",listAirport);
                    intent.putExtras(bundle);
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
