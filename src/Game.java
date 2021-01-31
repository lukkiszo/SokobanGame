import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasa odpowiadajaca za tworzenie odpowiedniego poziomu
 */
public class Game extends JComponent implements Runnable {

    public int levelNumber;
    private Player player;
    private ArrayList<Obstacle> obstacle;
    private Walls[] walls;
    private CorrectPlaces[] correctPlace;
    private Teleport[] teleports;
    private Level lev;

    Thread thread;

    public enum GameState {running, pause}

    public GameState gameState;

    public boolean nextLevelMenuShown = false;

    public double score;
    private int numberOfMoves = 0;
    private int numberOfLevels;
    private long allTime;
    private long startTime;
    private long stopTime;
    private int numberOfBackMoves;

    private ArrayList<Pair<Integer, Integer>> playerMoves;
    private ArrayList<Triplet<Integer, Integer, Integer>> obstacleMoves;

    String nickname;

    long currentTime;
    long lastTime = 0;

    private int numberOfDeletes;

    private boolean running = false;
    private MainWindow mainWindow;
    private PauseMenu pauseMenu;

    /**
     * Konstruktor
     * @param levelNr numer tworzonego poziomu
     * @param nick nazwa gracza
     * @throws IOException
     */
    public Game(int levelNr, String nick) throws IOException {
        nickname = nick;
        levelNumber = levelNr;
        lev = Reader.makeLevel(levelNumber);
        gameState = GameState.running;
        pauseMenu = new PauseMenu(this, levelNumber, nickname);
        walls = new Walls[lev.wallsPosition.size()];
        for (int i = 0; i<lev.wallsPosition.size(); i++)
        {
            walls[i] = new Walls(levelNumber, i);
        }
        player = new Player(levelNumber);
        numberOfLevels = Reader.getNumberOfLevels();
        numberOfBackMoves = 0;
        numberOfDeletes = 0;

        playerMoves = new ArrayList<>(10);
        obstacleMoves = new ArrayList<>(10);
        for (int i = 0; i<10; i++)
        {
            playerMoves.add(i, new Pair<>(0,0));
            obstacleMoves.add(i, new Triplet<>(0,0, 0));
        }

        obstacle = new ArrayList<>();
        correctPlace = new CorrectPlaces[lev.numberOfObstacles];
        for (int i = 0; i<lev.numberOfObstacles; i++)
        {
            obstacle.add(i, new Obstacle(levelNumber, i));
        }
        for (int i = 0; i<lev.numberOfObstacles; i++)
        {
            correctPlace[i] = new CorrectPlaces(levelNumber, i);
        }
        teleports = new Teleport[lev.teleportsPosition.size()];
        for (int i = 0; i<lev.teleportsPosition.size(); i++)
        {
            teleports[i] = new Teleport(levelNumber, i);
        }
        mainWindow = new MainWindow(this);
        mainWindow.add(pauseMenu);
    }

    /**
     * Metoda startujaca watek
     */
    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * Metoda obslugujaca kolizje przeszkod ze scianami
     */
    private void collisionObstacleWall(){
        for(int i = 0; i<obstacle.size(); i++)
        {
            obstacle.get(i).rightWall = false;
            obstacle.get(i).leftWall = false;
            obstacle.get(i).upWall = false;
            obstacle.get(i).downWall = false;
        }

        for (int i = 0; i<lev.wallsPosition.size(); i++) {
            for (int j = 0; j<obstacle.size(); j++){
                    if (Math.abs(obstacle.get(j).xpos + 1 - walls[i].xpos) < 0.01 && Math.abs(obstacle.get(j).ypos - walls[i].ypos) < 0.01) {
                        obstacle.get(j).rightWall = true;
                    }

                    if (Math.abs(obstacle.get(j).ypos + 1 - walls[i].ypos) < 0.01 && Math.abs(obstacle.get(j).xpos - walls[i].xpos) < 0.01) {
                        obstacle.get(j).downWall = true;
                    }


                    if (Math.abs(obstacle.get(j).ypos - 1 - walls[i].ypos) < 0.01 && Math.abs(obstacle.get(j).xpos - walls[i].xpos) < 0.01) {
                        obstacle.get(j).upWall = true;
                    }

                    if (Math.abs(obstacle.get(j).xpos - 1 - walls[i].xpos) < 0.01 && Math.abs(obstacle.get(j).ypos - walls[i].ypos) < 0.01) {
                        obstacle.get(j).leftWall = true;
                    }
            }
        }
    }

