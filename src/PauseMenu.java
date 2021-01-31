import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

/**
 * Klasa obslugujaca okno pauzy
 */
public class PauseMenu extends JComponent {
    public int prefWidth;
    public int prefHeight;
    private final JButton continueButton;
    private final JButton restartButton;
    private final JButton mainMenuButton;
    private final JLabel pauseLabel;
    private final int levelNumber;
    private final String nickname;
    private JButton[] pause;
    private final Game game;

    public int currentWidth;
    public int currentHeight;
    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    /**
     * Konstruktor
     * @param game aktualna gra
     * @param number numer aktualnego poziomu
     * @param nick nazwa gracza
     */
    PauseMenu(Game game, int number, String nick) {
        this.game = game;
        levelNumber = number;
        nickname = nick;
        continueButton = new JButton("Continue");
        restartButton = new JButton("Restart");
        mainMenuButton = new JButton("Main Menu");
        pauseLabel = new JLabel("Pause");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.darkGray);
        add(pauseLabel);
        pauseLabel.setForeground(Color.white);
        pauseLabel.setBackground(Color.darkGray);
        continueButton.setBackground(Color.darkGray);
        continueButton.setForeground(Color.white);
        continueButton.setFocusable(false);
        continueButton.setBorderPainted(false);
        restartButton.setBackground(Color.darkGray);
        restartButton.setForeground(Color.white);
        restartButton.setBorderPainted(false);
        restartButton.setFocusable(false);
        mainMenuButton.setBackground(Color.darkGray);
        mainMenuButton.setForeground(Color.white);
        mainMenuButton.setBorderPainted(false);
        mainMenuButton.setFocusable(false);

        pauseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.addActionListener(event -> game.togglePause());

        Reader.getPrefSize();
        prefHeight = 600;
        prefWidth = 800;
        this.setSize(new Dimension(prefWidth, prefHeight));
        restartButton.addActionListener(event ->{
            try{
                this.levelRestart();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );
        mainMenuButton.addActionListener(event -> this.goToMainMenu());
        this.setVisible(false);
        initComponents();
    }

    /**
     * Metoda obslugujaca powrot do menu glownego
     */
    private void goToMainMenu(){
        getParent().getParent().getParent().getParent().setVisible(false);
        new MenuWindow().setVisible(true);
    }

    /**
     * Metoda obslugujaca uruchomienie poziomu od nowa
     * @throws IOException
     */
    private void levelRestart() throws IOException {
        getParent().getParent().getParent().getParent().setVisible(false);
        new NextLevelMenu(levelNumber, nickname, (int) game.score).makeLevel(nickname);
    }

    /**
     * Metoda ustawiajaca i skalujaca menu pauzy
     */
    private void initComponents() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                currentWidth = e.getComponent().getSize().width;
                currentHeight = e.getComponent().getSize().height;
                pauseLabel.setFont(new Font("Consolas", Font.PLAIN, (int) (50 * (((double) currentWidth + (double) currentHeight) / ((double) prefWidth + (double) prefHeight)))));
                continueButton.setFont(new Font("Consolas", Font.PLAIN, (int) (25 * (((double) currentWidth + (double) currentHeight) / ((double) prefWidth + (double) prefHeight)))));
                restartButton.setFont(new Font("Consolas", Font.PLAIN, (int) (25 * (((double) currentWidth + (double) currentHeight) / ((double) prefWidth + (double) prefHeight)))));
                mainMenuButton.setFont(new Font("Consolas", Font.PLAIN, (int) (25 * (((double) currentWidth + (double) currentHeight) / ((double) prefWidth + (double) prefHeight)))));
                removeAll();
                add(new Box.Filler(minSize, prefSize, maxSize));
                add(pauseLabel);
                add(Box.createRigidArea(new Dimension(0, (int) (60 * ((double) currentHeight / (double) prefHeight)))));
                add(continueButton);
                add(Box.createRigidArea(new Dimension(0, (int) (80 * ((double) currentHeight / (double) prefHeight)))));
                add(restartButton);
                add(Box.createRigidArea(new Dimension(0, (int) (80 * ((double) currentHeight / (double) prefHeight)))));
                add(mainMenuButton);
                add(new Box.Filler(minSize, prefSize, maxSize));
            }
        });
    }

    /**
     * Metoda rysujaca tlo menu pauzy
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, currentWidth, currentHeight);
    }
}
