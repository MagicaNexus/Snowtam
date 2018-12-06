package com.example.ensim.snowtam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import Models.AirportModels.Airport;
import Models.AirportModels.ListAirportLocation;
import Models.AirportModels.ListAirportSnowtam;
import Services.SnowtamAPI.APIService;

public class HomeActivity extends AppCompatActivity {

    public static final ArrayList<Airport> listAirport = new ArrayList<Airport>();
    boolean OK=true;
    int cpteur=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ImageButton addChamps = (ImageButton) this.findViewById(R.id.addChamps);
        ImageButton valide = (ImageButton) this.findViewById(R.id.valide);
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



        ArrayList<Airport> listAirportGet=new ArrayList<Airport>();
        Intent intent = getIntent();
        if(intent!=null){
            listAirportGet= intent.getParcelableArrayListExtra("listAirport");
            if (intent != null && listAirportGet !=null){
                for(int x=0;x<listAirportGet.size();x++) {
                    if(x==0) {
                        champs1.setText(listAirportGet.get(x).getICAO_Code());
                        champs2.setVisibility(View.VISIBLE);
                        sup2.setVisibility(View.VISIBLE);

                    }
                    if(x==1){
                        champs2.setText(listAirportGet.get(x).getICAO_Code());
                        champs3.setVisibility(View.VISIBLE);
                        sup3.setVisibility(View.VISIBLE);

                    }
                    if(x==2){
                        champs3.setText(listAirportGet.get(x).getICAO_Code());
                        champs4.setVisibility(View.VISIBLE);
                        sup4.setVisibility(View.VISIBLE);

                    }
                    if(x==3){
                        champs4.setText(listAirportGet.get(x).getICAO_Code());
                    }
                    Log.d("machin", "truc : " + listAirportGet.get(x).getICAO_Code());
                }
            }
        }


        final ArrayList<String> airportsCode = new ArrayList<String>();

        final ProgressBar spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

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
                    addChamps.setVisibility(View.INVISIBLE);
                }

            }
        });


        //quand on suprime 2 ou 3, 4 devient 3 pour changer de place..

        sup2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                champs2.setVisibility(View.GONE);
                sup2.setVisibility(View.GONE);
                champs2.setText("");
                addChamps.setVisibility(View.VISIBLE);
            }
        });

        sup3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                champs3.setVisibility(View.GONE);
                sup3.setVisibility(View.GONE);
                champs3.setText("");
                addChamps.setVisibility(View.VISIBLE);
            }
        });

        sup4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                champs4.setVisibility(View.GONE);
                sup4.setVisibility(View.GONE);
                champs4.setText("");
                addChamps.setVisibility(View.VISIBLE);
            }
        });



        valide.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                OK=true;
                spinner.setVisibility(View.VISIBLE);
                cpteur=0;

                final Context context = getApplicationContext();
                final int duration = Toast.LENGTH_SHORT;

                airportsCode.clear();
                listAirport.clear();

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
                int i;

                //on verifie ici que les champs visibles sont remplis
                for(i=0; i<airportsCode.size(); i++){
                    if(airportsCode.get(i).length()==0)OK=false;
                }

                for (final String codeICAO:airportsCode) {
                    cpteur++;
                    final Airport ap=new Airport();


                    Response.Listener<ListAirportSnowtam> responseListener = new Response.Listener<ListAirportSnowtam>() {
                        @Override
                        public void onResponse(ListAirportSnowtam response) {

                            //on prend le premier snowtam dans la liste, c'est a dire le dernier en date
                            int k=0;
                            while(ap.getSnowtam()==null && k<response.getData().size()){
                                if(response.getData().get(k).getAll().contains("SNOWTAM")){
                                   // Log.d("AirportSnowtam",response.getData().get(k).getAll());
                                    ap.setSnowtam(response.getData().get(k).getAll());
                                }
                                k++;
                            }

                            if(ap.getSnowtam()==null){
                                ap.setSnowtam(getString(R.string.snowtam_err));
                            }

                            Response.Listener<ListAirportLocation> responseListener2 = new Response.Listener<ListAirportLocation>() {
                                @Override
                                public void onResponse(ListAirportLocation response) {
                                    if(response.getData().size()>0) {
                                        ap.setLatitude(response.getData().get(0).getLatitude());
                                        ap.setLongitude(response.getData().get(0).getLongitude());
                                        ap.setName(response.getData().get(0).getAirport_name());
                                        ap.setCountryName(response.getData().get(0).getCountryName());
                                        ap.setCityName(response.getData().get(0).getCityName());
                                        ap.setICAO_Code(codeICAO);
                                        listAirport.add(ap);
                                    }
                                        if (listAirport.size() >= airportsCode.size()) {

                                            if (OK) {
                                                Intent intent = new Intent(HomeActivity.this, ResultsActivity.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putParcelableArrayList("airports", listAirport);
                                                intent.putExtras(bundle);
                                                champs1.setText("");
                                                champs2.setText("");
                                                champs3.setText("");
                                                champs4.setText("");

                                                if (listAirport.size()>0) {

                                                    startActivity(intent);
                                                    champs2.setVisibility(View.GONE);
                                                    champs3.setVisibility(View.GONE);
                                                    champs4.setVisibility(View.GONE);
                                                    sup2.setVisibility(View.GONE);
                                                    sup3.setVisibility(View.GONE);
                                                    sup4.setVisibility(View.GONE);
                                                    spinner.setVisibility(View.GONE);
                                                }

                                            }
                                        }
                                        else{
                                            if((cpteur==airportsCode.size()&&listAirport.size()==0)) {
                                                Toast.makeText(context, "ICAO code invalid, or no response from the API", duration).show();
                                                spinner.setVisibility(View.GONE);
                                            }
                                        }

                                }
                            };
                            Response.ErrorListener errorListener2 = new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("Airporterreur", error.toString());
                                }
                            };
                            APIService.INSTANCE.searchLocation(codeICAO, responseListener2, errorListener2, v.getContext());
                        }
                    };
                    Response.ErrorListener errorListener=new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("SnowtamErreur",error.toString());
                            if(error.toString().contains("No address associated with hostname")) {
                                Toast.makeText(context, "No internet connection", duration).show();
                                spinner.setVisibility(View.GONE);
                            }
                        }
                    };
                    APIService.INSTANCE.searchAirportSnowtam(codeICAO, responseListener, errorListener,v.getContext());


                }


                if(!OK){
                    Toast.makeText(context, "Please complete the field(s) below", duration).show();
                    spinner.setVisibility(View.GONE);
                }

            }
        });




    }




}
