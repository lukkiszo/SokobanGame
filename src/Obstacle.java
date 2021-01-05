import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Obstacle extends JComponent {
    private Level lev;
    private double[] positions;
    private int numberOfObstacles;
    boolean isTouchingPlayer = false;
    boolean isOnCorrectPlace = false;

    public Obstacle(int levelNumber) throws IOException {
        lev = Reader.makeLevel(levelNumber);
        numberOfObstacles = lev.numberOfObstacles;
        positions = new double[2*numberOfObstacles];
        for(int i = 0; i<numberOfObstacles; i++)
        {
            positions[2*i] = lev.obstaclesPosition.elementAt(i).a;
            positions[2*i+1] = lev.obstaclesPosition.elementAt(i).b;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        setDoubleBuffered(true);
        for(int i = 0; i<numberOfObstacles; i++)
        {
            g.setColor(Color.RED);
            g.fillRect((int) (50 * positions[2*i]), (int) (50 * positions[2*i+1]), 50,50);
            g.setColor(Color.BLACK);
            g.drawRect((int) (50 * positions[2*i]), (int) (50 * positions[2*i+1]), 50,50);
        }

    }

}
