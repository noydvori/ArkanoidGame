import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * @author noy dvori 211908256
 * class SpriteCollection.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * adds the given sprites to the sprites list.
     *
     * @param s - the given sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d - the given DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * this function removes the given sprite from sprites arraylist.
     *
     * @param s - given sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}
