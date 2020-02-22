import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class Photo implements Printable{
    private String photoURL;

    public Photo(String photoURL) {
        this.photoURL = photoURL;
    }

    public void print() {
        JFrame frame =  new JFrame();
        try {
            JLabel photoLabel = new JLabel(new ImageIcon(ImageIO.read(new URL(photoURL))));
            frame.add(photoLabel);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch(IOException ex){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Photo dog = new Photo("https://i.imgur.com/chpHhVG.jpg");
        dog.print();
    }

}
