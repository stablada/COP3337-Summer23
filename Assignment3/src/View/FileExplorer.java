package View;

import Model.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileExplorer extends JFrame {
    private JButton backButton;
    private JButton openButton;
    private JButton deleteButton;
    private JButton copyButton;
    private JButton propertiesButton;
    private JButton quickWriteButton;
    private JButton newButton;
    private JButton importFileButton;
    private JTextField pathTextField;
    private JPanel mainPanel;
    private JTable FileTable;
    private JButton searchButton;
    private JTextField searchTextField;

    private String[] columnNames = {"Name", "Type", "Size"};

    public FileExplorer() {
        this.setContentPane(mainPanel);

        this.setTitle("File Explorer");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //Initialize the table upon program start
        //Change Ass3 to .\\files
        try {
            populateTable(".\\Assignment3\\Files\\root");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //--------------------Button Action Listeners--------------------
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open JOP NewFile
                //Options: Text, Image, Folder
                //JOP: create New or Import
                //If New
                //FileManager.newText / FileManager.newImage / FileManager.newFolder
                //If import
                //FileManager.importFile

                //loadTreeContent();
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Switch: File or Folder
                //File: if txt open FileEditor
                //      if img open ImageShowcase
                //Folder: Populate Table with new data
            }
        });
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOP: Copy where? (input path)
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOP: Are you sure?
                //if yes
                //FileManager.deleteFile(path)
            }
        });
        propertiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOP: File Properties (all basicFile attributes)
            }
        });
        quickWriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open QuickWrite
            }
        });
        importFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOP: Import what and where? (input file and path) [copies file from path to path]
            }
        });
    }

    public void populateTable(String path) throws IOException {
        ArrayList<File> files = Model.FileManager.queryPathFiles(path);

        String[] names = new String[files.size()];
        String[] types = new String[files.size()];
        String[] sizes = new String[files.size()];
        String[][] data = new String[files.size()][3];

        for (int i = 0; i < files.size(); i++) {
            names[i] = files.get(i).getName();
            sizes[i] = files.get(i).getTotalSpace() + " Bytes";
            if(Files.probeContentType(files.get(i).toPath()) == null) {
                types[i] = "directory";
            } else {
                types[i] = Files.probeContentType(files.get(i).toPath());
            }
        }
        for(int i = 0; i < files.size(); i++) {
            data[i][0] = names[i];
            data[i][1] = types[i];
            data[i][2] = sizes[i];
        }
        FileTable.setModel(new DefaultTableModel(data, columnNames));
    }
}
