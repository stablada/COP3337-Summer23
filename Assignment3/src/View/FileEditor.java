package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class FileEditor extends JFrame {
    private JButton saveButton;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea textArea1;
    private JLabel lineCount;
    private JLabel pathLabel;
    private JLabel fileSizeLabel;
    private JPanel mainPanel;
    PrintWriter saveWriter;


    public FileEditor(String fileName, String path){
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle(fileName);
        this.setSize(800, 600);

        this.setVisible(true);

        pathLabel.setText(path);
        try {
            lineCount.setText(String.valueOf(Files.lines(Paths.get(path)).count()));
            fileSizeLabel.setText(String.valueOf(Files.size(Paths.get(path))) + " bytes");
            String fileText = Files.readString(Paths.get(path));
            textArea1.setText(fileText);
            saveWriter = new PrintWriter(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(null, "Would you like to save?", "Save", JOptionPane.YES_NO_OPTION);
                if(JOptionPane.YES_OPTION == 0){
                    saveWriter.println(textArea1.getText());
                    saveWriter.close();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchText(searchField.getText());
            }
        });
        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    lineCount.setText(String.valueOf(countLines(textArea1, 800)));
                }
            }
        });
    }

    public void searchText(String query){
        Scanner scanner = new Scanner(textArea1.getText());
        StringBuilder result = new StringBuilder("Found " + query + " at: ");
        int line = 1;
        while(scanner.hasNextLine()){
            String nextLine = scanner.nextLine();
            if(nextLine.contains(query)){
                result.append("\nLine ").append(line).append(", Index ").append(nextLine.indexOf(query));
            }
            line++;
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    public int countLines(javax.swing.JTextArea textArea, double max_width) {
        javax.swing.text.PlainDocument doc = (javax.swing.text.PlainDocument) textArea.getDocument();
        int counter = 0;
        for (int i = 0; i < textArea.getLineCount(); i++) {
            try {
                int init = textArea.getLineStartOffset(i);
                int length = textArea.getLineEndOffset(i) - init;
                counter += Math.ceil(textArea.getFontMetrics(textArea.getFont()).stringWidth(doc.getText(init, length)) / max_width);
            } catch (javax.swing.text.BadLocationException ex) {
                JOptionPane.showMessageDialog(null, "Error. Try again!");
            }
        }
        return counter;
    }
}
