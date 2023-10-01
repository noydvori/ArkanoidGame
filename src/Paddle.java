import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author noy dvori 211908256
 * class Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private GUI gui;
    private double width;
    private double speed;

    /**
     * constructor of paddle.
     *
     * @param block - given block.
     */
    public Paddle(Block block) {
        this.block = block;
    }

    /**
     * constructor of paddle.
     *
     * @param block    - given block.
     * @param gui      - given gui.
     * @param speed    - given speed.
     * @param width    - given width.
     * @param keyboard - given keyboard.
     */
    public Paddle(Block block, GUI gui, KeyboardSensor keyboard, double speed, double width) {
        this.block = block;
        this.gui = gui;
        this.keyboard = keyboard;
        this.width = width;
        this.speed = speed;
    }

    /**
     * this function moves the paddle left.
     */

    public void moveLeft() {
        Point upperLeft = block.getCollisionRectangle().getUpperLeft();
        upperLeft.setX(upperLeft.getX() - speed);
        if (upperLeft.getX() < 10) {
            block.getCollisionRectangle().getUpperLeft().setX(10);
        }
        block.getCollisionRectangle().getUpperLeft().setX(upperLeft.getX());
    }

    /**
     * this function moves the paddle right.
     */
    public void moveRight() {
        Point upperLeft = block.getCollisionRectangle().getUpperLeft();
        upperLeft.setX(upperLeft.getX() + speed);
        if (upperLeft.getX() > (790 - getCollisionRectangle().getWidth())) {
            block.getCollisionRectangle().getUpperLeft().setX(790 - this.width);
        }
        block.getCollisionRectangle().getUpperLeft().setX(upperLeft.getX());
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        block.drawOn(d);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double size = (block.getCollisionRectangle().getWidth()) / 5;
        double cur = block.getCollisionRectangle().getUpperLeft().getX();
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        // section 1:
        if (collisionPoint.getX() <= cur + size && collisionPoint.getX() >= cur) {
            return currentVelocity.fromAngleAndSpeed(-60, speed);
        }
        // section 2:
        if (collisionPoint.getX() <= cur + 2 * size && collisionPoint.getX() >= cur + size) {
            return currentVelocity.fromAngleAndSpeed(-30, speed);
        }
        // section 3:
        if (collisionPoint.getX() <= cur + 3 * size && collisionPoint.getX() >= cur + 2 * size) {
            currentVelocity.setDy(-(currentVelocity.getDy()));
            return currentVelocity;
        }
        // section 4:
        if (collisionPoint.getX() <= cur + 4 * size && collisionPoint.getX() >= cur + 3 * size) {
            return currentVelocity.fromAngleAndSpeed(30, speed);
        }
        // section 5:
        if (collisionPoint.getX() <= cur + 5 * size && collisionPoint.getX() >= cur + 4 * size) {
            return currentVelocity.fromAngleAndSpeed(60, speed);
        }
        // default
        return new Velocity(-(currentVelocity.getDx()), -(currentVelocity.getDy()));
    }

    /**
     * this function add this paddle to the game.
     *
     * @param g - the given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
