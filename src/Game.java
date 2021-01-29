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

    public enum GameState {running, pause}

    public GameState gameState;

    public boolean escapePressed = false;
    public boolean gamePaused = false;
    public boolean nextLevelMenuShown = false;

    public double score;
    private int numberOfMoves = 0;
    private int numberOfLevels;
    private long allTime;
    private long startTime;
    private long stopTime;
    private int numberOfBackMoves;

    String nickname;

    long currentTime;
    long lastTime = 0;

    private boolean running = false;
    private MainWindow mainWindow;
    private PauseMenu pauseMenu;

    public Game(int levelNr, String nick) throws IOException {
        nickname = nick;
        levelNumber = levelNr;
        lev = Reader.makeLevel(levelNumber);
        walls = new Walls[lev.wallsPosition.size()];
        for (int i = 0; i<lev.wallsPosition.size(); i++)
        {
            walls[i] = new Walls(levelNumber, i);
        }
        player = new Player(levelNumber);

        numberOfLevels = Reader.getNumberOfLevels();
        numberOfBackMoves = 0;

// zrobione tak ze jest tyle samo dobrych miejsc co przeszkod
// jesli chcielibysmy zrobic wiecej dobrych miejsc niz przeszkod to trzeba zmienic, tak samo w paintComponent, tak samo w resizeAll()

        obstacle = new Obstacle[lev.numberOfObstacles];
        correctPlace = new CorrectPlaces[lev.numberOfObstacles];
        for (int i = 0; i<lev.numberOfObstacles; i++)
        {
            obstacle[i] = new Obstacle(levelNumber, i);
            correctPlace[i] = new CorrectPlaces(levelNumber, i);
        }

        keys = new Keys(player, this);
        mainWindow = new MainWindow(this);
        gameState = GameState.running;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
//        gaming = true;
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
                        numberOfMoves += 1;
                    }
                }

                if (player.position[0] + 0.5 == obstacle[i].xpos + 0.5 && player.position[1] + 1 == obstacle[i].ypos) {
                    player.downCollision = true;
                    if (player.goDown && !obstacle[i].downCollision && !obstacle[i].downWall) {
                        obstacle[i].ypos += 1;
                        player.position[1] += 1;
                        numberOfMoves += 1;
                    }
                }

                if (player.position[0] + 0.5 == obstacle[i].xpos + 0.5 && player.position[1] == obstacle[i].ypos + 1) {
                    player.upCollision = true;
                    if (player.goUp && !obstacle[i].upCollision && !obstacle[i].upWall) {
                        obstacle[i].ypos -= 1;
                        player.position[1] -= 1;
                        numberOfMoves += 1;
                    }
                }

                if (player.position[0] == obstacle[i].xpos + 1 && player.position[1] + 0.5 == obstacle[i].ypos + 0.5) {
                    player.leftCollision = true;
                    if (player.goLeft && !obstacle[i].leftCollision && !obstacle[i].leftWall) {
                        obstacle[i].xpos -= 1;
                        player.position[0] -= 1;
                        numberOfMoves += 1;
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

    public void playerMove() {
        for(int i = 0; i<lev.numberOfObstacles; i++) {
            if (player.goRight && !player.rightWall && !player.rightCollision && !obstacle[i].rightWall) {
                player.position[0] += 1;
                numberOfMoves += 1;
                break;
            } else if (player.goLeft && !player.leftWall && !player.leftCollision && !obstacle[i].leftWall) {
                player.position[0] -= 1;
                numberOfMoves += 1;
                break;
            } else if (player.goUp && !player.upWall && !player.upCollision && !obstacle[i].upWall) {
                player.position[1] -= 1;
                numberOfMoves += 1;
                break;
            } else if (player.goDown && !player.downWall && !player.downCollision && !obstacle[i].downWall) {
                player.position[1] += 1;
                numberOfMoves += 1;
                break;
            }
        }
    }

    public void nextLevel() throws IOException {
        numberOfMoves = 0;
        levelNumber += 1;
        nextLevelMenuShown = true;
        mainWindow.makeLevel(levelNumber);
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        while (running)
        {

//                gamePaused = pause();
                try {
                    update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                repaint();

        }
        stop();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
//            gaming = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateScore(){
        int wsp_cofniec;
        switch (numberOfBackMoves) {
            case 2:
            case 3:
                wsp_cofniec = 1000;
                break;
            case 4:
            case 5:
                wsp_cofniec = 5000;
                break;
            default:
                wsp_cofniec = 7500;
                break;
        }
        allTime = stopTime - startTime;
        score = (1/((double)numberOfMoves+1)) * 80000 * (levelNumber / (double)numberOfLevels) + (1 +(1/(double)(allTime/1000)) * 50000 + (double) lev.numberOfObstacles * 10000 - (double) (numberOfBackMoves - 1) * wsp_cofniec);
    }

//    public void isPaused(){
//        if (escapePressed && !gamePaused) {
//            gameState = GameState.pause;
//        }
//        else if (escapePressed && gamePaused){
//            gameState = GameState.running;
//        }
//    }
//
//    public boolean pause(){
//        boolean isPaused = false;
//        if (gameState == GameState.running){
//            mainWindow.setVisible(false);
//            pauseMenu = new PauseMenu(this);
//            System.out.println("tworze nowy obiekt");
////            gaming = false;
//            isPaused = true;
//        }
//        if (gameState == GameState.pause){
//            pauseMenu.setVisible(false);
//            mainWindow.setVisible(true);
//            System.out.println("niszcze obiekt");
////            gaming = true;
//            isPaused = false;
//        }
//
//        return isPaused;
//    }

    public void update() throws IOException{
//        switch (gameState){
//            case running:
                currentTime = System.currentTimeMillis();

                if(currentTime - lastTime > 300) {
                    player.collisionWithWalls();
                    collisionWithObstacles();
                    playerMove();
                    collisionWithOtherObstacle();
                    collisionObstacleWall();
                    isOnCorrectPlace();
                    updateScore();
                    stopTime = System.currentTimeMillis();
//                    gamePaused = pause();
//                    isPaused();
                    if(isVictory() && !nextLevelMenuShown)
                    {
                        nextLevel();
                    }
                    lastTime = System.currentTimeMillis();
                }
//                break;
//            case pause:
////                pause();
//                break;
//            default:
//                break;
//        }


    }

    public void resizeAll() {
        player.height = (int) ((double) player.prefHeight * ((double) mainWindow.currentHeight / (double) mainWindow.prefHeight));
        player.width = (int) ((double) player.prefWidth * ((double) mainWindow.currentWidth / (double) mainWindow.prefWidth));

        for (int i = 0; i < lev.numberOfObstacles; i++){
            obstacle[i].height = (int) ((double) obstacle[i].prefHeight * ((double) mainWindow.currentHeight / (double) mainWindow.prefHeight));
            obstacle[i].width = (int) ((double) obstacle[i].prefWidth * ((double) mainWindow.currentWidth / (double) mainWindow.prefWidth));
            correctPlace[i].height = (int) ((double) correctPlace[i].prefHeight * ((double) mainWindow.currentHeight / (double) mainWindow.prefHeight));
            correctPlace[i].width = (int) ((double) correctPlace[i].prefWidth * ((double) mainWindow.currentWidth / (double) mainWindow.prefWidth));
        }

        for (int i = 0; i < lev.wallsPosition.size(); i++){
            walls[i].height = (int) ((double) walls[i].prefHeight * ((double) mainWindow.currentHeight / (double) mainWindow.prefHeight));
            walls[i].width = (int) ((double) walls[i].prefWidth * ((double) mainWindow.currentWidth / (double) mainWindow.prefWidth));
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        for(int i = 0; i<lev.numberOfObstacles; i++)
        {
            correctPlace[i].paintComponent(g);
        }

        for(int i = 0; i<lev.numberOfObstacles; i++)
        {
            obstacle[i].paintComponent(g);
        }
        player.paintComponent(g);

        for(int i = 0; i<lev.wallsPosition.size(); i++)
        {
            walls[i].paintComponent(g);
        }
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.drawString("Wynik: " + (int) score, (int) (obstacle[0].width*lev.numberOfObstacles + obstacle[0].width), (int) (obstacle[0].height));

    }
}
