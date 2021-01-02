import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public class Reader {
    static Properties props = new Properties();
    static InputStream input = null;
    static String readFileName = null;

    public static Level makeLevel(Integer number) throws IOException {
        input = new FileInputStream("resources\\config.txt");
        props.load(input);
        readFileName = props.getProperty("file" + number);

        return new Level(readFileName);
    }

    public static int getNumberOfLevels() throws IOException {
        input = new FileInputStream("resources\\config.txt");
        props.load(input);
        return Integer.parseInt(props.getProperty("numberOfFiles"));
    }

    public static void main(String[] args) throws IOException {
        for(int i = 0; i < Reader.getNumberOfLevels(); i++)
        {
            Reader.makeLevel(i+1);
        }
    }
}