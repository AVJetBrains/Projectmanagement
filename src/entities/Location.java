package entities;

public class Location {
    private String City;
    private String Country;

    public Location(String city , String country){
        this.City = city;
        this.Country = country;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    @Override
    public String toString(){
        return this.getCity() + "Country ="+ this.getCountry();
    }
}
