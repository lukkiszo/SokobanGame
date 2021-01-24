import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

public class MenuWindow extends JFrame{
    JButton startButton, scoresButton, exitButton;
    JTextField nick;
    JPanel pan = new JPanel();
    public String nickname;
    int levelNumber = 1;

    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    MenuWindow(){
        super("SOKOBAN");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800,600);
        setLocationRelativeTo(null);
        JPanel pan = new JPanel();
        JLabel title = new JLabel("SOKOBAN");

        startButton = new JButton( "Start");
        scoresButton = new JButton("Highscores");
        exitButton = new JButton("Exit");

        title.setFont(new Font ("Consolas", Font.PLAIN, 50));
        title.setForeground(Color.white);

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
        pan.add(Box.createRigidArea(new Dimension(0, 80)));

        pan.add(scoresButton);
        scoresButton.setFont(new Font("Consolas", Font.PLAIN, 25));
        scoresButton.setBackground(Color.darkGray);
        scoresButton.setBorderPainted(false);
        scoresButton.setFocusable(false);
        scoresButton.setForeground(Color.white);
        pan.add(Box.createRigidArea(new Dimension(0, 80)));

        pan.add(exitButton);
        exitButton.setFont(new Font("Consolas", Font.PLAIN, 25));
        exitButton.setBackground(Color.darkGray);
        exitButton.setBorderPainted(false);
        exitButton.setFocusable(false);
        exitButton.setForeground(Color.white);

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
    void backAction()
    {
        this.dispose();
        new MenuWindow().setVisible(true);
    }
    void showHighscores()
    {
        getContentPane().removeAll();
        setSize(800, 600);

        JButton back = new JButton("< Back");
        JLabel label2 = new JLabel("Highscores" );
        JLabel score1 = new JLabel("1.  ABCD    123456789");
        JLabel score2 = new JLabel("2.  ACBD    122456789");
        JLabel score3 = new JLabel("3.  ADCB    121456789");
        JLabel score4 = new JLabel("4.  ABDC    120456789");
        JLabel score5 = new JLabel("5.  BCDA    119456789");

        JPanel pan1 = new JPanel();

        back.setForeground(Color.WHITE);
        back.setBackground(Color.darkGray);
        back.setFont(new Font("Consolas", Font.PLAIN, 20));
        back.setFocusable(false);
        back.setBorderPainted(false);

        label2.setForeground(Color.WHITE);
        label2.setBackground(Color.darkGray);
        pan1.setBackground(Color.darkGray);
        score1.setForeground(Color.WHITE);
        score1.setBackground(Color.darkGray);
        score2.setForeground(Color.WHITE);
        score2.setBackground(Color.darkGray);
        score3.setForeground(Color.WHITE);
        score3.setBackground(Color.darkGray);
        score4.setForeground(Color.WHITE);
        score4.setBackground(Color.darkGray);
        score5.setForeground(Color.WHITE);
        score5.setBackground(Color.darkGray);
        label2.setFont(new Font("Consolas", Font.PLAIN, 35));
        score1.setFont(new Font("Consolas", Font.PLAIN, 25));
        score2.setFont(new Font("Consolas", Font.PLAIN, 25));
        score3.setFont(new Font("Consolas", Font.PLAIN, 25));
        score4.setFont(new Font("Consolas", Font.PLAIN, 25));
        score5.setFont(new Font("Consolas", Font.PLAIN, 25));
        setLayout(new BorderLayout());

        pan1.setLayout(new BoxLayout(pan1, BoxLayout.Y_AXIS));
        pan1.add(Box.createRigidArea(new Dimension(0, 30)));
        pan1.add(new Box.Filler(minSize, prefSize, maxSize));
        pan1.add(label2);
        pan1.add(Box.createRigidArea(new Dimension(0, 65)));
        pan1.add(score1);
        pan1.add(Box.createRigidArea(new Dimension(0, 20)));
        pan1.add(new Box.Filler(minSize, prefSize, maxSize));
        pan1.add(score2);
        pan1.add(Box.createRigidArea(new Dimension(0, 20)));
        pan1.add(new Box.Filler(minSize, prefSize, maxSize));
        pan1.add(score3);
        pan1.add(Box.createRigidArea(new Dimension(0, 20)));
        pan1.add(new Box.Filler(minSize, prefSize, maxSize));
        pan1.add(score4);
        pan1.add(Box.createRigidArea(new Dimension(0, 20)));
        pan1.add(new Box.Filler(minSize, prefSize, maxSize));
        pan1.add(score5);
        pan1.add(Box.createRigidArea(new Dimension(0, 45)));
        pan1.add(new Box.Filler(minSize, prefSize, maxSize));
        pan1.add(back);
        pan1.add(Box.createRigidArea(new Dimension(0, 30)));
        pan1.add(new Box.Filler(minSize, prefSize, maxSize));

        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        score1.setAlignmentX(Component.CENTER_ALIGNMENT);
        score2.setAlignmentX(Component.CENTER_ALIGNMENT);
        score3.setAlignmentX(Component.CENTER_ALIGNMENT);
        score4.setAlignmentX(Component.CENTER_ALIGNMENT);
        score5.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(event -> backAction());
        this.add(pan1, BorderLayout.CENTER);


        setVisible(true);
        repaint();

    }

    void nickPicker()
    {
        getContentPane().removeAll();
        setSize(800, 600);
        JLabel label = new JLabel("Nick: ");
        JLabel label2 = new JLabel("Choose your nickname");
        JPanel pan1 = new JPanel();
        JButton back = new JButton("< Back");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        pan1.setLayout(new FlowLayout());

        label.setForeground(Color.WHITE);
        label.setBackground(Color.darkGray);
        label.setFont(new Font("Consolas", Font.PLAIN, 18));
        pan1.add(label);

        back.setForeground(Color.WHITE);
        back.setBackground(Color.darkGray);
        back.setFont(new Font("Consolas", Font.PLAIN, 18));
        back.setFocusable(false);
        back.setBorderPainted(false);

        label2.setFont(new Font("Consolas", Font.PLAIN, 35));
        label2.setForeground(Color.WHITE);
        label2.setBackground(Color.darkGray);

        nick = new JTextField(25);
        nick.requestFocus(true);
        pan1.add(nick);
        pan1.setBackground(Color.darkGray);

        JButton scButton = new JButton("Confirm >");
        scButton.setFont(new Font("Consolas", Font.PLAIN, 18));
        scButton.setBackground(Color.darkGray);
        scButton.setBorderPainted(false);
        scButton.setForeground(Color.WHITE);
        scButton.setFocusable(false);

        add(Box.createRigidArea(new Dimension(0, 30)));
        add(new Box.Filler(minSize, prefSize, maxSize));
        add(label2);
        add(Box.createRigidArea(new Dimension(0, 140)));
        add(new Box.Filler(minSize, prefSize, maxSize));
        add(pan1);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(new Box.Filler(minSize, prefSize, maxSize));
        add(scButton);
        add(Box.createRigidArea(new Dimension(0, 60)));
        add(new Box.Filler(minSize, prefSize, maxSize));
        add(back);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(new Box.Filler(minSize, prefSize, maxSize));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pan1.setAlignmentX(Component.CENTER_ALIGNMENT);
        scButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);

        back.addActionListener(event -> backAction());

        setVisible(true);
        repaint();

        scButton.addActionListener(event -> {
            try {
                nickname = nick.getText();
                makeLevel(levelNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void makeLevel(int levelNr) throws IOException {
        dispose();
        Game gam = new Game(levelNr);
    }


    public static void main(String[] args) {
        MenuWindow menu = new MenuWindow();
    }


}
