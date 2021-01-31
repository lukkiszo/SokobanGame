import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Klasa obslugujaca zdarzenia nacisniecia przyciskow na klawiaturze
 */
public class Keys extends KeyAdapter {
    private Player player;
    private Game game;

    /**
     * Konstruktor
     * @param player Aktualny gracz
     * @param game Aktualna gra
     */
    public Keys(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    /**
     * Metoda obslugujaca zdarzenie nacisniecia przycisku na klawiaturze
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) player.goUp = true;
        else if (key == KeyEvent.VK_DOWN) player.goDown = true;
        else if (key == KeyEvent.VK_LEFT) player.goLeft = true;
        else if (key == KeyEvent.VK_RIGHT) player.goRight = true;
        else if (key == KeyEvent.VK_W) player.wantToDeleteOnUp = true;
        else if (key == KeyEvent.VK_S) player.wantToDeleteOnDown = true;
        else if (key == KeyEvent.VK_A) player.wantToDeleteOnLeft = true;
        else if (key == KeyEvent.VK_D) player.wantToDeleteOnRight = true;
    }

    /**
     * Metoda obslugujaca zdarzenie zakonczenia naciskania przycisku na klawiaturze
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) player.goUp = false;
        else if (key == KeyEvent.VK_DOWN) player.goDown = false;
        else if (key == KeyEvent.VK_LEFT) player.goLeft = false;
        else if (key == KeyEvent.VK_RIGHT) player.goRight = false;
        else if (key == KeyEvent.VK_W) player.wantToDeleteOnUp = false;
        else if (key == KeyEvent.VK_S) player.wantToDeleteOnDown = false;
        else if (key == KeyEvent.VK_A) player.wantToDeleteOnLeft = false;
        else if (key == KeyEvent.VK_D) player.wantToDeleteOnRight = false;
        else if (key == KeyEvent.VK_ESCAPE) game.togglePause();
        else if (key == KeyEvent.VK_Z) game.backMoves();
    }
}
