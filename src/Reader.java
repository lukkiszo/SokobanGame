import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public class Reader {
    static Properties props = new Properties();
    static InputStream input = null;
    static String readedFileName = null;

    public static Level makeLevel(Integer number) throws IOException {
        input = new FileInputStream("resources\\config.txt");
        props.load(input);
        readedFileName = props.getProperty("file"+number);

        Level level = new Level(readedFileName);
        return level;
    }

    public static int getNumberOfLevels() throws IOException {
        input = new FileInputStream("resources\\config.txt");
        props.load(input);
        int maxFile = Integer.parseInt(props.getProperty("numberOfFiles"));
        return maxFile;
    }

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        for(int i=0; i<reader.getNumberOfLevels(); i++)
        {
            reader.makeLevel(i+1);
        }
    }
}