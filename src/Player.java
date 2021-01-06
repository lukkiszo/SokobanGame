import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Player extends JComponent {
    Level lev;
    double[] position;
    boolean goRight = false;
    boolean goLeft = false;
    boolean goUp = false;
    boolean goDown = false;
    boolean rightCollision = false;
    boolean leftCollision = false;
    boolean upCollision = false;
    boolean downCollision = false;
    boolean rightCollisionO = false;
    boolean leftCollisionO = false;
    boolean upCollisionO = false;
    boolean downCollisionO = false;
    double width = 50;
    double height = 50;

    long currentTime;
    long lastTime = 0;

    public Player(int levelNumber) throws IOException {
        lev = Reader.makeLevel(levelNumber);
        position = new double[2];
        position[0] = lev.playerPosition.elementAt(0).a;
        position[1] = lev.playerPosition.elementAt(0).b;
    }

    public void collisionWithWalls()
    {
        rightCollision = false;
        leftCollision = false;
        upCollision = false;
        downCollision = false;

        for (int i = 0; i<lev.wallsPosition.size(); i++)
        {
            if(position[0] + 1 == lev.wallsPosition.elementAt(i).a && position[1] + 0.5 == lev.wallsPosition.elementAt(i).b + 0.5) rightCollision = true;

            if(position[0] + 0.5 == lev.wallsPosition.elementAt(i).a + 0.5 && position[1] + 1 == lev.wallsPosition.elementAt(i).b) downCollision = true;

            if(position[0] + 0.5 == lev.wallsPosition.elementAt(i).a + 0.5 && position[1] == lev.wallsPosition.elementAt(i).b + 1) upCollision = true;

            if(position[0] == lev.wallsPosition.elementAt(i).a + 1 && position[1] + 0.5 == lev.wallsPosition.elementAt(i).b + 0.5) leftCollision = true;
        }
    }


    public void tick()
    {
//        currentTime = System.currentTimeMillis();

//        if(currentTime - lastTime > 150){
//            collisionWithWalls();


            if(goRight && !rightCollision && !rightCollisionO){
                System.out.println(rightCollisionO);
                System.out.println(rightCollision);
                position[0] += 1;
                System.out.println("RRRRRRRR");
            }
            else if(goLeft && !leftCollision && !leftCollisionO){
                System.out.println(leftCollisionO);
                System.out.println(leftCollision);
                position[0] -= 1;
                System.out.println("LLLLLLLL");
            }
            else if(goUp && !upCollision && !upCollisionO){
                System.out.println(3);
                position[1] -= 1;
                System.out.println("UUUUUUU");
            }
            else if(goDown && !downCollision && !downCollisionO){
                System.out.println(4);
                position[1] += 1;
                System.out.println("DDDDDDDD");
            }
//            lastTime = System.currentTimeMillis();
//        }
    }


    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval((int) (width*position[0]), (int) (height*position[1]), (int) width, (int) height);
        g.setColor(Color.BLACK);
        g.drawOval((int) (width*position[0]), (int) (height*position[1]), (int) width, (int) height);
    }
}
