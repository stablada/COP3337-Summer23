package View;

import javax.swing.*;

public class ImgEditor extends JFrame {
    public ImgEditor(String path){
        JFrame f = new JFrame("Image Viewer");

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(400, 400);
        this.setVisible(true);

        ImageIcon img = new ImageIcon(path);
        f.add(new JLabel(img));
        f.pack();
        f.setVisible(true);
    }
}
