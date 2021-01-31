import java.io.*;
import java.util.Comparator;
import java.util.Properties;
import java.util.Vector;

/**
 * Klasa odpowiadajaca za odczyt i zapis najlepszych wynikow
 */
public class HighscoresParser {
    private InputStream file;
    Properties val = new Properties();

    Vector<HighScore> highscores = new Vector<>();
    static HighScoreComparator comparator = new HighScoreComparator();

    /**
     * Konstruktor
     * @throws IOException
     */
    HighscoresParser() throws IOException {
        file = new FileInputStream("resources\\highscores.txt");
        val.load(file);
    }

    /**
     * Metoda sprawdzajaca czy podany highscore zawiera sie w top5 i sortujaca wyniki
     * @param highScore wynik uzyskany w grze
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
     * Komparator sortujacy {@link HighScore} poprzez ich {@link HighScore#score} w kolejnosci rosnacej
     */
    static class HighScoreComparator implements Comparator<HighScore>
    {
        public int compare(HighScore h1, HighScore h2) { return h1.score - h2.score; }
    }

    /**
     * Metoda sortujaca liste najlepszych wynikow
     * @param highscores Zbior najlepszych wynikow
     */
    void sort(Vector<HighScore> highscores){
        highscores.sort(comparator);
    }

    /**
     * Metoda zapisujaca liste najlepszych wynikow do pliku
     * @throws IOException
     */
    public void saveHighscores() throws IOException {
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

    /**
     * Metoda odczytujaca najlepsze wyniki z pliku
     * @throws IOException
     */
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
