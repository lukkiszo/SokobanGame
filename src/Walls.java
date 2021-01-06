import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Walls extends JPanel {
    public Level lev;
    double[] position;
    Rectangle[] wal;


    public Walls(int levelNumber) throws IOException {
        lev = Reader.makeLevel(levelNumber);
        position = new double[2*lev.wallsPosition.size()];
        wal = new Rectangle[lev.wallsPosition.size()];
        for(int i = 0; i<lev.wallsPosition.size(); i++)
        {
            position[2*i] = lev.wallsPosition.elementAt(i).a;
            position[2*i+1] = lev.wallsPosition.elementAt(i).b;
            wal[i] = new Rectangle(lev.wallsPosition.elementAt(i).a,lev.wallsPosition.elementAt(i).b,50,50);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);

        for(int i = 0; i<lev.wallsPosition.size(); i++)
        {
            g.setColor(Color.WHITE);
            g.fillRect((int) (50 * position[2*i]), (int) (50 * position[2*i+1]), 50,50);
            g.setColor(Color.BLACK);
            g.drawRect((int) (50 * position[2*i]), (int) (50 * position[2*i+1]), 50,50);
//                g2d.draw(new Rectangle2D.Double(40 * lev.wallsPosition.elementAt(i).a, 40 * lev.wallsPosition.elementAt(i).b, 40,40));
        }

    }

}