    /**
     * Metoda zwracajaca obiekt klasy Player
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Metoda obslugujaca kolizje gracza z przeszkodami
     */
    private void collisionWithObstacles() throws InterruptedException {
        player.rightCollision = false;
        player.leftCollision = false;
        player.upCollision = false;
        player.downCollision = false;

        for (int i = 0; i < obstacle.size(); i++) {
            if (Math.abs(player.position[0] + 1 - obstacle.get(i).xpos) < 0.01 && Math.abs(player.position[1] - obstacle.get(i).ypos) < 0.01) {
                if(player.wantToDeleteOnRight && numberOfDeletes < 2) {
                    obstacle.remove(i);
                    numberOfDeletes += 1;
                }
                player.rightCollision = true;
                if (player.goRight && !obstacle.get(i).rightCollision && !obstacle.get(i).rightWall) {
                    move(0, 1, true, obstacle.get(i));
                    playerMoves.add(0, new Pair<>(1, 0));
                    obstacleMoves.add(0, new Triplet<>(1, 0, i));
                }
            }

            if (Math.abs(player.position[0] - obstacle.get(i).xpos) < 0.01 && Math.abs(player.position[1]  + 1 - obstacle.get(i).ypos) < 0.01) {
                if(player.wantToDeleteOnDown && numberOfDeletes < 2) {
                    obstacle.remove(i);
                    numberOfDeletes += 1;
                }
                player.downCollision = true;
                if (player.goDown && !obstacle.get(i).downCollision && !obstacle.get(i).downWall) {
                    move(1, 1, true, obstacle.get(i));
                    playerMoves.add(0, new Pair<>(0, 1));
                    obstacleMoves.add(0, new Triplet<>(0, 1, i));
                }
            }

            if (Math.abs(player.position[0] - obstacle.get(i).xpos) < 0.01 && Math.abs(player.position[1]  - 1 - obstacle.get(i).ypos) < 0.01) {
                if(player.wantToDeleteOnUp && numberOfDeletes < 2) {
                    obstacle.remove(i);
                    numberOfDeletes += 1;
                }
                player.upCollision = true;
                if (player.goUp && !obstacle.get(i).upCollision && !obstacle.get(i).upWall) {
                    move(1, -1, true, obstacle.get(i));
                    playerMoves.add(0, new Pair<>(0, -1));
                    obstacleMoves.add(0, new Triplet<>(0, -1, i));
                }
            }

            if (Math.abs(player.position[0] - obstacle.get(i).xpos - 1) < 0.01 && Math.abs(player.position[1] - obstacle.get(i).ypos) < 0.01) {
                if(player.wantToDeleteOnLeft && numberOfDeletes < 2) {
                    obstacle.remove(i);
                    numberOfDeletes += 1;
                }
                player.leftCollision = true;
                if (player.goLeft && !obstacle.get(i).leftCollision && !obstacle.get(i).leftWall) {
                    move(0, -1,true, obstacle.get(i));
                    playerMoves.add(0, new Pair<>(-1, 0));
                    obstacleMoves.add(0, new Triplet<>(-1, 0, i));
                }
            }
        }
    }

