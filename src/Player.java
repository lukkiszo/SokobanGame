import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Klasa postaci
 */
public class Player extends JComponent {
    Level lev;
    Reader reader = new Reader();
    public double[] position;
    public boolean goRight = false;
    public boolean goLeft = false;
    public boolean goUp = false;
    public boolean goDown = false;
    public boolean rightWall = false;
    public boolean leftWall = false;
    public boolean upWall = false;
    public boolean downWall = false;
    public boolean rightCollision = false;
    public boolean leftCollision = false;
    public boolean upCollision = false;
    public boolean downCollision = false;
    public boolean isOnTeleport = false;
    public BufferedImage image;
    public double width;
    public double height;

    public boolean wantToDeleteOnLeft = false;
    public boolean wantToDeleteOnRight = false;
    public boolean wantToDeleteOnUp = false;
    public boolean wantToDeleteOnDown = false;


    public int prefWidth = 50;
    public int prefHeight = 50;

    /**
     * Konstruktor
     * @param levelNumber numer poziomu
     * @throws IOException
     */
    public Player(int levelNumber) throws IOException {
        width = prefWidth;
        height = prefHeight;
        lev = Reader.makeLevel(levelNumber);
        position = new double[2];
        position[0] = lev.playerPosition.elementAt(0).a;
        position[1] = lev.playerPosition.elementAt(0).b;

        prefWidth = Reader.prefWidth /lev.numberOfWallsX;
        prefHeight = Reader.prefHeight /(lev.numberOfWallsY+2);
        File imageFile = new File("resources/player.png");
        image = ImageIO.read(imageFile);
    }

    /**
     * Metoda obslugujaca kolizje postaci ze scianami
     */
    public void collisionWithWalls()
    {
        rightWall = false;
        leftWall = false;
        upWall = false;
        downWall = false;

        for (int i = 0; i<lev.wallsPosition.size(); i++)
        {
            if(position[0] + 1 == lev.wallsPosition.elementAt(i).a && position[1] + 0.5 == lev.wallsPosition.elementAt(i).b + 0.5) rightWall = true;

            if(position[0] + 0.5 == lev.wallsPosition.elementAt(i).a + 0.5 && position[1] + 1 == lev.wallsPosition.elementAt(i).b) downWall = true;

            if(position[0] + 0.5 == lev.wallsPosition.elementAt(i).a + 0.5 && position[1] == lev.wallsPosition.elementAt(i).b + 1) upWall = true;

            if(position[0] == lev.wallsPosition.elementAt(i).a + 1 && position[1] + 0.5 == lev.wallsPosition.elementAt(i).b + 0.5) leftWall = true;
        }
    }

    /**
     * Metoda rysujaca postac
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, (int) (width * (position[0])), (int) (height * (position[1]+1)), (int) width, (int) height, null);
    }
}
