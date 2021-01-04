import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Obstacle extends JComponent {
    private Level lev = Reader.makeLevel(1);

    public Obstacle() throws IOException {
    }


    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);

        for(int i = 0; i<lev.obstaclesPosition.size(); i++)
        {
            g.setColor(Color.RED);
            g.fillRect(50 * lev.obstaclesPosition.elementAt(i).a, 50 * lev.obstaclesPosition.elementAt(i).b, 50,50);
            g.setColor(Color.BLACK);
            g.drawRect(50 * lev.obstaclesPosition.elementAt(i).a, 50 * lev.obstaclesPosition.elementAt(i).b, 50,50);
        }

    }

}