    /**
     * Metoda obslugujaca kolizje przeszkod miedzy soba
     */
    public void collisionWithOtherObstacle(){

        for(int i = 0; i<obstacle.size(); i++){
            obstacle.get(i).rightCollision = false;
            obstacle.get(i).downCollision = false;
            obstacle.get(i).leftCollision = false;
            obstacle.get(i).upCollision = false;
            for (int j = 0; j<obstacle.size(); j++) {
                if (Math.abs(obstacle.get(i).xpos + 1 - obstacle.get(j).xpos) < 0.01 && Math.abs(obstacle.get(i).ypos - obstacle.get(j).ypos) < 0.01) {
                    obstacle.get(i).rightCollision = true;
                    break;
                }

                if (Math.abs(obstacle.get(i).xpos - obstacle.get(j).xpos) < 0.01 && Math.abs(obstacle.get(i).ypos + 1 - obstacle.get(j).ypos) < 0.01) {
                    obstacle.get(i).downCollision = true;
                    break;
                }

                if (Math.abs(obstacle.get(i).xpos - 1 - obstacle.get(j).xpos) < 0.01 && Math.abs(obstacle.get(i).ypos - obstacle.get(j).ypos) < 0.01) {
                    obstacle.get(i).leftCollision = true;
                    break;
                }

                if (Math.abs(obstacle.get(i).xpos - obstacle.get(j).xpos) < 0.01 && Math.abs(obstacle.get(i).ypos - 1 - obstacle.get(j).ypos) < 0.01) {
                    obstacle.get(i).upCollision = true;
                    break;
                }
            }
        }
    }

    /**
     * Metoda sprawdzajaca czy przeszkody sa na ustalonych miejscach do zakonczenia gry
     * @return liczba przeszkod ustawionych poprawnie
     */
    public int isOnCorrectPlace()
    {
        int k = 0;
        for(int i = 0; i<obstacle.size(); i++)
        {
            obstacle.get(i).isOnCorrectPlace = false;
            for(int j = 0; j<correctPlace.length; j++)
            {
                if(Math.abs(obstacle.get(i).xpos - correctPlace[j].xpos) < 0.01 && Math.abs(obstacle.get(i).ypos - correctPlace[j].ypos) < 0.01)
                {
                    obstacle.get(i).isOnCorrectPlace = true;
                    k++;
                }
            }
        }
        return k;
    }

    /**
     * Metoda sprawdzajaca czy gra zostala ukonczona
     * @return
     */
    public boolean isVictory()
    {
        boolean victory = false;
        if(isOnCorrectPlace() == obstacle.size())
        {
            victory = true;
        }
        return victory;
    }

    /**
     * Metoda obslugujaca ruch gracza i przeszkod
     * @param direction kierunek x lub y
     * @param value wartosc +- 1 okreslajaca zwrot ruchu
     * @param isObstacle czy gracz dotyka przeszkody
     * @param obstacle obiekt dotykanej przez gracza przeszkody
     */
    public void move(int direction, int value, boolean isObstacle, Obstacle obstacle) throws InterruptedException {
        playerMoves.remove(9);
        obstacleMoves.remove(9);
        numberOfMoves += 1;

            if(direction == 0){
                if(value == 1){
                    for(int i = 0; i<200; i++)
                    {
                        synchronized (playerMoves){
                            playerMoves.wait(1);
                            player.position[0] += 0.005;
                            if(isObstacle) obstacle.xpos += 0.005;
                            repaint();
                        }
                    }
                }
                else if (value == -1) {
                    for(int i = 0; i<200; i++)
                    {
                        synchronized (playerMoves){
                            playerMoves.wait(1);
                            player.position[0] -= 0.005;
                            if(isObstacle) obstacle.xpos -= 0.005;
                            repaint();
                        }
                    }
                }
            }
            else if(direction == 1)
            {
                if(value == 1){
                    for(int i = 0; i<200; i++)
                    {
                        synchronized (playerMoves){
                            playerMoves.wait(1);
                            player.position[1] += 0.005;
                            if(isObstacle) obstacle.ypos += 0.005;
                            repaint();
                        }
                    }
                }
                else if (value == -1) {
                    for(int i = 0; i<200; i++)
                    {
                        synchronized (playerMoves){
                            playerMoves.wait(1);
                            player.position[1] -= 0.005;
                            if(isObstacle) obstacle.ypos -= 0.005;
                            repaint();
                        }
                    }
                }
            }
    }

