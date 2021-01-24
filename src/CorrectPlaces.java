import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CorrectPlaces extends JPanel{

    public Level lev;

    public double xpos;
    public double ypos;
    public int number;

    public CorrectPlaces(int levelNumber, int index) throws IOException {
        lev = Reader.makeLevel(levelNumber);
        xpos = lev.correctPlacesPosition.elementAt(index).a;
        ypos = lev.correctPlacesPosition.elementAt(index).b;
        number = lev.numberOfObstacles;
    }

    @Override
    public void paintComponent(Graphics g) {
            g.setColor(Color.BLUE);
            g.drawRect((int) (50 * xpos), (int) (50 * ypos), 50, 50);
    }
}
