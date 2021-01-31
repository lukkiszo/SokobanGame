import java.io.*;
import java.util.Comparator;
import java.util.Properties;
import java.util.Vector;

public class HighscoresParser {
    private InputStream file;
    Properties val = new Properties();

    Vector<HighScore> highscores = new Vector<>();
    static HighScoreComparator comparator = new HighScoreComparator();

    HighscoresParser() throws IOException {
        file = new FileInputStream("resources\\highscores.txt");
        val.load(file);
    }

    /**
     * If given highscore is in the top 5 then it's added and highscores are sorted again
     * @param highScore a highscore gained on this level
     */
    void addHighscore(HighScore highScore){
        if(highscores.elementAt(0).score > 0) {
            if (highscores.size() < 5) highscores.add(highScore);
            else if (highscores.elementAt(0).score < highScore.score) highscores.set(0, highScore);
            else return;
        }
        highscores.sort(comparator);
    }

    /**
     * Comparator that sorts {@link HighScore} object by their {@link HighScore#score} fields in an ascending order
     */
    static class HighScoreComparator implements Comparator<HighScore>
    {
        public int compare(HighScore h1, HighScore h2) { return h1.score - h2.score; }
    }

    void sort(Vector<HighScore> highscores){
        highscores.sort(comparator);
    }

    public void zapisPliku() throws IOException {
        FileWriter plikWy = null;
        try {
            plikWy = new FileWriter("resources\\highscores.txt");
            for (int i = 0; i<highscores.size() ; i++) {
                plikWy.write(highscores.elementAt(i).nickname + ";" + highscores.elementAt(i).score);
                plikWy.write('\n');
            }
        } finally {
            if (plikWy != null) {
                plikWy.close();
            }
        }
    }

    void read() throws IOException {
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader("resources\\highscores.txt"));
            String l = file.readLine();
            highscores.clear();
            while (l != null) {
                String[] p = l.split(";");
                HighScore hs = new HighScore(p[0], Integer.parseInt(p[1]));
                highscores.add(0, hs);
                l = file.readLine();
            }
            sort(highscores);
        } finally {
            if (file != null) {
                file.close();
            }
        }
    }
}
