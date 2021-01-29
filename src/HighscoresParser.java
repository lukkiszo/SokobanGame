import java.io.*;
import java.util.Properties;
import java.util.Vector;

public class HighscoresParser {
    private InputStream file;
    Properties val = new Properties();

    Vector<HighScore> highscores = new Vector<>();

    HighscoresParser() throws IOException {
        file = new FileInputStream("resources\\highscores.txt");
        val.load(file);
    }

    /**
     * If given highscore is in the top 5 then it's added and highscores are sorted again
     * @param highScore a highscore gained on this level
     */
    void addHighscore(HighScore highScore){
        if(highscores.size() < 5) highscores.add(highScore);
        else if(highscores.elementAt(4).score < highScore.score) highscores.set(4, highScore);
        else return;
    }

    void sort(Vector<HighScore> highscores){
        for (int i = 0; i < highscores.size() - 1; i++){
            for (int j = 0; j < highscores.size() - 1; j++){
                if(highscores.elementAt(j).score > highscores.elementAt(j+1).score){
                    int temp = highscores.elementAt(j).score;
                    String temp1 = highscores.elementAt(j).nickname;
                    highscores.elementAt(j).score = highscores.elementAt(j+1).score;
                    highscores.elementAt(j).nickname = highscores.elementAt(j+1).nickname;
                    highscores.elementAt(j+1).score = temp;
                    highscores.elementAt(j+1).nickname = temp1;
                }
            }
        }
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
                System.out.println(l);
                String[] p = l.split(";");
                HighScore hs = new HighScore(p[0], Integer.parseInt(p[1]));
                highscores.add(0, hs);
                l = file.readLine();

                System.out.println(highscores.size());
            }
            sort(highscores);
        } finally {
            if (file != null) {
                file.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HighscoresParser parser = new HighscoresParser();
        parser.read();
        parser.addHighscore(new HighScore("proba", 1000));
        parser.zapisPliku();


    }
}
