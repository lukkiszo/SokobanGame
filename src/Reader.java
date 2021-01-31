import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

/**
 * Klasa pobierajaca dane z pliku config
 */
public class Reader {
    static Properties props = new Properties();
    static InputStream input = null;
    static String readFileName = null;
    static int prefHeight;
    static int prefWidth;

    /**
     * Metoda odczytujaca dane poziomu
     * @param number numer poziomu
     * @return obiekt z danymi poziomu
     * @throws IOException
     */
    public static Level makeLevel(Integer number) throws IOException {
        input = new FileInputStream("resources\\config.txt");
        props.load(input);
        readFileName = props.getProperty("file" + number);
        return new Level(readFileName);
    }

    /**
     * Metoda odczytujaca preferowane rozmiary okna
     */
    public static void getPrefSize()
    {
        prefWidth = Integer.parseInt(props.getProperty("prefWidth"));
        prefHeight = Integer.parseInt(props.getProperty("prefHeight"));
    }

    /**
     * Metoda odczytujaca ilosc poziomow z pliku konfiguracyjnego
     * @return liczba poziomow
     * @throws IOException
     */
    public static int getNumberOfLevels() throws IOException {
        input = new FileInputStream("resources\\config.txt");
        props.load(input);
        return Integer.parseInt(props.getProperty("numberOfFiles"));
    }
}