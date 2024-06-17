
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePresenter implements ActionListener, KeyListener {
    private GameModel model;
    private GameView view;
    private Timer timer;

    public GamePresenter(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.view.addKeyListener(this);
        this.view.setFocusable(true);
        this.view.requestFocusInWindow();
        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.update();
        view.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            model.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
