import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class CorrectPlaces extends JPanel{

    public Level lev;
    public double[] position;
    Rectangle[] places;


    public CorrectPlaces(int levelNumber) throws IOException {
        lev = Reader.makeLevel(levelNumber);
        position = new double[3*lev.correctPlacesPosition.size()];
        places = new Rectangle[lev.correctPlacesPosition.size()];
        for(int i = 0; i<lev.correctPlacesPosition.size(); i++)
        {
            position[3*i] = lev.correctPlacesPosition.elementAt(i).a;
            position[3*i+1] = lev.correctPlacesPosition.elementAt(i).b;
            position[3*i+2] = 0;
            places[i] = new Rectangle(lev.correctPlacesPosition.elementAt(i).a,lev.correctPlacesPosition.elementAt(i).b,50,50);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        for(int i = 0; i<lev.correctPlacesPosition.size(); i++)
        {
            if(position[3*i+2] == 0) {
//                g.setColor(Color.BLUE);
//                g.fillRect((int) (50 * position[3 * i]), (int) (50 * position[3 * i + 1]), 50, 50);
                g.setColor(Color.BLUE);
                g.drawRect((int) (50 * position[3 * i]), (int) (50 * position[3 * i + 1] ), 50, 50);
            }
        }

    }
}