    /**
     * Metoda obslugujaca ruch gracza
     */
    public void playerMove() throws InterruptedException {
        for(int i = 0; i<obstacle.size(); i++) {
            if (player.goRight && !player.rightWall && !player.rightCollision && !obstacle.get(i).rightWall) {
                move(0,1, false, obstacle.get(0));
                playerMoves.add(0, new Pair<>(1, 0));
                obstacleMoves.add(0, new Triplet<>(0, 0, 0));
                break;
            } else if (player.goLeft && !player.leftWall && !player.leftCollision && !obstacle.get(i).leftWall) {
                move(0,-1, false, obstacle.get(0));
                playerMoves.add(0, new Pair<>(-1, 0));
                obstacleMoves.add(0, new Triplet<>(0, 0, 0));
                break;
            } else if (player.goUp && !player.upWall && !player.upCollision && !obstacle.get(i).upWall) {
                move(1,-1, false, obstacle.get(0));
                playerMoves.add(0, new Pair<>(0, -1));
                obstacleMoves.add(0, new Triplet<>(0, 0, 0));
                break;
            } else if (player.goDown && !player.downWall && !player.downCollision && !obstacle.get(i).downWall) {
                move(1,1, false, obstacle.get(0));
                playerMoves.add(0, new Pair<>(0, 1));
                obstacleMoves.add(0, new Triplet<>(0, 0, 0));
                break;
            }
        }
    }

    /**
     * Metoda sprawdzajaca czy przeszkoda nie stoi na teleporcie
     */
    public void isOnTeleport()
    {
        teleports[1].isBlocked = false;
        for(int i = 0; i<obstacle.size(); i++) {
            if (Math.abs(obstacle.get(i).xpos - teleports[1].xpos) < 0.01 && Math.abs(obstacle.get(i).ypos - teleports[1].ypos) < 0.01) {
                teleports[1].isBlocked = true;
                break;
            }
        }

        if(!teleports[1].isBlocked && Math.abs(player.position[0] - teleports[0].xpos) < 0.01 && Math.abs(player.position[1] - teleports[0].ypos) < 0.01)
        {
            player.position[0] = teleports[1].xpos;
            player.position[1] = teleports[1].ypos;
        }
    }

    /**
     * Metoda umozliwiajaca cofanie ruchow gracza
     */
    public void backMoves() {
        if(playerMoves.size() > 0)
        {
            player.position[0] -= playerMoves.get(0).a;
            player.position[1] -= playerMoves.get(0).b;
            playerMoves.remove(0);
            playerMoves.add(9, new Pair<>(0,0));
            obstacle.get(obstacleMoves.get(0).c).xpos -= obstacleMoves.get(0).a;
            obstacle.get(obstacleMoves.get(0).c).ypos -= obstacleMoves.get(0).b;
            obstacleMoves.remove(0);
            obstacleMoves.add(9, new Triplet<>(0,0,0));
            numberOfBackMoves += 1;
        }
    }

