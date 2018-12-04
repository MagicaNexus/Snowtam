package Models.SnowtamModels;


import java.util.ArrayList;

import Models.AirportModels.Airport;

public class SnowtamSingleton {
    private static SnowtamSingleton instance;
    private SnowtamSingleton(){}
    public ArrayList<Airport> getListAirport() {
        return listAirport;
    }

    public void setListAirport(ArrayList<Airport> listAirport) {
        this.listAirport = listAirport;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private ArrayList<Airport> listAirport;
    private int index;
    private String icon;

    public static synchronized SnowtamSingleton getInstance(){
        if(instance==null){
            instance=new SnowtamSingleton();
        }
        return instance;
    }


}
