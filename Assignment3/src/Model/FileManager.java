package Model;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileManager {
    private static ArrayList<File> files = new ArrayList<>();

    //REMEMBER TO CHANGE PATH TO .\\Files\\root
    private static File rootDir = new File(".\\Assignment3\\Files\\root");

    public FileManager() {
        directorySearch(rootDir);
    }

    public void directorySearch(File directory) {
        File[] dirContent = directory.listFiles();
        for (File file : dirContent) {
            files.add(file);
            if (file.listFiles() != null)
                directorySearch(file);
        }
    }

    public void importFile(String path) {
        //find file and import it into the program
        //if exists import
        //else jop error
    }
    public static void newText(String name, String path) {
        File newFile = new File(path + "\\" + name + ".txt");
        try {
            if (newFile.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Text File " + name + " created successfully at " + path + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Error: \nText File " + name + " already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void newImg(String name, String path) {
        File newImg = new File(path + "\\" + name + ".png");
        try {
            if (newImg.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Image File " + name + " created successfully at " + path + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Error: \nImage File " + name + " already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void newFolder(String name, String path) {
        File newDir = new File(path + "\\" + name);
        if (newDir.mkdir()) {
            JOptionPane.showMessageDialog(null, "Directory " + name + " created successfully at " + path + ".");
        } else {
            JOptionPane.showMessageDialog(null, "Error: \nDirectory " + name + " already exists.");
        }
    }

    public ArrayList<File> getFiles() {
        return files;
    }
    public static ArrayList<File> queryPathFiles(String query) {
        ArrayList<File> queryFiles = new ArrayList<>();
        File directory = null;
        try {
            directory = new File(query);
        } catch (Exception e) {
            //JOP: Err not a valid path
        }
        File[] dirContent = directory.listFiles();
        for (File file : dirContent) {
            queryFiles.add(file);
        }
        return queryFiles;
    }
    public static ArrayList<File> queryNameFiles(String query) {
        ArrayList<File> queryFiles = new ArrayList<>();
        for (File file : files) {
            if (file.getName().contains(query)) {
                queryFiles.add(file);
            }
        }
        return queryFiles;
    }
}
