package Model;

public class AirportLocation {

    private String airport;
    private float visibility;
    private float wind;
    private float precipitation;
    private float freezing;
    private float dangerous;
    private float VMC_IMC;
    private String date;
    private String airport_name;
    private double latitude;
    private double longitude;
    private String countryCode;
    private String raw_metar;
    private String datetime;


    // Getter Methods

    public String getAirport() {
        return airport;
    }

    public float getVisibility() {
        return visibility;
    }

    public float getWind() {
        return wind;
    }

    public float getPrecipitation() {
        return precipitation;
    }

    public float getFreezing() {
        return freezing;
    }

    public float getDangerous() {
        return dangerous;
    }

    public float getVMC_IMC() {
        return VMC_IMC;
    }

    public String getDate() {
        return date;
    }

    public String getAirport_name() {
        return airport_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getRaw_metar() {
        return raw_metar;
    }

    public String getDatetime() {
        return datetime;
    }

    // Setter Methods

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public void setPrecipitation(float precipitation) {
        this.precipitation = precipitation;
    }

    public void setFreezing(float freezing) {
        this.freezing = freezing;
    }

    public void setDangerous(float dangerous) {
        this.dangerous = dangerous;
    }

    public void setVMC_IMC(float VMC_IMC) {
        this.VMC_IMC = VMC_IMC;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAirport_name(String airport_name) {
        this.airport_name = airport_name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setRaw_metar(String raw_metar) {
        this.raw_metar = raw_metar;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
