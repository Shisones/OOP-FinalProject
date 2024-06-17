import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private GameModel model;
    private static final int PIPE_WIDTH = 50;
    private static final int PIPE_GAP = 200;

    public GameView(GameModel model) {
        this.model = model;
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(model.getPlayerX(), model.getPlayerY(), 50, 50);

        for (GameModel.Pipe pipe : model.getPipes()) {
            if (pipe.isTop()) {
                g.fillRect(pipe.getX(), 0, PIPE_WIDTH, pipe.getY());
            } else {
                g.fillRect(pipe.getX(), pipe.getY() + PIPE_GAP, PIPE_WIDTH, 600 - (pipe.getY() + PIPE_GAP));
            }
        }
    }
}
