package Models.AirportModels;

import java.util.ArrayList;

public class ListAirportLocation {

    private int total;
    private ArrayList<AirportLocation> data;


    public ArrayList<AirportLocation> getData() {
        return data;
    }

    public void setData(ArrayList<AirportLocation> data) {
        this.data = data;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
