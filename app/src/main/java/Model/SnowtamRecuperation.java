package Model;


import java.util.ArrayList;

public class SnowtamRecuperation{
    private static SnowtamRecuperation instance;
    private SnowtamRecuperation(){}
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

    private ArrayList<Airport> listAirport;
    private int index;

    public static synchronized SnowtamRecuperation getInstance(){
        if(instance==null){
            instance=new SnowtamRecuperation();
        }
        return instance;
    }


}
