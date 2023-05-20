package Controller;

public class Manufacturer {
    private String name;
    private String address;

    public Manufacturer(String name, String address){
        this.name = name;
        this.address = address;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
}
