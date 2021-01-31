import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Teleport extends JPanel {
    private Level lev;
    Reader reader = new Reader();
    public double xpos;
    public double ypos;
    public int index;
    public double width;
    public double height;
    public boolean isBlocked = false;
    public int prefWidth = 50;
    public int prefHeight = 50;
    private BufferedImage image;
    private BufferedImage image1;

    public Teleport(int levelNumber, int ind) throws IOException {
        width = prefWidth;
        height = prefHeight;
        index = ind;
        lev = Reader.makeLevel(levelNumber);
        xpos = lev.teleportsPosition.elementAt(ind).a;
        ypos = lev.teleportsPosition.elementAt(ind).b;

        reader.getPrefSize();
        prefWidth = reader.prefWidth/lev.numberOfWallsX;
        prefHeight = reader.prefHeight/(lev.numberOfWallsY+2);
        File imageFile = new File("resources/teleport.png");
        image = ImageIO.read(imageFile);
        File imageFile1 = new File("resources/teleport2.png");
        image1 = ImageIO.read(imageFile1);
    }

    public void paintComponent(Graphics g) {
        setDoubleBuffered(true);
        if(index == 0)
        g.drawImage(image, (int) (width * (xpos)), (int) (height * (ypos+1)), (int) width, (int) height, null);
        else
        g.drawImage(image1, (int) (width * (xpos)), (int) (height * (ypos+1)), (int) width, (int) height, null);
    }
}