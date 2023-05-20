package Controller;

import java.util.Date;

public class Product {
    private Manufacturer manufacturer;
    private String name;
    private String date;
    private int quantity;
    private double price;

    public Product(Manufacturer manufacturer, String name, String date, int quantity, double price){
        this.manufacturer = manufacturer;
        this.name = name;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }
    public Manufacturer getManufacturer(){
        return manufacturer;
    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }
    public int getQuantity(){
        return quantity;
    }
    public double getPrice(){
        return price;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setPrice(double price){
        this.price = price;
    }
}
