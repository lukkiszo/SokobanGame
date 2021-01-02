import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class MenuWindow extends JFrame{
    JButton startButton, scoresButton, exitButton;
    JTextField nick;
    JPanel pan = new JPanel();
    String nickname;
    int levelNumber = 1;
//    private Object Rectangle;

    MenuWindow(){
        super("Sokoban Game");
        this.add(pan);
        this.setContentPane(pan);
        setSize(800,600);
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("SOKOBAN");

        startButton = new JButton( "Start");
        scoresButton = new JButton("Highscores");
        exitButton = new JButton("Exit");

        title.setBounds(275, 35, 300, 100);
        title.setFont(new Font ("Consolas", Font.PLAIN, 50));
        title.setForeground(Color.white);
        pan.add(title);

        pan.add(startButton);
        startButton.setFont(new Font("Consolas", Font.PLAIN, 25));
        startButton.setBounds(315,175,148,80);
        startButton.setBackground(Color.darkGray);
        startButton.setForeground(Color.white);
        startButton.setBorderPainted(false);
        startButton.setFocusable(false);

        pan.add(scoresButton);
        scoresButton.setFont(new Font("Consolas", Font.PLAIN, 25));
        scoresButton.setBounds(240,285,296,80);
        scoresButton.setBackground(Color.darkGray);
        scoresButton.setBorderPainted(false);
        scoresButton.setFocusable(false);
        scoresButton.setForeground(Color.white);

        pan.add(exitButton);
        exitButton.setFont(new Font("Consolas", Font.PLAIN, 25));
        exitButton.setBounds(315, 395 ,148,80);
        exitButton.setBackground(Color.darkGray);
        exitButton.setBorderPainted(false);
        exitButton.setFocusable(false);
        exitButton.setForeground(Color.white);

        title.setVisible(true);
        setLayout(null);
        setSize(800,600);
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

        JLabel label = new JLabel("Nick");
        label.setBounds(150, 185, 100, 110);
        this.add(label);

        nick = new JTextField(10);
        nick.setText("Nickname");
        nick.requestFocus(true);
        nick.setBounds(240,185,296, 110);
        pan.setBackground(Color.pink);

        JButton scButton = new JButton("CONFIRM");
        scButton.setFont(new Font("Consolas", Font.BOLD, 10));
        scButton.setBounds(300,385,90,50);
        scButton.setBackground(Color.darkGray);
        scButton.setBorderPainted(false);
        scButton.setForeground(Color.WHITE);

        this.add(scButton);
        this.add(nick);
        this.repaint();

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
//        System.out.println(nickname);
        getContentPane().removeAll();

        Reader.makeLevel(levelNumber);
//        this.paint((Graphics) Rectangle);
//        MojPanel panel1 = new MojPanel();
//        panel1.repaint();
//        Rectangle rect = new Rectangle(100,100,100,100);
//        getContentPane().paint(rect);
        this.repaint();
    }

    public static void main(String[] args) {
        MenuWindow menu = new MenuWindow();
    }

    public class MojPanel extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.white);
            g.fillRect(0,0,500,250);
            g.setColor(Color.blue);
            g.fillRect(100,100, 50, 30);

        }
    }

}