import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Klasa scian
 */
public class Walls extends JComponent{
    public Level lev;
    Reader reader = new Reader();
    public double xpos;
    public double ypos;
    public int numberOfWallsX;
    public int numberOfWallsY;

    public double width;
    public double height;

    public int prefWidth = 50;
    public int prefHeight = 50;
    private BufferedImage image;

    /**
     * Konstruktor
     * @param levelNumber numer aktualnego poziomu
     * @param index Numer sciany w plikach konfiguracyjnych
     * @throws IOException
     */
    public Walls(int levelNumber, int index) throws IOException {


        lev = Reader.makeLevel(levelNumber);
        xpos = lev.wallsPosition.elementAt(index).a;
        ypos = lev.wallsPosition.elementAt(index).b;

        Reader.getPrefSize();
        prefWidth = Reader.prefWidth /lev.numberOfWallsX;
        prefHeight = Reader.prefHeight /(lev.numberOfWallsY+2);

        width = prefWidth;
        height = prefHeight;

        File imageFile = new File("resources/sciana.bmp");
        image = ImageIO.read(imageFile);
    }

    /**
     * Metoda rysujaca obiekt sciany w grze
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, (int) (width * (xpos)), (int) (height * (ypos+1)), (int) width, (int) height, null );
    }

}
