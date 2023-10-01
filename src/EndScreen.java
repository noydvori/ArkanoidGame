import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author noy dvori 211908256
 * class EndScreen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private boolean isWin;
    private GUI gui;
    private Sleeper sleeper;
    private boolean stop;

    /**
     * constructor of EndScreen.
     *
     * @param keyboardSensor
     * @param score
     * @param isWin
     * @param gui
     */
    public EndScreen(KeyboardSensor keyboardSensor, Counter score, boolean isWin, GUI gui) {
        this.isWin = isWin;
        this.score = score;
        this.keyboardSensor = keyboardSensor;
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        if (isWin) {
            d.drawText(270, 300, "YOU WIN! your score is: " + score.getValue(), 20);
        } else {
            d.drawText(270, 300, "YOU LOSE! your score is: " + score.getValue(), 20);
        }
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
