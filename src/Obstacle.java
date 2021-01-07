import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Obstacle extends JComponent {
    Rectangle[] obs;  // tablica wszystkich przeszkod
    private Level lev;
    private CorrectPlaces correctPlaces;
    public double[] positions;
    private int numberOfObstacles;
//    public boolean czySieRuszylo = false;

    public boolean rightCollision = false;
    public boolean downCollision = false;

    public Obstacle(int levelNumber) throws IOException {
        lev = Reader.makeLevel(levelNumber);
        numberOfObstacles = lev.numberOfObstacles;
        positions = new double[3*numberOfObstacles];
        correctPlaces = new CorrectPlaces(levelNumber);
        obs = new Rectangle[numberOfObstacles];
        for(int i = 0; i<numberOfObstacles; i++)
        {
            positions[3*i] = lev.obstaclesPosition.elementAt(i).a;
            positions[3*i+1] = lev.obstaclesPosition.elementAt(i).b;
            positions[3*i+2] = 0;
            obs[i] = new Rectangle(lev.obstaclesPosition.elementAt(i).a,lev.obstaclesPosition.elementAt(i).b,50,50);
        }
    }

    public boolean collisionWithOther(){
        rightCollision = false;
        downCollision = false;

        for(int i = 0; i<numberOfObstacles; i++){
            for (int j = 0; j<numberOfObstacles; j++) {
                if (positions[3 * i] - 1 == positions[3 * j] && positions[3 * i + 1] == positions[3 * j + 1]) {
                    rightCollision = true;
                    break;
                }

                if(positions[3 * i] == positions[3 * j] && positions[3 * i + 1] + 1 == positions[3 * j + 1]) {
                    downCollision = true;
                    break;
                }
            }
        }

        return rightCollision || downCollision;
    }

    public boolean isOnCorrectPlace()
    {
        int k = 0;
        for(int i = 0; i<numberOfObstacles; i++)
        {
            for(int j = 0; j<correctPlaces.position.length/3; j++)
            {
                if(positions[3*i] == correctPlaces.position[3*j] && positions[3*i+1] == correctPlaces.position[3*j+1])
                {
                    positions[3*j+2] = 1;
                    positions[3*i+2] = 1;
                    k++;
                }
            }
        }

            return k == lev.numberOfObstacles;

    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        setDoubleBuffered(true);
        for(int i = 0; i<numberOfObstacles; i++)
        {
//            if(positions[3*i+2] == 0) {
                g.setColor(Color.RED);
                g.fillRect((int) (50 * positions[3 * i]), (int) (50 * positions[3 * i + 1]), 50, 50);
                g.setColor(Color.BLACK);
                g.drawRect((int) (50 * positions[3 * i]), (int) (50 * positions[3 * i + 1]), 50, 50);
//            }
//            else if(positions[3*i+2] == 1){
//                g.setColor(Color.orange);
//                g.fillRect((int) (50 * positions[3 * i]), (int) (50 * positions[3 * i + 1]), 50, 50);
//                g.setColor(Color.BLACK);
//                g.drawRect((int) (50 * positions[3 * i]), (int) (50 * positions[3 * i + 1]), 50, 50);
//            }
        }

    }

}