    /**
     * Metoda tworzaca nastepny poziom
     * @throws IOException
     */
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
            try {
                update();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            repaint();

        }
        stop();
    }

    /**
     * Metoda zatrzymujaca watek
     */
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda aktualizujaca wynik
     */
    private void updateScore(){
        int wsp_cofniec;
        switch (numberOfBackMoves) {
            case 2:
            case 3:
                wsp_cofniec = 5000;
                break;
            case 4:
            case 5:
                wsp_cofniec = 15000;
                break;
            default:
                wsp_cofniec = 25000;
                break;
        }
        allTime = stopTime - startTime;
        score = (1/((double)numberOfMoves+1)) * 80000 * (levelNumber / (double)numberOfLevels) + (1 +(1/(double)(allTime/100))) * 50000 + (double) lev.numberOfObstacles * 10000 - (double) ((numberOfBackMoves - 1) * wsp_cofniec) - 15000 * numberOfDeletes;
        if(score < 0) score = 0;
    }

    /**
     * Metoda zmieniajaca status menu pauzy
     */
    public void togglePause() {
        gameState = gameState == GameState.running ? GameState.pause : GameState.running;
        if (gameState == GameState.pause){
            this.setVisible(false);
            pauseMenu.setVisible(true);
            pauseMenu.setFocusable(true);
        }
        else {
            pauseMenu.setFocusable(false);
            mainWindow.setFocusable(true);
            pauseMenu.setVisible(false);
            this.setVisible(true);
        }
    }

    /**
     * Glowna metoda, aktualizujaca stan gry
     * @throws IOException
     */
    public void update() throws IOException, InterruptedException {
        currentTime = System.currentTimeMillis();

        if (currentTime - lastTime > 100) {
            if(gameState == GameState.running) {
                player.collisionWithWalls();
                collisionWithObstacles();
                playerMove();
                collisionWithOtherObstacle();
                collisionObstacleWall();
                isOnCorrectPlace();
                isOnTeleport();
                updateScore();

                stopTime = System.currentTimeMillis();
                if (isVictory() && !nextLevelMenuShown) {
                    nextLevel();
                }
                lastTime = System.currentTimeMillis();
            }
        }
    }

    /**
     * Metoda skalujaca wszystkie elementy okna
     */
    public void resizeAll() {
        setSize(mainWindow.currentWidth, mainWindow.currentHeight);
        player.height = (int) ((double) player.prefHeight * ((double) mainWindow.currentHeight / (double) mainWindow.prefHeight));
        player.width = (int) ((double) player.prefWidth * ((double) mainWindow.currentWidth / (double) mainWindow.prefWidth));

        for (int i = 0; i < obstacle.size(); i++){
            obstacle.get(i).height = (int) ((double) obstacle.get(i).prefHeight * ((double) mainWindow.currentHeight / (double) mainWindow.prefHeight));
            obstacle.get(i).width = (int) ((double) obstacle.get(i).prefWidth * ((double) mainWindow.currentWidth / (double) mainWindow.prefWidth));
        }

        for (int i = 0; i < lev.correctPlacesPosition.size(); i++){
            correctPlace[i].height = (int) ((double) correctPlace[i].prefHeight * ((double) mainWindow.currentHeight / (double) mainWindow.prefHeight));
            correctPlace[i].width = (int) ((double) correctPlace[i].prefWidth * ((double) mainWindow.currentWidth / (double) mainWindow.prefWidth));
        }

        for (int i = 0; i < lev.teleportsPosition.size(); i++){
            teleports[i].height = (int) ((double) teleports[i].prefHeight * ((double) mainWindow.currentHeight / (double) mainWindow.prefHeight));
            teleports[i].width = (int) ((double) teleports[i].prefWidth * ((double) mainWindow.currentWidth / (double) mainWindow.prefWidth));
        }

        for (int i = 0; i < lev.wallsPosition.size(); i++){
            walls[i].height = (int) ((double) walls[i].prefHeight * ((double) mainWindow.currentHeight / (double) mainWindow.prefHeight));
            walls[i].width = (int) ((double) walls[i].prefWidth * ((double) mainWindow.currentWidth / (double) mainWindow.prefWidth));
        }
    }

    /**
     * Metoda rysujaca wszystkie elementy na oknie
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(0xDFF6E9A2, true));
        g.fillRect(0, 0, mainWindow.currentWidth, mainWindow.currentHeight);

        for(int i = 0; i < lev.correctPlacesPosition.size(); i++)
        {
            correctPlace[i].paintComponent(g);
        }

        for(int i = 0; i<lev.teleportsPosition.size(); i++)
        {
            teleports[i].paintComponent(g);
        }

        for(int i = 0; i < obstacle.size(); i++)
        {
            obstacle.get(i).paintComponent(g);
        }
        player.paintComponent(g);

        for(int i = 0; i<lev.wallsPosition.size(); i++)
        {
            walls[i].paintComponent(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, (int) (obstacle.get(0).height / 2)));
        g.drawString("Wynik: " + (int) score, mainWindow.currentWidth / 3, (int) (obstacle.get(0).height / 2));

    }
}
