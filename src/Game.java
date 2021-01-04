import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game extends JComponent {
    private Walls walls = new Walls();
    private Player player = new Player();
    private Obstacle obstacle = new Obstacle();

    private boolean running = false;

    public Game() throws IOException {
    }


    @Override
    public void paintComponent(Graphics g) {

        walls.paintComponent(g);
        player.paintComponent(g);
        obstacle.paintComponent(g);

    }
}
