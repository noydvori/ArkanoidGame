import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * @author noy dvori 211908256
 * class Block.
 * each block include rectancle and color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle collisionRectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor of block.
     *
     * @param r - given block.
     */
    public Block(Rectangle r) {
        this.collisionRectangle = r;
    }

    /**
     * constructor of block.
     *
     * @param r     - given rectangle.
     * @param color - given color.
     */
    public Block(Rectangle r, java.awt.Color color) {
        this.collisionRectangle = r;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * @param upperLeft - the upper left point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Block(Point upperLeft, double width, double height) {
        this.collisionRectangle = new Rectangle(upperLeft, width, height);
        hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        // if the collision point is on the upperLine:
        if ((collisionRectangle.getUpperLine().isOnLine(collisionPoint))) {
            currentVelocity.setDy((-1) * (currentVelocity.getDy()));
            // checks if the points is on the corners of the block.
            if (collisionRectangle.getLeftLine().isOnLine(collisionPoint)
                    || collisionRectangle.getRightLine().isOnLine(collisionPoint)) {
                currentVelocity.setDx((-1) * (currentVelocity.getDx()));
            }
            return currentVelocity;
        }
        // if the collision point is on the lowerLine:
        if ((collisionRectangle.getLowerLine().isOnLine(collisionPoint))) {
            currentVelocity.setDy((-1) * (currentVelocity.getDy()));
            // checks if the points is on the corners of the block.
            if (collisionRectangle.getLeftLine().isOnLine(collisionPoint)
                    || collisionRectangle.getRightLine().isOnLine(collisionPoint)) {
                currentVelocity.setDx((-1) * (currentVelocity.getDx()));
            }
            return currentVelocity;
        }
        // if the collision point is on the leftLine:
        if ((collisionRectangle.getLeftLine().isOnLine(collisionPoint))) {
            currentVelocity.setDx((-1) * (currentVelocity.getDx()));
            // checks if the points is on the corners of the block.
            if (collisionRectangle.getUpperLine().isOnLine(collisionPoint)
                    || collisionRectangle.getLowerLine().isOnLine(collisionPoint)) {
                currentVelocity.setDy((-1) * (currentVelocity.getDy()));
            }
            return currentVelocity;
        }
        // if the collision point is on the rightLine:
        if ((collisionRectangle.getRightLine().isOnLine(collisionPoint))) {
            currentVelocity.setDx((-1) * (currentVelocity.getDx()));
            // checks if the points is on the corners of the block.
            if (collisionRectangle.getUpperLine().isOnLine(collisionPoint)
                    || collisionRectangle.getLowerLine().isOnLine(collisionPoint)) {
                currentVelocity.setDy((-1) * (currentVelocity.getDy()));
            }
            return currentVelocity;
        }
        // nothing changed.
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) collisionRectangle.getUpperLeft().getX(),
                (int) collisionRectangle.getUpperLeft().getY(),
                (int) collisionRectangle.getWidth(), (int) collisionRectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) collisionRectangle.getUpperLeft().getX(),
                (int) collisionRectangle.getUpperLeft().getY(),
                (int) collisionRectangle.getWidth(), (int) collisionRectangle.getHeight());

    }

    @Override
    public void timePassed() {
    }

    /**
     * this function adds this block to game g.
     *
     * @param g - given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * the function removes this block from game.
     *
     * @param game - given game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * this function will be called whenever a hit() occurs, and will notify all of the registered HitListener objects
     * by calling their hitEvent method.
     *
     * @param hitter - given ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

}