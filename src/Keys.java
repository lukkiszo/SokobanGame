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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        super.keyPressed(e);
        int key = e.getKeyCode();


        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
            player.goUp = true;
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
            player.goDown = true;
        }
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            player.goLeft = true;
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            player.goRight = true;
        }

        if(key == KeyEvent.VK_ESCAPE){
            game.escapePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        super.keyReleased(e);

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
            player.goUp = false;
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
            player.goDown = false;
        }
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
            player.goLeft = false;
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            player.goRight = false;
        }

        if(key == KeyEvent.VK_ESCAPE){
            game.escapePressed = false;
        }

    }
}
