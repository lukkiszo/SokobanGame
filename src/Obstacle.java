import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Obstacle extends JComponent {
    Rectangle[] obs;  // tablica wszystkich przeszkod
    private Level lev;
    public double[] positions;
    private int numberOfObstacles;
    boolean isOnCorrectPlace = false;

    public boolean rightCollision = false;
    public boolean downCollision = false;

    public Obstacle(int levelNumber) throws IOException {
        lev = Reader.makeLevel(levelNumber);
        numberOfObstacles = lev.numberOfObstacles;
        positions = new double[2*numberOfObstacles];
        obs = new Rectangle[numberOfObstacles];
        for(int i = 0; i<numberOfObstacles; i++)
        {
            positions[2*i] = lev.obstaclesPosition.elementAt(i).a;
            positions[2*i+1] = lev.obstaclesPosition.elementAt(i).b;
            obs[i] = new Rectangle(lev.obstaclesPosition.elementAt(i).a,lev.obstaclesPosition.elementAt(i).b,50,50);
        }
    }

    public boolean collisionWithOther(){
        rightCollision = false;
        downCollision = false;

        for(int i = 0; i<numberOfObstacles; i++){
            for (int j = 0; j<numberOfObstacles; j++) {
                if (positions[2 * i] - 1 == positions[2 * j] && positions[2 * i + 1] == positions[2 * j + 1]) {
                    rightCollision = true;
                    break;
                }

                if(positions[2 * i] == positions[2 * j] && positions[2 * i + 1] + 1 == positions[2 * j + 1]) {
                    downCollision = true;
                    break;
                }
            }
        }

        return rightCollision || downCollision;
    }

    private boolean sprawdzCzyNaMiejscu(Rectangle[] obs, int[] correctPositions)
    {
        System.out.println(obs[0]);
        return isOnCorrectPlace;
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
