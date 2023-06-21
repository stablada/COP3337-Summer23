package View;

import Model.BasicFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FileEditor extends JFrame {
    private JButton saveButton;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea textArea1;
    private JLabel lineCount;
    private JLabel pathLabel;
    private JLabel fileSizeLabel;
    private JPanel mainPanel;

    public FileEditor(String fileName, String path){
        this.setContentPane(mainPanel);

        this.setTitle(fileName);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //private void updateLineCount() {
            //update lineCount
        //}

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open JOP SaveFile
                //If yes
                //Filemanager.save
                //Else
                //Do nothing
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //search function through SearchField.getText()
            }
        });
        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    //garbage code pls fix
                    lineCount.setText(String.valueOf(lineCount.getText()) + 1);
                }
            }
        });
    }
}
