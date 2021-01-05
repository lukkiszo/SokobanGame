import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.io.IOException;

public class MenuWindow extends JFrame{
    JButton startButton, scoresButton, exitButton;
    JTextField nick;
    JPanel pan = new JPanel();
    String nickname;
    int levelNumber = 1;

    MenuWindow(){
        super("SOKOBAN");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800,600);
        setLocationRelativeTo(null);
        JLabel title = new JLabel("SOKOBAN");
        Dimension minSize = new Dimension(1, 1);
        Dimension prefSize = new Dimension(1, 20);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
        //frame.setLocation(600, 250);
        startButton = new JButton( "Start");
        scoresButton = new JButton("Highscores");
        exitButton = new JButton("Exit");

        title.setFont(new Font ("Consolas", Font.PLAIN, 50));
        title.setForeground(Color.white);
        title.setPreferredSize(new Dimension(300,100));
        //title.setMaximumSize(maxSize);
        pan.setBackground(Color.darkGray);
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

        pan.add(new Box.Filler(minSize, prefSize, maxSize));
        pan.add(title);
        pan.add(Box.createRigidArea(new Dimension(0, 90)));

        pan.add(startButton);
        startButton.setFont(new Font("Consolas", Font.PLAIN, 25 ));
        startButton.setBackground(Color.darkGray);
        startButton.setForeground(Color.white);
        startButton.setBorderPainted(false);
        startButton.setFocusable(false);
        startButton.setMaximumSize(maxSize);
        pan.add(Box.createRigidArea(new Dimension(0, 80)));

        pan.add(scoresButton);
        scoresButton.setFont(new Font("Consolas", Font.PLAIN, 25));
        scoresButton.setBackground(Color.darkGray);
        scoresButton.setBorderPainted(false);
        scoresButton.setFocusable(false);
        scoresButton.setForeground(Color.white);
        scoresButton.setMaximumSize(maxSize);
        pan.add(Box.createRigidArea(new Dimension(0, 80)));

        pan.add(exitButton);
        exitButton.setFont(new Font("Consolas", Font.PLAIN, 25));
        exitButton.setBackground(Color.darkGray);
        exitButton.setBorderPainted(false);
        exitButton.setFocusable(false);
        exitButton.setForeground(Color.white);
        exitButton.setMaximumSize(maxSize);

        pan.add(new Box.Filler(minSize, prefSize, maxSize));

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //button.setFont(button.getFont().deriveFont((float)(button.getWidth()/2)));
        //button1.setFont(button2.getFont().deriveFont((float)(button.getWidth()/2)));
        //button2.setFont(button1.getFont().deriveFont((float)(button.getWidth()/2)));
        this.add(pan, BorderLayout.CENTER);
        this.setContentPane(pan);
        setVisible(true);

        exitButton.addActionListener(event -> System.exit(1));
        scoresButton.addActionListener(event -> showHighscores());
        startButton.addActionListener(event -> nickPicker());

    }

    void showHighscores()
    {

    }

    void nickPicker()
    {
        getContentPane().removeAll();
        setSize(800, 600);
        JLabel label = new JLabel("Nick: ");
        JLabel label2 = new JLabel("Choose your nickname");
        JPanel pan1 = new JPanel();
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        pan1.setLayout(new FlowLayout());

        label.setForeground(Color.WHITE);
        label.setBackground(Color.darkGray);
        label.setFont(new Font("Consolas", Font.BOLD, 18));
        pan1.add(label);

        label2.setFont(new Font("Consolas", Font.BOLD, 35));
        label2.setForeground(Color.WHITE);
        label2.setBackground(Color.darkGray);

        nick = new JTextField(25);
        nick.requestFocus(true);
        pan1.add(nick);
        pan1.setBackground(Color.darkGray);

        JButton scButton = new JButton("CONFIRM");
        scButton.setFont(new Font("Consolas", Font.BOLD, 18));
        scButton.setBackground(Color.darkGray);
        scButton.setBorderPainted(false);
        scButton.setForeground(Color.WHITE);
        scButton.setFocusable(false);

        add(Box.createRigidArea(new Dimension(0, 30)));
        add(label2);
        add(Box.createRigidArea(new Dimension(0, 140)));
        add(pan1);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(scButton);
        add(Box.createRigidArea(new Dimension(0, 80)));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pan1.setAlignmentX(Component.CENTER_ALIGNMENT);
        scButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        setVisible(true);
        repaint();

        scButton.addActionListener(event -> {
            try {
                makeLevel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    void makeLevel() throws IOException {
        nickname = nick.getText();
        System.out.println(nickname);
        getContentPane().removeAll();
        Reader.makeLevel(levelNumber);

        dispose();
        Game gam = new Game();
//        MainWindow men = new MainWindow(gam);
//        men.addKeyListener(gam.keys);
//        SwingUtilities.invokeLater((Runnable) gam);

//        Graphics g = getContentPane().getGraphics();
//        this.paintComponents(g);
    }


    public static void main(String[] args) {
        MenuWindow menu = new MenuWindow();
    }

}