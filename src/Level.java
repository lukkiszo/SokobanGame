import java.io.IOException;
import java.util.*;

public class Level {
    int numberOfObstacles;
    int levelNumber;
    int numberOfWallsX;
    int numberOfWallsY;
    Vector<Pair<Integer, Integer>> obstaclesPosition = new Vector<>();
    Vector<Pair<Integer, Integer>> wallsPosition = new Vector<>();
    Vector<Pair<Integer, Integer>> playerPosition = new Vector<>();
    Vector<Pair<Integer, Integer>> correctPlacesPosition = new Vector<>();
    Vector<Pair<Integer, Integer>> teleportsPosition = new Vector<>();

    Level(String nameOfFile)
    {
        try
        {
            var nonet = GetValuesFromReader.getPropValues(nameOfFile);
            numberOfObstacles = nonet.e;
            levelNumber = nonet.d;
            numberOfWallsX = nonet.h;
            numberOfWallsY = nonet.i;

            for(int i = 0; i < nonet.c.length; i += 2)
            {
                obstaclesPosition.add(new Pair<>(nonet.c[i], nonet.c[i + 1]));
            }
            for(int i = 0; i<nonet.b.length; i += 2)
            {
                wallsPosition.add(new Pair<>(nonet.b[i], nonet.b[i + 1]));
            }
            for(int i = 0; i<nonet.a.length; i += 2)
            {
                playerPosition.add(new Pair<>(nonet.a[i], nonet.a[i + 1]));
            }
            for(int i = 0; i < nonet.f.length; i += 2)
            {
                correctPlacesPosition.add(new Pair<>(nonet.f[i], nonet.f[i + 1]));
            }
            for(int i = 0; i < nonet.g.length; i += 2)
            {
                teleportsPosition.add(new Pair<>(nonet.g[i], nonet.g[i + 1]));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
