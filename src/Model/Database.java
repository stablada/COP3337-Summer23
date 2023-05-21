package Model;

import Controller.Product;

import java.io.*;
import java.util.ArrayList;

public class Database implements Serializable {
    //Inventory
    private static ArrayList<Product> products = new ArrayList<Product>();
    //Deleted
    private static ArrayList<Product> deleted = new ArrayList<Product>();

    public static void init() throws IOException, ClassNotFoundException {
        try {
            File save = new File("inventory.db");
            if (!save.createNewFile()) {
                FileInputStream is1 = new FileInputStream("inventory.db");
                ObjectInputStream is2 = new ObjectInputStream(is1);
                products = (ArrayList<Product>) is2.readObject();
                is2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File save = new File("deleted.db");
            if (!save.createNewFile()) {
                FileInputStream is1 = new FileInputStream("deleted.db");
                ObjectInputStream is2 = new ObjectInputStream(is1);
                deleted = (ArrayList<Product>) is2.readObject();
                is2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void add(Product product){
        products.add(product);
    }
    public static void delete(int index){
        deleted.add(products.get(index));
        products.remove(index);
    }
    public static void close() throws IOException {
        FileOutputStream os1 = new FileOutputStream("inventory.db");
        ObjectOutputStream os2 = new ObjectOutputStream(os1);
        os2.writeObject(products);
        os2.close();

        FileOutputStream os3 = new FileOutputStream("deleted.db");
        ObjectOutputStream os4 = new ObjectOutputStream(os3);
        os4.writeObject(deleted);
        os4.close();
    }
    public static int search(String name){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getName().equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Product> query(String keyword){
        keyword = keyword.toLowerCase();
        ArrayList<Product> output = new ArrayList<Product>();
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getName().toLowerCase().contains(keyword)){
                output.add(products.get(i));
            }
        }
        if (output.size() == 0)
            return null;
        else
            return output;
    }

    public static ArrayList<Product> getInventory(){
        return products;
    }
    public static ArrayList<Product> getDeleted(){
        return deleted;
    }
}
