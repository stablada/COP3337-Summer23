package Controller;

import Model.FileManager;
import View.FileEditor;
import View.FileExplorer;

public class Main {
    public static void main(String[] args) {
        //Initialize the FileExplorer Window upon program start
        FileManager fileManager = new FileManager();
        FileExplorer fileExplorer = new FileExplorer();
    }
}