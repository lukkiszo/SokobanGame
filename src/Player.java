import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Player extends JComponent {
    Level lev = Reader.makeLevel(1);

    public Player() throws IOException {
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval(50*lev.playerPosition.elementAt(0).a, 50*lev.playerPosition.elementAt(0).b, 50, 50);
        g.setColor(Color.BLACK);
        g.drawOval(50*lev.playerPosition.elementAt(0).a, 50*lev.playerPosition.elementAt(0).b, 50, 50);
    }
}
