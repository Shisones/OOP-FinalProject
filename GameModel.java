import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
    private int playerX, playerY;
    private boolean isJumping;
    private boolean onGround;
    private int velocityY;

    private static final int GRAVITY = 1;
    private static final int PLAYER_SIZE = 50;
    private static final int PIPE_WIDTH = 50;
    private static final int PIPE_GAP = 200;
    private static final int PIPE_SPEED = 5;

    private List<Pipe> pipes;
    private Random random;

    public GameModel() {
        playerX = 50;
        playerY = 250;
        isJumping = false;
        onGround = false;
        velocityY = 0;
        pipes = new ArrayList<>();
        random = new Random();
        generatePipes();
    }

    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }

    public List<Pipe> getPipes() { return pipes; }

    public void update() {
        if (isJumping || !onGround) {
            velocityY -= GRAVITY;
            playerY -= velocityY;
        }

        if (playerY >= 550) { // Adjusted for player size
            playerY = 550;
            onGround = true;
            isJumping = false;
            velocityY = 0;
        } else {
            onGround = false;
        }

        checkCollisions();
        updatePipes();
    }

    public void jump() {
        if (onGround) {
            isJumping = true;
            onGround = false;
            velocityY = 15;
        }
    }

    private void generatePipes() {
        for (int i = 0; i < 5; i++) {
            int yPosition = random.nextInt(300);
            boolean isTop = random.nextBoolean();
            pipes.add(new Pipe(800 + i * 200, yPosition, isTop));
        }
    }

    private void updatePipes() {
        List<Pipe> newPipes = new ArrayList<>();
        for (Pipe pipe : pipes) {
            pipe.setX(pipe.getX() - PIPE_SPEED);
            if (pipe.getX() + PIPE_WIDTH > 0) {
                newPipes.add(pipe);
            }
        }
        if (pipes.size() < 5) {
            int yPosition = random.nextInt(300);
            boolean isTop = random.nextBoolean();
            newPipes.add(new Pipe(800, yPosition, isTop));
        }
        pipes = newPipes;
    }

    private void checkCollisions() {
        for (Pipe pipe : pipes) {
            if (pipe.isTop()) {
                if (playerX < pipe.getX() + PIPE_WIDTH && playerX + PLAYER_SIZE > pipe.getX() &&
                        playerY < pipe.getY()) {
                    playerY = pipe.getY() - PLAYER_SIZE;
                    onGround = true;
                    isJumping = false;
                    velocityY = 0;
                }
            } else {
                if (playerX < pipe.getX() + PIPE_WIDTH && playerX + PLAYER_SIZE > pipe.getX() &&
                        playerY + PLAYER_SIZE > pipe.getY() + PIPE_GAP) {
                    playerY = pipe.getY() + PIPE_GAP;
                    onGround = true;
                    isJumping = false;
                    velocityY = 0;
                }
            }
        }
    }

    public static class Pipe {
        private int x, y;
        private boolean isTop;

        public Pipe(int x, int y, boolean isTop) {
            this.x = x;
            this.y = y;
            this.isTop = isTop;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public boolean isTop() {
            return isTop;
        }
    }
}
