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

    long currentTime;
    long lastTime = 0;

    public Player(int levelNumber) throws IOException {
        lev = Reader.makeLevel(levelNumber);
        position = new double[2];
        position[0] = lev.playerPosition.elementAt(0).a;
        position[1] = lev.playerPosition.elementAt(0).b;
    }

    public void tick()
    {
        currentTime = System.currentTimeMillis();

        if(currentTime - lastTime > 200){
            if(goRight){
                position[0] += 1;
                System.out.println("RRRRRRRR");
            }
            else if(goLeft){
                position[0] -= 1;
                System.out.println("LLLLLLLL");
            }
            else if(goUp){
                position[1] -= 1;
                System.out.println("UUUUUUU");
            }
            else if(goDown){
                position[1] += 1;
                System.out.println("DDDDDDDD");
            }
            lastTime = System.currentTimeMillis();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval((int) (50*position[0]), (int) (50*position[1]), 50, 50);
        g.setColor(Color.BLACK);
        g.drawOval((int) (50*position[0]), (int) (50*position[1]), 50, 50);
    }
}
