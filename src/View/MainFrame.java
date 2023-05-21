package View;

import Controller.Manufacturer;
import Controller.Product;
import Model.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JTabbedPane navTabs;
    private JPanel mainPanel;
    private JTextField searchTF;
    private JButton locateButton;
    private JTable locateTable;
    private JTable inventoryTable;
    private JButton addButton;
    private JButton helpButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JTable deletedTable;
    private JPanel locatePanel;
    private JPanel inventoryPanel;
    private JPanel deletedPanel;
    private JLabel notFoundLB;

    private String[] invCNames = {"Product", "Purchase Date", "Quantity", "Price", "Manufacturer", "State"};
    private String[] locateCNames = {"Product", "Price", "Quantity"};
    private String[] deletedCNames = {"Product", "Date", "Manufacturer"};

    public MainFrame(){
        this.setContentPane(mainPanel);

        this.setResizable(false);
        this.setTitle("ABC Enterprise Inventory Management System");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        updateInventoryTable();
        updateDeletedTable();

        this.notFoundLB.setVisible(false);

        ImageIcon icon = new ImageIcon("src\\View\\icon.png");
        this.setIconImage(icon.getImage());

        locateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchTF.getText();
                //search for product
                if(Database.query(search) != null){
                    notFoundLB.setVisible(false);
                    ArrayList<Product> products = Database.query(search);
                    String[][] data = new String[products.size()][3];

                    for(int i = 0; i < products.size(); i++){
                        data[i][0] = products.get(i).getName();
                        data[i][1] = Double.toString(products.get(i).getPrice());
                        data[i][2] = Integer.toString(products.get(i).getQuantity());
                    }

                    locateTable.setModel(new DefaultTableModel(data, locateCNames));

                }else{
                    notFoundLB.setVisible(true);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //I apologize for the inefficiency; I wanted to do input validation, but I couldn't figure out how to do it
                //more efficiently than this brute force method. I suppose I could have made a method for it, but I didn't think it was necessary.

                String name;
                while (true) {
                    try {
                        name = JOptionPane.showInputDialog("Enter product name:");
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: Bad input. Please input a valid string.");
                    }
                }

                int quantity;
                while (true) {
                    try {
                        quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: Bad input. Please input a valid integer.");
                    }
                }

                double price;
                while (true) {
                    try {
                        price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: Bad input. Please input a valid double.");
                    }
                }

                String date;
                while (true) {
                    try {
                        date = JOptionPane.showInputDialog("Enter date purchased (mm/dd/yyyy):");
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: Bad input. Please input a valid string.");
                    }
                }

                String manufacturerName;
                while (true) {
                    try {
                        manufacturerName = JOptionPane.showInputDialog("Enter manufacturer name:");
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: Bad input. Please input a valid string.");
                    }
                }

                String manufacturerAddress;
                while (true) {
                    try {
                        manufacturerAddress = JOptionPane.showInputDialog("Enter manufacturer state abbreviation OR country:");
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: Bad input. Please input a valid string.");
                    }
                }
                Database.add(new Product(new Manufacturer(manufacturerName, manufacturerAddress), name, date, quantity, price));
                updateInventoryTable();
                updateDeletedTable();
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello!"
                +"\nThis is the help menu for the ABC Enterprise Inventory Management System."
                +"\nTo add a product, click the \"Add\" button. You will be prompted to enter the product's name, quantity, price, date purchased, manufacturer name, and manufacturer address."
                +"\nTo delete a product, click the \"Delete\" button. You will be prompted to enter the product's name."
                +"\nTo save the database, click the \"Save\" button."
                +"\nThis Program was made by Sebastian Tablada, PID:6326595. Feel free to contact me at stablada67@gmail.com or send me a message through canvas for any inquiries or concerns."
                +"\nThank you for using the ABC Enterprise Inventory Management System!");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name;
                while (true) {
                    try {
                        name = JOptionPane.showInputDialog("Enter product name:");
                        break;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: Bad input. Please input a valid string.");
                    }
                }
                if (Database.search(name) > -1) {
                    Database.delete(Database.search(name));
                }
                else{JOptionPane.showMessageDialog(null, "Error: Product not found.");}
                updateInventoryTable();
                updateDeletedTable();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Database.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void updateInventoryTable(){
        String[][] data = new String[Database.getInventory().size()][6];

        for(int i = 0; i < Database.getInventory().size(); i++){
            data[i][0] = Database.getInventory().get(i).getName();
            data[i][1] = Database.getInventory().get(i).getDate();
            data[i][2] = Integer.toString(Database.getInventory().get(i).getQuantity());
            data[i][3] = Double.toString(Database.getInventory().get(i).getPrice());
            data[i][4] = Database.getInventory().get(i).getManufacturer().getName();
            data[i][5] = Database.getInventory().get(i).getManufacturer().getAddress();
        }

        inventoryTable.setModel(new DefaultTableModel(data, invCNames));
    }

    public void updateDeletedTable(){
        String[][] data = new String[Database.getDeleted().size()][6];

        for(int i = 0; i < Database.getDeleted().size(); i++){
            data[i][0] = Database.getDeleted().get(i).getName();
            data[i][1] = Database.getDeleted().get(i).getDate();
            data[i][2] = Integer.toString(Database.getDeleted().get(i).getQuantity());
            data[i][3] = Double.toString(Database.getDeleted().get(i).getPrice());
            data[i][4] = Database.getDeleted().get(i).getManufacturer().getName();
            data[i][5] = Database.getDeleted().get(i).getManufacturer().getAddress();
        }

        deletedTable.setModel(new DefaultTableModel(data, deletedCNames));
    }
}
