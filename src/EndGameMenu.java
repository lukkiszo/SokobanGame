import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

/**
 * Klasa odpowiadajaca za wyswietlanie koncowego okna gry
 */
public class EndGameMenu extends JFrame {

    private final JButton highscores;
    private final JButton exit;
    private final JLabel title;
    private final JLabel scoreLabel;
    private final JLabel totalScoreLabel;
    private final JPanel pan;

    HighscoresParser parser = new HighscoresParser();

    String nickname;

    public int currentWidth;
    public int currentHeight;

    public int prefWidth = 800;
    public int prefHeight = 600;

    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    /**
     * Konstruktor
     * @param nick Nazwa gracza
     * @param totalScore wynik calkowity zsumowany ze wszystkich poziomow
     * @param score wynik poziomu
     * @throws IOException
     */
    EndGameMenu(String nick, int totalScore, double score) throws IOException {
        super("SOKOBAN");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(prefWidth,prefHeight);
        setLocationRelativeTo(null);
        pan = new JPanel();
        title = new JLabel("You Won!");
        scoreLabel = new JLabel("Score: " + (int) score);
        totalScoreLabel = new JLabel("Total Score: " + totalScore);

        this.setIconImage(new ImageIcon("resources/icon.png").getImage());

        highscores = new JButton( "Show Highscores");
        exit = new JButton("Return to Main Menu");

        title.setFont(new Font ("Consolas", Font.PLAIN, 50));
        title.setForeground(Color.white);

        scoreLabel.setForeground(Color.white);
        totalScoreLabel.setForeground(Color.white);

        pan.setBackground(Color.darkGray);
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.add(new Box.Filler(minSize, prefSize, maxSize));
        pan.add(title);

        highscores.setFont(new Font("Consolas", Font.PLAIN, 25));
        highscores.setBackground(Color.darkGray);
        highscores.setForeground(Color.white);
        highscores.setBorderPainted(false);
        highscores.setFocusable(false);

        exit.setFont(new Font("Consolas", Font.PLAIN, 25));
        exit.setBackground(Color.darkGray);
        exit.setBorderPainted(false);
        exit.setFocusable(false);
        exit.setForeground(Color.white);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        highscores.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(pan, BorderLayout.CENTER);
        this.setContentPane(pan);
        setVisible(true);

        nickname = nick;
        initComponents();

        exit.addActionListener(event -> {
            try {
                saveAndMainMenu(totalScore);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        highscores.addActionListener(event -> {
            try {
                saveAndHighscores(totalScore);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Metoda zapisujaca wynik i wracajaca do menu glownego
     * @param totalScore Wynik ze wszystkich poziomow
     * @throws IOException
     */
    public void saveAndMainMenu(int totalScore) throws IOException {
        dispose();
        parser.read();
        parser.addHighscore(new HighScore(nickname, totalScore));
        parser.saveHighscores();
        new MenuWindow().setVisible(true);
    }

    /**
     * Metoda zapisujaca wynik i przechodzaca do okna najlepszych wynikow
     * @param totalScore Wynik ze wszystkich poziomow
     * @throws IOException
     */
    private void saveAndHighscores(int totalScore) throws IOException {
        dispose();
        parser.read();
        parser.addHighscore(new HighScore(nickname, totalScore));
        parser.saveHighscores();
        new HighscoreWindow();
    }

    /**
     * Metoda ustawiajaca i skalujaca elementy okna
     */
    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                currentWidth = e.getComponent().getSize().width;
                currentHeight = e.getComponent().getSize().height;

                exit.setFont(new Font("Consolas", Font.PLAIN, (int) (25*((double)currentWidth/(double)prefWidth))));
                highscores.setFont(new Font("Consolas", Font.PLAIN, (int) (25*((double)currentWidth/(double)prefWidth))));
                title.setFont(new Font("Consolas", Font.PLAIN, (int) (50*((double)currentWidth/(double)prefWidth))));
                totalScoreLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, (int) (40*((double)currentWidth/(double)prefWidth))));
                scoreLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, (int) (40*((double)currentWidth/(double)prefWidth))));
                pan.removeAll();
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(title);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (50*((double)currentWidth/(double)prefWidth)))));
                pan.add(scoreLabel);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (70*((double)currentWidth/(double)prefWidth)))));
                pan.add(totalScoreLabel);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (70*((double)currentWidth/(double)prefWidth)))));
                pan.add(highscores);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (70*((double)currentWidth/(double)prefWidth)))));
                pan.add(exit);
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
            }
        });
    }
}
