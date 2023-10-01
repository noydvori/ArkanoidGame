import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author noy dvori 211908256
 * class ScoreIndicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter counter;

    /**
     * constructor of ScoreIndicator.
     *
     * @param counter - the given counter.
     */
    public ScoreIndicator(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(370, 15, "Score: " + counter.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }

    /**
     * this function adds thr sprite to the game.
     *
     * @param g - the given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
