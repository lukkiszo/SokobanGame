import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    MainWindow(Game game){
        this.setTitle("Sokoban");
        this.setSize(new Dimension(600,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.add(game);
        this.setVisible(true);
//        game.start();
    }
}
