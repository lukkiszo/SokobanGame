import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game extends JComponent implements Runnable{
    public int levelNumber = 1;
    private Walls walls;
    private Player player;
    private Obstacle obstacle;
//    private Obstacle[] obstacle;
    private CorrectPlaces correctPlace;
    private Level lev;
    public Keys keys;
    Thread thread;

    long currentTime;
    long lastTime = 0;

    public boolean rightWall = false;
    public boolean leftWall = false;
    public boolean upWall = false;
    public boolean downWall = false;


    private boolean running = false;
    private MainWindow mainWindow;

    public Game() throws IOException {
        lev = Reader.makeLevel(levelNumber);
        walls = new Walls(levelNumber);
        player = new Player(levelNumber);
        obstacle = new Obstacle(levelNumber);
//        obstacle = new Obstacle[lev.numberOfObstacles];
        correctPlace = new CorrectPlaces(levelNumber);
//        for(int i = 0; i<lev.numberOfObstacles; i++)
//        {
//            obstacle[i] = new Obstacle(levelNumber);
//        }
        keys = new Keys(player, this);
        mainWindow = new MainWindow(this);
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
        System.out.println("START");
    }

    // obstacle - wall
    private boolean collisionObstacleWall(){
        rightWall = false;
        leftWall = false;
        upWall = false;
        downWall = false;

        for (int i = 0; i<lev.wallsPosition.size(); i++) {
            for (int j = 0; j<lev.numberOfObstacles; j++){
                if(obstacle.positions[3 * j] + 1 == walls.position[2 * i] && obstacle.positions[3 * j + 1] == walls.position[2 * i + 1]){
                    rightWall = true;
                }

                if(obstacle.positions[3 * j + 1] + 1 == walls.position[2 * i + 1] && obstacle.positions[3 * j] == walls.position[2 * i]){
                    downWall = true;
                }

                if(obstacle.positions[3 * j + 1] == walls.position[2 * i + 1] + 1 && obstacle.positions[3 * j] == walls.position[2 * i]){
                    upWall = true;
                }

                if(obstacle.positions[3 * j] == walls.position[2 * i] + 1 && obstacle.positions[3 * j + 1] == walls.position[2 * i + 1]){
                    leftWall = true;
                }
            }
        }
        return rightWall || leftWall || upWall || downWall;
    }

    // player - obstacle
    private boolean collisionWithObstacles() {
        player.rightCollisionO = false;
        player.leftCollisionO = false;
        player.upCollisionO = false;
        player.downCollisionO = false;

            for (int i = 0; i < lev.obstaclesPosition.size(); i++) {
                if (player.position[0] == obstacle.positions[3 * i] && player.position[1] + 0.5 == obstacle.positions[3 * i + 1] + 0.5) {
                    player.rightCollisionO = true;
                    if(player.goRight && !obstacle.rightCollision && !rightWall){
                        obstacle.positions[3 * i] += 1;
                    }
                }

                if (player.position[0] + 0.5 == obstacle.positions[3 * i] + 0.5 && player.position[1] == obstacle.positions[3 * i + 1]) {
                    player.downCollisionO = true;
                    if (player.goDown && !obstacle.downCollision && !downWall) {
                        obstacle.positions[3 * i + 1] += 1;
                    }
                }

                if (player.position[0] + 0.5 == obstacle.positions[3 * i] + 0.5 && player.position[1] == obstacle.positions[3 * i + 1]) {
                    player.upCollisionO = true;
                    if (player.goUp && !obstacle.downCollision && !upWall) {
                        obstacle.positions[3 * i + 1] -= 1;
                    }
                }

                if (player.position[0] == obstacle.positions[3 * i] && player.position[1] + 0.5 == obstacle.positions[3 * i + 1] + 0.5) {
                    player.leftCollisionO = true;
                    if (player.goLeft && !obstacle.rightCollision && !leftWall) {
                        obstacle.positions[3 * i] -= 1;
                    }
                }

            }

        return player.rightCollisionO || player.leftCollisionO || player.upCollisionO || player.downCollisionO;
    }


    @Override
    public void run() {
        while (running)
        {
            update();
            repaint();
            if(obstacle.isOnCorrectPlace()){
                System.out.println("WYGRANA");
                break;
            }
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

    public void update(){
//        System.out.println("wzzzzzzz");
        currentTime = System.currentTimeMillis();

        if(currentTime - lastTime > 300) {
            player.tick();
            player.collisionWithWalls();
            collisionWithObstacles();
            obstacle.collisionWithOther();
            collisionObstacleWall();
            lastTime = System.currentTimeMillis();
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        correctPlace.paintComponent(g);
        walls.paintComponent(g);
        player.paintComponent(g);
        obstacle.paintComponent(g);

    }
}
