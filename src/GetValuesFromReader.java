import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

public class GetValuesFromReader {
    public static Nonet<Integer[], Integer[], Integer[], Integer, Integer, Integer[], Integer[], Integer, Integer> getPropValues(String nr) throws IOException {
        Properties val = new Properties();
        String fileName = "resources\\" + nr;

        InputStream input = new FileInputStream(fileName);

        val.load(input);

        String levelNumber_str = val.getProperty("levelNumber");
        String numberOfObstacles_str = val.getProperty("numberOfObstacles");
        String playerPosition = val.getProperty("playerPosition");
        String obstacles = val.getProperty("obstacles");
        String walls = val.getProperty("walls");
        String correctPlaces = val.getProperty("correctPlaces");
        String teleportsPlaces = val.getProperty("teleport");
        String numberOfWallsX_str = val.getProperty("wallsX");
        String numberOfWallsY_str = val.getProperty("wallsY");

        String[] arr1 = obstacles.split(";");
        Integer[] int_obstacles = new Integer[arr1.length * 2];
        for (int i = 0 ; i < arr1.length ; i++)
        {
            String[] arr2 = arr1[i].split(",");
            int_obstacles[i*2] = Integer.parseInt(arr2[0]);
            int_obstacles[(i*2)+1] = Integer.parseInt(arr2[1]);
        }

        String[] arr3 = walls.split(";");
        Integer[] int_walls = new Integer[arr3.length * 2];
        for (int i = 0 ; i < arr3.length ; i++) {
            String[] arr4 = arr3[i].split(",");
            int_walls[i*2] = Integer.parseInt(arr4[0]);
            int_walls[(i*2)+1] = Integer.parseInt(arr4[1]);
        }

        String[] arr5 = playerPosition.split(",");
        Integer[] plPos = new Integer[2];
        plPos[0] = Integer.parseInt(arr5[0]);
        plPos[1] = Integer.parseInt(arr5[1]);

        String[] arr6 = correctPlaces.split(";");
        Integer[] int_correctPlacesPosition = new Integer[arr6.length * 2];
        for (int i = 0 ; i < arr6.length ; i++) {
            String[] arr7 = arr6[i].split(",");
            int_correctPlacesPosition[i*2] = Integer.parseInt(arr7[0]);
            int_correctPlacesPosition[(i*2)+1] = Integer.parseInt(arr7[1]);
        }

        String[] arr8 = teleportsPlaces.split(";");
        Integer[] int_teleports = new Integer[arr8.length * 2];
        for (int i = 0 ; i < arr8.length ; i++) {
            String[] arr9 = arr8[i].split(",");
            int_teleports[i*2] = Integer.parseInt(arr9[0]);
            int_teleports[(i*2)+1] = Integer.parseInt(arr9[1]);
        }

        int levelNumber = Integer.parseInt(levelNumber_str);
        int numberOfObstacles = Integer.parseInt(numberOfObstacles_str);
        int int_wallsX = Integer.parseInt(numberOfWallsX_str);
        int int_wallsY = Integer.parseInt(numberOfWallsY_str);
        return new Nonet<>(plPos, int_walls, int_obstacles, levelNumber, numberOfObstacles, int_correctPlacesPosition, int_teleports, int_wallsX, int_wallsY);
    }
}