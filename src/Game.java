import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game extends JComponent implements Runnable{
    public int levelNumber;
    private Player player;
    private Obstacle[] obstacle;
    private Walls[] walls;
    private CorrectPlaces[] correctPlace;
    private Level lev;
    public Keys keys;
    Thread thread;

    long currentTime;
    long lastTime = 0;

    private boolean running = false;
    private MainWindow mainWindow;

    public Game(int levelNr) throws IOException {
        levelNumber = levelNr;
        lev = Reader.makeLevel(levelNumber);
        walls = new Walls[lev.wallsPosition.size()];
        for (int i = 0; i<lev.wallsPosition.size(); i++)
        {
            walls[i] = new Walls(levelNumber, i);
        }
        player = new Player(levelNumber);

// zrobione tak ze jest tyle samo dobrych miejsc co przeszkod
// jesli chcielibysmy zrobic wiecej dobrych miejsc niz przeszkod to trzeba zmienic, tak samo w paintComponent

        obstacle = new Obstacle[lev.numberOfObstacles];
        correctPlace = new CorrectPlaces[lev.numberOfObstacles];
        for (int i = 0; i<lev.numberOfObstacles; i++)
        {
            obstacle[i] = new Obstacle(levelNumber, i);
            correctPlace[i] = new CorrectPlaces(levelNumber, i);
        }

        keys = new Keys(player, this);
        mainWindow = new MainWindow(this);
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
        System.out.println("START");
    }

//     obstacle - wall
    private void collisionObstacleWall(){
        for(int i = 0; i<lev.numberOfObstacles; i++)
        {
            obstacle[i].rightWall = false;
            obstacle[i].leftWall = false;
            obstacle[i].upWall = false;
            obstacle[i].downWall = false;
        }

        for (int i = 0; i<lev.wallsPosition.size(); i++) {
            for (int j = 0; j<lev.numberOfObstacles; j++){

                if(obstacle[j].xpos + 1 == walls[i].xpos && obstacle[j].ypos == walls[i].ypos){
                    obstacle[j].rightWall = true;
                }

                if(obstacle[j].ypos + 1 == walls[i].ypos && obstacle[j].xpos == walls[i].xpos){
                    obstacle[j].downWall = true;
                }

                if(obstacle[j].ypos == walls[i].ypos + 1 && obstacle[j].xpos == walls[i].xpos){
                    obstacle[j].upWall = true;
                }

                if(obstacle[j].xpos == walls[i].xpos + 1 && obstacle[j].ypos == walls[i].ypos){
                    obstacle[j].leftWall = true;
                }
            }
        }
    }

//     player - obstacle
    private void collisionWithObstacles() {
        player.rightCollision = false;
        player.leftCollision = false;
        player.upCollision = false;
        player.downCollision = false;

            for (int i = 0; i < obstacle.length; i++) {
                if (player.position[0] + 1 == obstacle[i].xpos  && player.position[1] + 0.5 == obstacle[i].ypos + 0.5) {
                    player.rightCollision = true;
                    if(player.goRight && !obstacle[i].rightCollision && !obstacle[i].rightWall){
                        obstacle[i].xpos += 1;
                        player.position[0] += 1;
                    }
                }

                if (player.position[0] + 0.5 == obstacle[i].xpos + 0.5 && player.position[1] + 1 == obstacle[i].ypos) {
                    player.downCollision = true;
                    if (player.goDown && !obstacle[i].downCollision && !obstacle[i].downWall) {
                        obstacle[i].ypos += 1;
                        player.position[1] += 1;
                    }
                }

                if (player.position[0] + 0.5 == obstacle[i].xpos + 0.5 && player.position[1] == obstacle[i].ypos + 1) {
                    player.upCollision = true;
                    if (player.goUp && !obstacle[i].upCollision && !obstacle[i].upWall) {
                        obstacle[i].ypos -= 1;
                        player.position[1] -= 1;
                    }
                }

                if (player.position[0] == obstacle[i].xpos + 1 && player.position[1] + 0.5 == obstacle[i].ypos + 0.5) {
                    player.leftCollision = true;
                    if (player.goLeft && !obstacle[i].leftCollision && !obstacle[i].leftWall) {
                        obstacle[i].xpos -= 1;
                        player.position[0] -= 1;
                    }
                }

            }
    }

//    obstacle - obstacle
    public void collisionWithOtherObstacle(){

        for(int i = 0; i<lev.numberOfObstacles; i++){
            obstacle[i].rightCollision = false;
            obstacle[i].downCollision = false;
            obstacle[i].leftCollision = false;
            obstacle[i].upCollision = false;
            for (int j = 0; j<lev.numberOfObstacles; j++) {
                if (obstacle[i].xpos + 1 == obstacle[j].xpos && obstacle[i].ypos == obstacle[j].ypos) {
                    obstacle[i].rightCollision = true;
                    break;
                }

                if(obstacle[i].xpos == obstacle[j].xpos && obstacle[i].ypos + 1 == obstacle[j].ypos) {
                    obstacle[i].downCollision = true;
                    break;
                }

                if(obstacle[i].xpos == obstacle[j].xpos + 1 && obstacle[i].ypos == obstacle[j].ypos) {
                    obstacle[i].leftCollision = true;
                    break;
                }

                if(obstacle[i].xpos == obstacle[j].xpos && obstacle[i].ypos == obstacle[j].ypos + 1) {
                    obstacle[i].upCollision = true;
                    break;
                }
            }
        }
    }


    public int isOnCorrectPlace()
    {
        int k = 0;
        for(int i = 0; i<lev.numberOfObstacles; i++)
        {
            obstacle[i].isOnCorrectPlace = false;
            for(int j = 0; j<correctPlace.length; j++)
            {
                if(obstacle[i].xpos == correctPlace[j].xpos && obstacle[i].ypos == correctPlace[j].ypos)
                {
                    obstacle[i].isOnCorrectPlace = true;
                    k++;
                }
            }
        }
        return k;
    }

        public boolean isVictory()
    {
        boolean victory = false;
        if(isOnCorrectPlace() == lev.numberOfObstacles)
        {
            victory = true;
        }
        return victory;
    }

    public void playerMove()
    {
        for(int i = 0; i<lev.numberOfObstacles; i++) {
            if (player.goRight && !player.rightWall && !player.rightCollision && !obstacle[i].rightWall) {
                player.position[0] += 1;
                break;
            } else if (player.goLeft && !player.leftWall && !player.leftCollision && !obstacle[i].leftWall) {
                player.position[0] -= 1;
                break;
            } else if (player.goUp && !player.upWall && !player.upCollision && !obstacle[i].upWall) {
                player.position[1] -= 1;
                break;
            } else if (player.goDown && !player.downWall && !player.downCollision && !obstacle[i].downWall) {
                player.position[1] += 1;
                break;
            }
        }
    }

    public boolean nextLevel() throws IOException {
        levelNumber += 1;
        mainWindow.makeLevel(levelNumber);
        return true;
    }

    @Override
    public void run() {
        while (running)
        {
            try {
                update();
            } catch (IOException e) {
                e.printStackTrace();
            }
            repaint();
        }
        stop();
//        update();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() throws IOException {
        currentTime = System.currentTimeMillis();

        if(currentTime - lastTime > 300) {
            player.collisionWithWalls();
            collisionWithObstacles();
            playerMove();
            collisionWithOtherObstacle();
            collisionObstacleWall();
            isOnCorrectPlace();

            if(isVictory())
            {
                System.out.println("Wygrana, NEXT LEVEL");
                nextLevel();
            }
            lastTime = System.currentTimeMillis();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        player.paintComponent(g);
        for(int i = 0; i<lev.numberOfObstacles; i++)
        {
            obstacle[i].paintComponent(g);
            correctPlace[i].paintComponent(g);
        }

        for(int i = 0; i<lev.wallsPosition.size(); i++)
        {
            walls[i].paintComponent(g);
        }

    }
}
