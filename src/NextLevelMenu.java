import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class NextLevelMenu extends JFrame {
    private JButton nextLevel;
    private JButton exit;

//    public int currentWidth;
//    public int currentHeight;

    private int level;
    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    NextLevelMenu(int number){
        super("SOKOBAN");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800,600);
        setLocationRelativeTo(null);
        JPanel pan = new JPanel();
        JLabel title = new JLabel("You Won!");

        nextLevel = new JButton( "Next Level");
        exit = new JButton("Return to Main Menu");

        title.setFont(new Font ("Consolas", Font.PLAIN, 50));
        title.setForeground(Color.white);

        pan.setBackground(Color.darkGray);
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        pan.add(new Box.Filler(minSize, prefSize, maxSize));
        pan.add(title);
        pan.add(Box.createRigidArea(new Dimension(0, 90)));

        pan.add(nextLevel);
        nextLevel.setFont(new Font("Consolas", Font.PLAIN, 25 ));
        nextLevel.setBackground(Color.darkGray);
        nextLevel.setForeground(Color.white);
        nextLevel.setBorderPainted(false);
        nextLevel.setFocusable(false);
        pan.add(Box.createRigidArea(new Dimension(0, 80)));

        pan.add(exit);
        exit.setFont(new Font("Consolas", Font.PLAIN, 25));
        exit.setBackground(Color.darkGray);
        exit.setBorderPainted(false);
        exit.setFocusable(false);
        exit.setForeground(Color.white);

        pan.add(new Box.Filler(minSize, prefSize, maxSize));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(pan, BorderLayout.CENTER);
        this.setContentPane(pan);
        setVisible(true);

        level = number;

        exit.addActionListener(event -> saveAndMainMenu());
        nextLevel.addActionListener(event -> {
            try {
                makeLevel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void makeLevel() throws IOException {
        dispose();
        Game gam = new Game(level);
    }

    public void saveAndMainMenu()
    {
        dispose();
        new MenuWindow().setVisible(true);
    }
}
