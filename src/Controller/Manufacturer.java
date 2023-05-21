package Controller;

import java.io.Serializable;

public class Manufacturer implements Serializable {
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
