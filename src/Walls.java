import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Walls extends JPanel {
    public Level lev = Reader.makeLevel(1);

    public Walls() throws IOException {
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);

        for(int i = 0; i<lev.wallsPosition.size(); i++)
        {
            g.setColor(Color.WHITE);
            g.fillRect(50 * lev.wallsPosition.elementAt(i).a, 50 * lev.wallsPosition.elementAt(i).b, 50,50);
            g.setColor(Color.BLACK);
            g.drawRect(50 * lev.wallsPosition.elementAt(i).a, 50 * lev.wallsPosition.elementAt(i).b, 50,50);
//                g2d.draw(new Rectangle2D.Double(40 * lev.wallsPosition.elementAt(i).a, 40 * lev.wallsPosition.elementAt(i).b, 40,40));
        }

    }

}
