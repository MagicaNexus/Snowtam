package Services.WeatherAPI;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Models.AirportModels.Airport;
import Models.SnowtamModels.SnowtamSingleton;

public class WeatherHttpClient {

    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String API_KEY = "&APPID=16083ffe25c6bd560cb8f46caec47e15";

    SnowtamSingleton recup = SnowtamSingleton.getInstance();
    int index = recup.getIndex();
    ArrayList<Airport> listairport = recup.getListAirport();
    String latitude = Double.toString(listairport.get(index).getLatitude());
    String longitude = Double.toString(listairport.get(index).getLongitude());

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";


    public String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + "lat=" + latitude + "&lon=" + longitude + API_KEY)).openConnection();
            Log.d("HTTPCLIENT Localisation", BASE_URL + "lat=" + latitude + "&lon=" + longitude + API_KEY);
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    public byte[] getImage(String code) {
        SnowtamSingleton recup = SnowtamSingleton.getInstance();
        Log.d("HTTPCLIENT ICON", code);
        recup.setIcon(code);
        return null;
    }
}
