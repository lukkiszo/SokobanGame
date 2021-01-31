import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Klasa przeszkod
 */
public class Obstacle extends JComponent {

    Reader reader = new Reader();
    public boolean isOnCorrectPlace = false;
    public double xpos;
    public double ypos;

    public double width;
    public double height;

    public int prefWidth = 50;
    public int prefHeight = 50;

    public boolean rightCollision = false;
    public boolean downCollision = false;
    public boolean leftCollision = false;
    public boolean upCollision = false;

    public boolean rightWall = false;
    public boolean leftWall = false;
    public boolean upWall = false;
    public boolean downWall = false;

    private final BufferedImage image;
    private final BufferedImage image1;

    /**
     * Konstruktor
     * @param levelNumber numer poziomu
     * @param index numer przeszkody
     * @throws IOException
     */
    public Obstacle(int levelNumber, int index) throws IOException {
        width = prefWidth;
        height = prefHeight;
        Level lev = Reader.makeLevel(levelNumber);
        xpos = lev.obstaclesPosition.elementAt(index).a;
        ypos = lev.obstaclesPosition.elementAt(index).b;

        prefWidth = Reader.prefWidth/ lev.numberOfWallsX;
        prefHeight = Reader.prefHeight/(lev.numberOfWallsY+2);
        File imageFile = new File("resources/box.png");
        image = ImageIO.read(imageFile);
        File imageFile1 = new File("resources/boxonplace.png");
        image1 = ImageIO.read(imageFile1);
    }

    /**
     * Metoda rysujaca przeszkody na ekranie
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        setDoubleBuffered(true);
        if (isOnCorrectPlace) {
            g.drawImage(image1, (int) (width * (xpos)), (int) (height * (ypos + 1)), (int) width, (int) height, null);
        } else {
            g.drawImage(image, (int) (width * (xpos)), (int) (height * (ypos + 1)), (int) width, (int) height, null);
        }
    }
}
