package Model;

import java.io.File;
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
    public void newText(String path, String name) {
        //Open stream and create new text file
    }
    public void newImg(String path, String name) {
        //Open stream and create new image file
    }
    public void newFolder(String path, String name) {
        //Open stream and create new folder directory
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
