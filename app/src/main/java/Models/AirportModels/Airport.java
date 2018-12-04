package Models.AirportModels;

import android.os.Parcel;
import android.os.Parcelable;

public class Airport implements Parcelable{
    private String ICAO_Code;
    private double latitude;
    private double longitude;
    private String snowtam;
    private String name;
    private String countryName;
    private String cityName;


    public Airport() {
    }



    public Airport(String ICAO_Code, double latitude, double longitude, String snowtam, String name, String countryName, String cityName) {
        this.ICAO_Code = ICAO_Code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.snowtam = snowtam;
        this.name=name;
        this.countryName=countryName;
        this.cityName=cityName;

    }



    public String getICAO_Code() {
        return ICAO_Code;
    }

    public void setICAO_Code(String ICAO_Code) {
        this.ICAO_Code = ICAO_Code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSnowtam() {
        return snowtam;
    }

    public void setSnowtam(String snowtam) {
        this.snowtam = snowtam;
    }



    protected Airport(Parcel in) {
        ICAO_Code = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        snowtam = in.readString();
        name = in.readString();
        countryName = in.readString();
        cityName=in.readString();
    }

    public static final Creator<Airport> CREATOR = new Creator<Airport>() {
        @Override
        public Airport createFromParcel(Parcel in) {
            return new Airport(in);
        }

        @Override
        public Airport[] newArray(int size) {
            return new Airport[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ICAO_Code);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(snowtam);
        parcel.writeString(name);
        parcel.writeString(countryName);
        parcel.writeString(cityName);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}