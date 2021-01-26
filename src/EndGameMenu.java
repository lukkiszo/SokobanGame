import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class EndGameMenu extends JFrame {

    private JButton highscores;
    private JButton exit;
    private JLabel title;
    private JPanel pan;

    String nickname;

    public int currentWidth;
    public int currentHeight;

    public int prefWidth = 800;
    public int prefHeight = 600;

    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    EndGameMenu(String nick){
        super("SOKOBAN");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(prefWidth,prefHeight);
        setLocationRelativeTo(null);
        pan = new JPanel();
        title = new JLabel("You Won!");

        highscores = new JButton( "Show Highscores");
        exit = new JButton("Return to Main Menu");

        title.setFont(new Font ("Consolas", Font.PLAIN, 50));
        title.setForeground(Color.white);

        pan.setBackground(Color.darkGray);
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.add(new Box.Filler(minSize, prefSize, maxSize));
        pan.add(title);

        highscores.setFont(new Font("Consolas", Font.PLAIN, 25 ));
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
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(pan, BorderLayout.CENTER);
        this.setContentPane(pan);
        setVisible(true);

        nickname = nick;
        initComponents();

        exit.addActionListener(event -> saveAndMainMenu());
        highscores.addActionListener(event -> saveAndHighscores());
    }

    public void saveAndMainMenu()
    {
        dispose();
        new MenuWindow().setVisible(true);
    }

    private void saveAndHighscores()
    {
        dispose();
        new HighscoreWindow();
    }

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
                pan.removeAll();
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(title);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (90*((double)currentWidth/(double)prefWidth)))));
                pan.add(highscores);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (80*((double)currentWidth/(double)prefWidth)))));
                pan.add(exit);
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
            }
        });
    }
}
