package Model;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public enum APIService {
    INSTANCE;

    public void searchLocation(String AirportCode, Response.Listener responseListener, Response.ErrorListener errorListener, Context context){

        final String url="https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/airports/locations/" +
                "international-list?api_key=c2ff65c0-ec95-11e8-acf9-1d6bfa3c323d&format=json&airports="+AirportCode+"&states=";


        RequestQueue queue= Volley.newRequestQueue(context);
        GsonRequest<ListAirportLocation> request= new GsonRequest<>(url,ListAirportLocation.class, null, responseListener, errorListener);
        queue.add(request);
    }

    public void searchAirportSnowtam(String codeICAO, Response.Listener respListener, Response.ErrorListener errorListener, Context context){
        final String url="https://v4p4sz5ijk.execute-api.us-east-1.amazonaws.com/anbdata/states/notams/notams-realtime-list?" +
                "api_key=c2ff65c0-ec95-11e8-acf9-1d6bfa3c323d&format=json&criticality=&locations="+codeICAO;

        RequestQueue queue = Volley.newRequestQueue(context);
        GsonRequest<ListAirportSnowtam> request = new GsonRequest<>(url,ListAirportSnowtam.class,null,respListener,errorListener);
        queue.add(request);

    }
}
