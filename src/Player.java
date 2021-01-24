import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Player extends JComponent {
    Level lev;
    public double[] position;
    public boolean goRight = false;
    public boolean goLeft = false;
    public boolean goUp = false;
    public boolean goDown = false;
    public boolean rightWall = false;
    public boolean leftWall = false;
    public boolean upWall = false;
    public boolean downWall = false;
    public boolean rightCollision = false;
    public boolean leftCollision = false;
    public boolean upCollision = false;
    public boolean downCollision = false;

    public double width;
    public double height;

    public int prefWidth = 50;
    public int prefHeight = 50;

    public Player(int levelNumber) throws IOException {
        width = prefWidth;
        height = prefHeight;
        lev = Reader.makeLevel(levelNumber);
        position = new double[2];
        position[0] = lev.playerPosition.elementAt(0).a;
        position[1] = lev.playerPosition.elementAt(0).b;
    }

    public void collisionWithWalls()
    {
        rightWall = false;
        leftWall = false;
        upWall = false;
        downWall = false;

        for (int i = 0; i<lev.wallsPosition.size(); i++)
        {
            if(position[0] + 1 == lev.wallsPosition.elementAt(i).a && position[1] + 0.5 == lev.wallsPosition.elementAt(i).b + 0.5) rightWall = true;

            if(position[0] + 0.5 == lev.wallsPosition.elementAt(i).a + 0.5 && position[1] + 1 == lev.wallsPosition.elementAt(i).b) downWall = true;

            if(position[0] + 0.5 == lev.wallsPosition.elementAt(i).a + 0.5 && position[1] == lev.wallsPosition.elementAt(i).b + 1) upWall = true;

            if(position[0] == lev.wallsPosition.elementAt(i).a + 1 && position[1] + 0.5 == lev.wallsPosition.elementAt(i).b + 0.5) leftWall = true;
        }
    }


    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval((int) (width*position[0]), (int) (height*position[1]), (int) width, (int) height);
        g.setColor(Color.BLACK);
        g.drawOval((int) (width*position[0]), (int) (height*position[1]),(int) width, (int) height);
    }
}
