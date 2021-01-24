import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class NextLevelMenu extends JFrame {
    private JButton nextLevel;
    private JButton exit;
    private JLabel title;
    private JPanel pan;

    String nickname;

    public int currentWidth;
    public int currentHeight;

    public int prefWidth = 800;
    public int prefHeight = 600;

    private int level;
    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    NextLevelMenu(int number, String nick){
        super("SOKOBAN");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(prefWidth,prefHeight);
        setLocationRelativeTo(null);
        pan = new JPanel();
        title = new JLabel("You Won!");

        nextLevel = new JButton( "Next Level");
        exit = new JButton("Return to Main Menu");

        title.setFont(new Font ("Consolas", Font.PLAIN, 50));
        title.setForeground(Color.white);

        pan.setBackground(Color.darkGray);
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.add(new Box.Filler(minSize, prefSize, maxSize));
        pan.add(title);

        nextLevel.setFont(new Font("Consolas", Font.PLAIN, 25 ));
        nextLevel.setBackground(Color.darkGray);
        nextLevel.setForeground(Color.white);
        nextLevel.setBorderPainted(false);
        nextLevel.setFocusable(false);

        exit.setFont(new Font("Consolas", Font.PLAIN, 25));
        exit.setBackground(Color.darkGray);
        exit.setBorderPainted(false);
        exit.setFocusable(false);
        exit.setForeground(Color.white);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(pan, BorderLayout.CENTER);
        this.setContentPane(pan);
        setVisible(true);

        level = number;
        nickname = nick;
        initComponents();

        exit.addActionListener(event -> saveAndMainMenu());
        nextLevel.addActionListener(event -> {
            try {
                makeLevel(nickname);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                currentWidth = e.getComponent().getSize().width;
                currentHeight = e.getComponent().getSize().height;

                exit.setFont(new Font("Consolas", Font.PLAIN, (int) (25*((double)currentWidth/(double)prefWidth))));
                nextLevel.setFont(new Font("Consolas", Font.PLAIN, (int) (25*((double)currentWidth/(double)prefWidth))));
                title.setFont(new Font("Consolas", Font.PLAIN, (int) (50*((double)currentWidth/(double)prefWidth))));
                pan.removeAll();
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(title);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (90*((double)currentWidth/(double)prefWidth)))));
                pan.add(nextLevel);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (80*((double)currentWidth/(double)prefWidth)))));
                pan.add(exit);
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
            }
        });
    }

    public void makeLevel(String nickname) throws IOException {
        dispose();
        Game gam = new Game(level, nickname);
    }

    public void saveAndMainMenu()
    {
        dispose();
        new MenuWindow().setVisible(true);
    }
}
