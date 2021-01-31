import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keys extends KeyAdapter {
    private Player player;
    private Game game;

    public Keys(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) player.goUp = true;
        else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) player.goDown = true;
        else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) player.goLeft = true;
        else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) player.goRight = true;
        else if (key == KeyEvent.VK_E) player.wantToDelete = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) player.goUp = false;
        else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) player.goDown = false;
        else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) player.goLeft = false;
        else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) player.goRight = false;
        else if (key == KeyEvent.VK_E) player.wantToDelete = false;
        else if (key == KeyEvent.VK_ESCAPE) game.togglePause();
    }
}
