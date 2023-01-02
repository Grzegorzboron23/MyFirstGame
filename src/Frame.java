import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
Panel panel;
    Frame(){
        panel = new Panel();

        this.add(panel);
        this.setTitle("Game Pong");
        this.setBackground(Color.black);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
