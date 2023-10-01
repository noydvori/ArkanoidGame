import biuoop.DrawSurface;

/**
 * @author noy dvori 211908256
 * Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d - the given screen.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
