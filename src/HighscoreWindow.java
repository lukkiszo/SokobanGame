import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HighscoreWindow extends JFrame {

    private JButton back;
    private JLabel label2;
    private JLabel score1;
    private JLabel score2;
    private JLabel score3;
    private JLabel score4;
    private JLabel score5;

    private JPanel pan;

    public int currentWidth;
    public int currentHeight;

    public int prefWidth = 800;
    public int prefHeight = 600;

    Dimension minSize = new Dimension(1, 1);
    Dimension prefSize = new Dimension(1, 20);
    Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);

    HighscoreWindow(){
        super("Highscores");
        getContentPane().setBackground(Color.darkGray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(prefWidth,prefHeight);
        setLocationRelativeTo(null);

        back = new JButton("< Back");
        label2 = new JLabel("Highscores" );
        score1 = new JLabel("1.  ABCD    123456789");
        score2 = new JLabel("2.  ACBD    122456789");
        score3 = new JLabel("3.  ADCB    121456789");
        score4 = new JLabel("4.  ABDC    120456789");
        score5 = new JLabel("5.  BCDA    119456789");

        pan = new JPanel();

        back.setForeground(Color.WHITE);
        back.setBackground(Color.darkGray);
        back.setFocusable(false);
        back.setBorderPainted(false);

        label2.setForeground(Color.WHITE);
        label2.setBackground(Color.darkGray);
        pan.setBackground(Color.darkGray);
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
        setLayout(new BorderLayout());

        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));

        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        score1.setAlignmentX(Component.CENTER_ALIGNMENT);
        score2.setAlignmentX(Component.CENTER_ALIGNMENT);
        score3.setAlignmentX(Component.CENTER_ALIGNMENT);
        score4.setAlignmentX(Component.CENTER_ALIGNMENT);
        score5.setAlignmentX(Component.CENTER_ALIGNMENT);
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
                score1.setFont(new Font("Consolas", Font.PLAIN, (int) (25*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                score2.setFont(new Font("Consolas", Font.PLAIN, (int) (25*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                score3.setFont(new Font("Consolas", Font.PLAIN, (int) (25*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                score4.setFont(new Font("Consolas", Font.PLAIN, (int) (25*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));
                score5.setFont(new Font("Consolas", Font.PLAIN, (int) (25*(((double)currentWidth + (double)currentHeight)/((double)prefWidth + (double)prefHeight)))));

                pan.removeAll();

                pan.add(Box.createRigidArea(new Dimension(0, (int) (30*((double)currentHeight/(double)prefHeight)))));
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(label2);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (65*((double)currentHeight/(double)prefHeight)))));
                pan.add(score1);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (20*((double)currentHeight/(double)prefHeight)))));
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(score2);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (20*((double)currentHeight/(double)prefHeight)))));
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(score3);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (20*((double)currentHeight/(double)prefHeight)))));
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(score4);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (20*((double)currentHeight/(double)prefHeight)))));
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(score5);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (45*((double)currentHeight/(double)prefHeight)))));
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
                pan.add(back);
                pan.add(Box.createRigidArea(new Dimension(0, (int) (30*((double)currentHeight/(double)prefHeight)))));
                pan.add(new Box.Filler(minSize, prefSize, maxSize));
            }
        });
    }
}
