import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Klasa obslugujaca okno z wyborem nazwy gracza
 */
public class NickPicker extends JFrame{
    private JTextField nick;
    private JLabel label;
    private JLabel label2;
    private JPanel pan;
    private JButton back;
    private JButton scButton;
    int levelNumber = 1;

    public String nickname;


    public int prefWidth = 800;
    public int prefHeight = 600;

    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    /**
     * Konstruktor
     */
    NickPicker(){
        super("Nickname pick");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(prefWidth, prefHeight);
        setLocationRelativeTo(null);
        label = new JLabel("Nick: ");
        label2 = new JLabel("Choose your nickname");
        pan = new JPanel();
        back = new JButton("< Back");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        pan.setLayout(new FlowLayout());

        this.setIconImage(new ImageIcon("resources/icon.png").getImage());

        label.setForeground(Color.WHITE);
        label.setBackground(Color.darkGray);
        label.setFont(new Font("Consolas", Font.PLAIN, 18));
        pan.add(label);

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
        pan.add(nick);
        pan.setBackground(Color.darkGray);

        scButton = new JButton("Confirm >");
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
        add(pan);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(new Box.Filler(minSize, prefSize, maxSize));
        add(scButton);
        add(Box.createRigidArea(new Dimension(0, 60)));
        add(new Box.Filler(minSize, prefSize, maxSize));
        add(back);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(new Box.Filler(minSize, prefSize, maxSize));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pan.setAlignmentX(Component.CENTER_ALIGNMENT);
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

    /**
     * Metoda obslugujaca powrot do menu
     */
    public void backAction()
    {
        this.dispose();
        new MenuWindow().setVisible(true);
    }

    /**
     * Metoda tworzaca nowy poziom
     * @param levelNr numer poziomu
     * @throws IOException
     */
    public void makeLevel(int levelNr) throws IOException {
        dispose();
        MainWindow.totalScore = 0;
        Game gam = new Game(levelNr, nickname);
    }

}
