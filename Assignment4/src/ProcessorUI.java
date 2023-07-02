import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ProcessorUI extends JFrame {
    private JButton selectFileButton;
    private JButton checkButton;
    private JPanel contentPane;
    private JLabel fileNameLabel;
    private JFileChooser fileChooser = new JFileChooser();
    private File file;

    ProcessorUI(){
        this.setContentPane(contentPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Processor");
        this.setSize(400, 200);

        this.setVisible(true);

        fileNameLabel.setText("No file selected.");

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int status = fileChooser.showOpenDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                    if (fileChooser.getSelectedFile().isFile()) {
                        file = fileChooser.getSelectedFile();
                        fileNameLabel.setText(file.getName());
                    }
                }
            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Preprocessor preprocessor = new Preprocessor(file);
                String returnMessage = "";
                if(preprocessor.checkDelimiters() && preprocessor.checkSyntax()){
                    returnMessage += "Test passed.\n";
                } else {
                    returnMessage += "Test failed.\n";
                }
                if(preprocessor.checkDelimiters()){
                    returnMessage += "Delimiters test passed.\n";
                } else {
                    returnMessage += "Delimiters test failed.\n";
                }
                if (preprocessor.checkSyntax()){
                    returnMessage += "Syntax test passed.";
                } else {
                    returnMessage += "Syntax test failed.";
                }
                JOptionPane.showMessageDialog(null, returnMessage);
            }
        });
    }
}
