import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class HighscoreWindow extends JFrame {
    private JButton back;
    private JLabel label2;

    HighscoresParser parser = new HighscoresParser();

    private JPanel pan;

    public int currentWidth;
    public int currentHeight;

    public int prefWidth = 800;
    public int prefHeight = 600;

    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    HighscoreWindow() throws IOException {
        super("Highscores");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(prefWidth,prefHeight);
        setLocationRelativeTo(null);

        this.setIconImage(new ImageIcon("resources/icon.png").getImage());

        parser.read();
        parser.sort(parser.highscores);

        back = new JButton("< Go to MainMenu");
        label2 = new JLabel("Highscores" );

        pan = new JPanel();

        back.setForeground(Color.WHITE);
        back.setBackground(Color.darkGray);
        back.setFocusable(false);
        back.setBorderPainted(false);

        label2.setForeground(Color.WHITE);
        label2.setBackground(Color.darkGray);
        pan.setBackground(Color.darkGray);
        setLayout(new BorderLayout());

        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(event -> backAction());
        this.add(pan, BorderLayout.CENTER);

        initComponents();

        setVisible(true);
        repaint();

    }

    void backAction()
    {
        this.dispose();
        new MenuWindow().setVisible(true);
    }

    private void initComponents() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                currentWidth = e.getComponent().getSize().width;
                currentHeight = e.getComponent().getSize().height;

                back.setFont(new Font("Consolas", Font.PLAIN, (int) (20*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                label2.setFont(new Font("Consolas", Font.PLAIN, (int) (35*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));

                pan.removeAll();

                pan.add(Box.createRigidArea(new Dimension(0, (int) (30*((double)currentHeight/(double)prefHeight)))));
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(label2);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (65*((double)currentHeight/(double)prefHeight)))));

                for(int i = 0; i<parser.highscores.size(); i++)
                {
                    JLabel score = new JLabel((i+1) +".  " + parser.highscores.elementAt(parser.highscores.size() - 1 - i).nickname +"    " + parser.highscores.elementAt(parser.highscores.size() - 1 - i).score);
                    score.setForeground(Color.WHITE);
                    score.setBackground(Color.darkGray);
                    score.setAlignmentX(Component.CENTER_ALIGNMENT);
                    score.setFont(new Font("Consolas", Font.PLAIN, (int) (25*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                    pan.add(score);
                    pan.add(Box.createRigidArea(new Dimension(0, (int) (20*((double)currentHeight/(double)prefHeight)))));
                    pan.add(new Box.Filler(minSize, prefSize, maxSize));
                }
                pan.add(back);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (30*((double)currentHeight/(double)prefHeight)))));
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
            }
        });
    }
}
