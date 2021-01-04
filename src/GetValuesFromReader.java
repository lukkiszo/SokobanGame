import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

    public class GetValuesFromReader {
        public static Quintet<Integer[], Integer[], Integer[], Integer, Integer> getPropValues(String nr) throws IOException {
                Properties val = new Properties();
                String fileName = "resources\\" + nr;

                InputStream input = new FileInputStream(fileName);

                val.load(input);

                String levelNumber_str = val.getProperty("levelNumber");
                String numberOfObstacles_str = val.getProperty("numberOfObstacles");
                String playerPosition = val.getProperty("playerPosition");
                String obstacles = val.getProperty("obstacles");
                String walls = val.getProperty("walls");

                String[] arr1 = obstacles.split(";");
                Integer[] int_obstacles = new Integer[arr1.length * 2];
                System.out.println("Obstacles: ");
                for (int i = 0 ; i < arr1.length ; i++)
                {
                   String[] arr2 = arr1[i].split(",");
                   System.out.println(Integer.parseInt(arr2[0]) + "," + Integer.parseInt(arr2[1]) );
                   int_obstacles[i*2] = Integer.parseInt(arr2[0]);
                   int_obstacles[(i*2)+1] = Integer.parseInt(arr2[1]);
                }

                String[] arr3 = walls.split(";");
                Integer[] int_walls = new Integer[arr3.length * 2];
                System.out.println("Walls: ");
                for (int i = 0 ; i < arr3.length ; i++) {
                    String[] arr4 = arr3[i].split(",");
                    System.out.println(Integer.parseInt(arr4[0]) + "," + Integer.parseInt(arr4[1]) );
                    int_walls[i*2] = Integer.parseInt(arr4[0]);
                    int_walls[(i*2)+1] = Integer.parseInt(arr4[1]);
                }

                System.out.println("Player Position: ");
                String[] arr5 = playerPosition.split(",");
                Integer[] plPos = new Integer[2];
                plPos[0] = Integer.parseInt(arr5[0]);
                plPos[1] = Integer.parseInt(arr5[1]);
                System.out.println(Integer.parseInt(arr5[0]) + "," + Integer.parseInt(arr5[1]) );

                int levelNumber = Integer.parseInt(levelNumber_str);
                int numberOfObstacles = Integer.parseInt(numberOfObstacles_str);
                System.out.println("Level Number: ");
                System.out.println(levelNumber);
                System.out.println("Number of Obstacles: ");
                System.out.println(numberOfObstacles);

                return new Quintet<>(plPos, int_walls, int_obstacles, levelNumber, numberOfObstacles);
        }
    }