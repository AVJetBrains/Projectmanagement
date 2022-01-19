package entities;

public class Restaurant {
    private String Name;
    private String location;
    private double id;

    public Restaurant(String Name , String location , double id){
        this.Name = Name;
        this.location = location;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public String getLocation() {
        return location;
    }

    public double getId() {
        return id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(double id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return this.getName()+ "Restaurant -> ID:"+ this.getId()+" ,Location:"+ getLocation();
    }
}
