package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileExplorer extends JFrame {
    private JButton backButton;
    private JButton openButton;
    private JButton deleteButton;
    private JButton copyButton;
    private JButton propertiesButton;
    private JButton helpButton;
    private JButton newButton;
    private JButton importFileButton;
    private JTextField pathTextField;
    private JPanel mainPanel;
    private JTable FileTable;
    private JButton searchButton;
    private JTextField searchTextField;
    public static String basePath;

    private static String[] columnNames = {"Name", "Type", "Size"};

    public FileExplorer() {
        this.setContentPane(mainPanel);

        this.setTitle("File Explorer");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //Initialize the table upon program start
        //Change Ass3 to .\\files
        populateTable(".\\Assignment3\\Files\\root");


        //--------------------Button Action Listeners--------------------
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] fileType = {"Text", "Image", "Directory"};

                int n = JOptionPane.showOptionDialog(null,
                        "Which file type would you like to add?",
                        "Add file",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        fileType,
                        fileType[0]);

                String name = JOptionPane.showInputDialog("Enter the name of the file: ");
                String path = pathTextField.getText();

                switch (n) {
                    case 0:
                        Model.FileManager.newText(name, path);
                        break;
                    case 1:
                        Model.FileManager.newImg(name, path);
                        break;
                    case 2:
                        Model.FileManager.newFolder(name, path);
                        break;
                }
                populateTable(path);
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (FileTable.getValueAt(FileTable.getSelectedRow(), 1).toString()) {
                    case "directory":
                        populateTable(pathTextField.getText() + "\\" + FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString());
                        break;
                    case "text/plain":
                        JOptionPane.showMessageDialog(null, "Warning! \nDon't forget to click save before you close the window! \nFailure to do this will result in data loss!");
                        FileEditor fileEditor = new FileEditor(FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString(), pathTextField.getText() + "\\" + FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString());
                        break;
                    case "image/png":
                        ImgEditor imgEditor = new ImgEditor(pathTextField.getText() + "\\" + FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString());
                        break;
                }
            }
        });
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = JOptionPane.showInputDialog("Enter the path to copy to: \nRemember .\\Files\\root is the root directory. \n[Do not include filename] \n\nExample: .\\Files\\root\\testAssets");
                try {
                    File file = new File(path + "\\" + FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString());
                    Files.copy(new File(pathTextField.getText() + "\\" + FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString()).toPath(), new File(path + "\\" + FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString()).toPath());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString(), "Delete File", JOptionPane.YES_NO_OPTION);
                if(JOptionPane.YES_OPTION == 0) {
                    try {
                        Files.delete(new File(pathTextField.getText() + "\\" + FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString()).toPath());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    populateTable(pathTextField.getText());
                }
            }
        });
        propertiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Properties properties = new Properties(
                        /*name*/ FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString(),
                        /*path*/ pathTextField.getText() + "\\" + FileTable.getValueAt(FileTable.getSelectedRow(), 0).toString(),
                        /*type*/ FileTable.getValueAt(FileTable.getSelectedRow(), 1).toString(),
                        /*size*/ FileTable.getValueAt(FileTable.getSelectedRow(), 2).toString(),
                        /*basePath*/basePath = pathTextField.getText()
                );
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Welcome to File Explorer!" +
                        "\n\nTo get started, click on a file or folder and then press OPEN to enter." +
                        "\nTo go back to the previous directory, click the back button." +
                        "\nTo create a new file, click the new button and follow the dialogue box instructions." +
                        "\nTo view the properties of a file, click the properties button." +
                        "\nTo edit the attributes of an existing file, click the properties button and edit the text fields." +
                        "\n\nThank you for using File Explorer!");
            }
        });
        importFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = JOptionPane.showInputDialog("Enter the path to import from: \nRemember .\\Files\\root is the root directory. \n[Include filename] \n\nExample: .\\Files\\root\\testAssets\\test.txt");
                File file = new File(path);
                try {
                    Files.copy(file.toPath(), new File(pathTextField.getText() + "\\" + file.getName()).toPath());
                    JOptionPane.showMessageDialog(null, "File imported successfully!");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "File import failed!");
                    e1.printStackTrace();
                }
                populateTable(pathTextField.getText());
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = pathTextField.getText();
                if(path.contains("\\")) {
                    path = path.substring(0, path.lastIndexOf("\\"));
                }
                populateTable(path);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<File> files = Model.FileManager.queryNameFiles(searchTextField.getText());

                StringBuilder out = new StringBuilder("Found " + files.size() + " files containing the name '" + searchTextField.getText() + "'.");
                for(File file : files){
                    out.append("\n").append(file.getName()).append(" at ").append(file.getPath());
                }

                JOptionPane.showMessageDialog(null, out.toString());
            }
        });
    }

    public void populateTable(String path) {
        ArrayList<File> files = Model.FileManager.queryPathFiles(path);

        pathTextField.setText(path);

        String[] names = new String[files.size()];
        String[] types = new String[files.size()];
        String[] sizes = new String[files.size()];
        String[][] data = new String[files.size()][3];

        for (int i = 0; i < files.size(); i++) {
            names[i] = files.get(i).getName();
            try {
                if(Files.probeContentType(files.get(i).toPath()) == null) {
                    types[i] = "directory";
                } else {
                    types[i] = Files.probeContentType(files.get(i).toPath());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                sizes[i] = Files.size(Path.of(files.get(i).getPath())) + " Bytes";
            } catch (IOException e) {
                throw new RuntimeException(e);
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
