import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainWindow extends JFrame {
    Reader reader = new Reader();

    MainWindow(Game game){
        reader.getPrefSize();
        this.setTitle("Sokoban");
        this.setSize(new Dimension(reader.prefWidth, reader.prefHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.add(game);
        this.addKeyListener(game.keys);
        this.setVisible(true);
        game.start();
    }

    public void makeLevel(int levelNr) throws IOException {
        this.dispose();
        Game gam = new Game(levelNr);
    }
}
