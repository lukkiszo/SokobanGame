import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PauseMenu extends JFrame {
    public int prefWidth;
    public int prefHeight;

    private Label[] pause;

    private Game game1;

    Reader reader = new Reader();

    public int currentWidth;
    public int currentHeight;

    PauseMenu(Game game){
        pause = new Label[3];
        pause[0] = new Label("continue");
        pause[1] = new Label("restart");
        pause[2] = new Label("go to main menu");
        game1 = game;
        reader.getPrefSize();
        prefHeight = 300;
        prefWidth = 400;
        this.setTitle("Sokoban");
        this.setSize(new Dimension(prefWidth, prefHeight));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
//        this.add(game);
        this.addKeyListener(game.keys);


        float startY = 300 - (float)(pause.length * 40 + (pause.length - 1) * 10) / 2;
        for (int i = 0; i < pause.length; i++) {
            this.add(pause[i]);
            pause[i].setLocation(200, (int) (startY + i * 50));
        }

        this.setVisible(true);
//        game.start();
        initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                currentWidth = e.getComponent().getSize().width;
                currentHeight = e.getComponent().getSize().height;
            }
        });
    }

}
