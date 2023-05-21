package Model;

import View.MainFrame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Database.init();
        MainFrame mainFrame = new MainFrame();
    }
}