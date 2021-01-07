import java.io.IOException;
import java.util.*;

public class Level {
    int numberOfObstacles;
    int levelNumber;
    Vector<Pair<Integer, Integer>> obstaclesPosition = new Vector<>();
    Vector<Pair<Integer, Integer>> wallsPosition = new Vector<>();
    Vector<Pair<Integer, Integer>> playerPosition = new Vector<>();
    Vector<Pair<Integer, Integer>> correctPlacesPosition = new Vector<>();

    Level(String nameOfFile)
    {
        try
        {
            var sextet = GetValuesFromReader.getPropValues(nameOfFile);
            numberOfObstacles = sextet.e;
            levelNumber = sextet.d;
            for(int i = 0; i < sextet.c.length; i += 2)
            {
                obstaclesPosition.add(new Pair<>(sextet.c[i], sextet.c[i + 1]));
            }
            for(int i = 0; i<sextet.b.length; i += 2)
            {
                wallsPosition.add(new Pair<>(sextet.b[i], sextet.b[i + 1]));
            }
            for(int i = 0; i<sextet.a.length; i += 2)
            {
                playerPosition.add(new Pair<>(sextet.a[i], sextet.a[i + 1]));
            }
            for(int i = 0; i < sextet.f.length; i += 2)
            {
                correctPlacesPosition.add(new Pair<>(sextet.f[i], sextet.f[i + 1]));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
