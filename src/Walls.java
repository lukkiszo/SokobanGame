import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Walls extends JPanel {
    public Level lev;

    public double xpos;
    public double ypos;
    public int numberOfWalls;


    public Walls(int levelNumber, int index) throws IOException {
        lev = Reader.makeLevel(levelNumber);
        xpos = lev.wallsPosition.elementAt(index).a;
        ypos = lev.wallsPosition.elementAt(index).b;
        numberOfWalls = lev.wallsPosition.size();
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect((int) (50 * xpos), (int) (50 * ypos), 50,50);
            g.setColor(Color.BLACK);
            g.drawRect((int) (50 * xpos), (int) (50 * ypos), 50,50);

    }

}
