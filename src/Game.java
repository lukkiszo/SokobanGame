import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game extends JComponent implements Runnable{
    public int levelNumber = 1;
    private Walls walls;
    private Player player;
    private Obstacle obstacle;
    public Keys keys;
    Thread thread;

    private boolean running = false;
    private MainWindow mainWindow;

    public Game() throws IOException {
        walls = new Walls(levelNumber);
        player = new Player(levelNumber);
        obstacle = new Obstacle(levelNumber);
        keys = new Keys(player, this);
        mainWindow = new MainWindow(this);
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
        System.out.println("START");
    }

    @Override
    public void run() {
        while (running)
        {
            update();
            repaint();
        }
        stop();
//        update();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(){
//        System.out.println("wzzzzzzz");
        player.tick();
    }

    @Override
    public void paintComponent(Graphics g) {

        walls.paintComponent(g);
        player.paintComponent(g);
        obstacle.paintComponent(g);

    }
}
