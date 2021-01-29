import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MainWindow extends JFrame {
    Reader reader = new Reader();

    public int currentWidth;
    public int currentHeight;

    public int prefWidth;
    public int prefHeight;

    private Game game1;

    public static int totalScore = 0;

    MainWindow(Game game){
        game1 = game;
//        currentHeight = reader.prefHeight;
//        currentWidth = reader.prefWidth;
        reader.getPrefSize();
        prefHeight = reader.prefHeight;
        prefWidth = reader.prefWidth;
        this.setTitle("Sokoban");
        this.setSize(new Dimension(reader.prefWidth, reader.prefHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.add(game);
        this.addKeyListener(game.keys);
        this.setVisible(true);
        game.start();
        initComponents(game);
    }


    private void initComponents(Game game) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                currentWidth = e.getComponent().getSize().width;
                currentHeight = e.getComponent().getSize().height;

                game.resizeAll();
            }
        });
    }

    public void makeLevel(int levelNr) throws IOException {
        this.dispose();
        totalScore += game1.score;
        if(levelNr <= Reader.getNumberOfLevels()){
            NextLevelMenu nextLevel = new NextLevelMenu(levelNr, game1.nickname, (int) game1.score);
        }
        else{
            EndGameMenu endGameMenu = new EndGameMenu(game1.nickname, totalScore, (int) game1.score);
        }
//        NextLevelMenu nextLevel = new NextLevelMenu(levelNr, game1.nickname);
    }
}
