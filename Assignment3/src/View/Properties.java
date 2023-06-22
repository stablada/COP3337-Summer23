package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Properties extends JFrame {
    private JTextField fileNameField;
    private JTextField pathField;
    private JLabel fileTypeLabel;
    private JLabel sizeLabel;
    private JButton updateButton;
    private JPanel mainPanel;
    private JLabel nameLabel;

    public Properties(String name, String path, String type, String size, String basePath){
        this.setContentPane(mainPanel);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 400);
        this.setVisible(true);

        nameLabel.setText(name);
        pathField.setText(path);
        fileTypeLabel.setText(type);
        sizeLabel.setText(size);


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Would you like to update the file?", "Update", JOptionPane.YES_NO_OPTION);
                if(JOptionPane.YES_OPTION == 0){
                    File test = new File(path);
                    test.renameTo(new File(pathField.getText()));
                    JOptionPane.showMessageDialog(null, "File updated! \nPlease press back to see changes on the table.");

                    nameLabel.setText(pathField.getText().substring(pathField.getText().lastIndexOf("\\") + 1));
                    fileTypeLabel.setText(type);
                    sizeLabel.setText(size);
                }
            }
        });
    }
}
