package Models.AirportModels;

import java.util.ArrayList;

import Models.AirportModels.AirportSnowtam;

public class ListAirportSnowtam {

    private int total;
    private ArrayList<AirportSnowtam> data;


    public ArrayList<AirportSnowtam> getData() {
        return data;
    }

    public void setData(ArrayList<AirportSnowtam> data) {
        this.data = data;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
