import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CorrectPlaces extends JPanel{

    public Level lev;

    public double xpos;
    public double ypos;
    public int number;

    public double width;
    public double height;

    public int prefWidth = 50;
    public int prefHeight = 50;

    public CorrectPlaces(int levelNumber, int index) throws IOException {
        width = prefWidth;
        height = prefHeight;
        lev = Reader.makeLevel(levelNumber);
        xpos = lev.correctPlacesPosition.elementAt(index).a;
        ypos = lev.correctPlacesPosition.elementAt(index).b;
        number = lev.numberOfObstacles;
    }

    @Override
    public void paintComponent(Graphics g) {
            g.setColor(Color.BLUE);
//            g.drawRect((int) (width * xpos), (int) (height * ypos), (int) width, (int) height);
            g.fillOval((int) ((int) (width * xpos) + (width/2) - width/5), (int) ((int) (height * ypos) + (height/2) - height/5), (int) (2*width)/5, (int) (2*height)/5);
    }
}
