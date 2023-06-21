package Controller;

import Model.FileManager;
import View.FileEditor;
import View.FileExplorer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Initialize the FileExplorer Window upon program start
        FileManager fileManager = new FileManager();
        FileExplorer fileExplorer = new FileExplorer();
        JOptionPane.showMessageDialog(null, "Welcome to File Explorer!" +
                "\n\nTo get started, click on a file or folder and then press OPEN to enter." +
                "\nTo go back to the previous directory, click the back button." +
                "\nTo create a new file, click the new button and follow the dialogue box instructions." +
                "\nTo view the properties of a file, click the properties button." +
                "\nTo edit the attributes of an existing file, click the properties button and edit the text fields." +
                "\n\nThank you for using File Explorer!");
    }
}