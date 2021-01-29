import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class Walls extends JPanel{
    public Level lev;

    public double xpos;
    public double ypos;
    public int numberOfWalls;

    public double width;
    public double height;

    public int prefWidth = 50;
    public int prefHeight = 50;
    private BufferedImage image;

    public Walls(int levelNumber, int index) throws IOException {
        width = prefWidth;
        height = prefHeight;
        lev = Reader.makeLevel(levelNumber);
        xpos = lev.wallsPosition.elementAt(index).a;
        ypos = lev.wallsPosition.elementAt(index).b;
        numberOfWalls = lev.wallsPosition.size();
        File imageFile = new File("resources/sciana.bmp");
        image = ImageIO.read(imageFile);
    }

    @Override
    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
        g.drawImage(image, (int) (width * xpos), (int) (height * ypos), (int) width, (int) height, null );
//        g.setColor(Color.WHITE);
//        g.fillRect((int) (width * xpos), (int) (height * ypos), (int) width, (int) height);
//        g.setColor(Color.BLACK);
//        g.drawRect((int) (width * xpos), (int) (height * ypos), (int) width, (int) height);

    }

}
