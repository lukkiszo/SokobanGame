import java.awt.*;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

/**
 * Klasa obslugujaca menu glowne
 */
public class MenuWindow extends JFrame{
    private final JButton startButton;
    private final JButton scoresButton;
    private final JButton exitButton;
    private final JPanel pan;
    private final JLabel title;
    public String nickname;
    int levelNumber = 1;

    public int currentWidth;
    public int currentHeight;

    public int prefWidth = 800;
    public int prefHeight = 600;

    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    /**
     * Konstruktor
     */
    MenuWindow(){
        super("SOKOBAN");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800,600);
        setLocationRelativeTo(null);
        pan = new JPanel();
        title = new JLabel("SOKOBAN");

        this.setIconImage(new ImageIcon("resources/icon.png").getImage());

        startButton = new JButton( "Start");
        scoresButton = new JButton("Highscores");
        exitButton = new JButton("Exit");

        title.setFont(new Font ("Consolas", Font.PLAIN, 50));
        title.setForeground(Color.white);

        pan.setBackground(Color.darkGray);
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.add(new Box.Filler(minSize, prefSize, maxSize));
        pan.add(title);
        startButton.setBackground(Color.darkGray);
        startButton.setForeground(Color.white);
        startButton.setBorderPainted(false);
        startButton.setFocusable(false);
        scoresButton.setBackground(Color.darkGray);
        scoresButton.setBorderPainted(false);
        scoresButton.setFocusable(false);
        scoresButton.setForeground(Color.white);
        exitButton.setBackground(Color.darkGray);
        exitButton.setBorderPainted(false);
        exitButton.setFocusable(false);
        exitButton.setForeground(Color.white);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(pan, BorderLayout.CENTER);
        this.setContentPane(pan);
        setVisible(true);

        initComponents();

        exitButton.addActionListener(event -> System.exit(1));
        scoresButton.addActionListener(event -> {
            try {
                showHighscores();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        startButton.addActionListener(event -> nickPicker());

    }

    /**
     * Metoda tworzaca okno z najlepszymi wynikami
     * @throws IOException
     */
    void showHighscores() throws IOException {
        dispose();
        new HighscoreWindow();
    }

    /**
     * Metoda tworzaca okno z wyborem nazwy gracza
     */
    void nickPicker()
    {
        dispose();
        new NickPicker();
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

                title.setFont(new Font("Consolas", Font.PLAIN, (int) (50*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                startButton.setFont(new Font("Consolas", Font.PLAIN, (int) (25*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                scoresButton.setFont(new Font("Consolas", Font.PLAIN, (int) (25*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                exitButton.setFont(new Font("Consolas", Font.PLAIN, (int) (25*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                pan.removeAll();
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(title);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (90*((double)currentHeight/(double)prefHeight)))));
                pan.add(startButton);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (80*((double)currentHeight/(double)prefHeight)))));
                pan.add(scoresButton);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (80*((double)currentHeight/(double)prefHeight)))));
                pan.add(exitButton);
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
            }
        });
    }

    public static void main(String[] args) {
        MenuWindow menu = new MenuWindow();
    }


}
