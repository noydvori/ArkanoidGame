import biuoop.DrawSurface;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author noy dvori 211908256
 * class Ball.
 * Ball have size (radius), color, and location (a Point). the class include functuion that draw
 * the ball on given surface.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point frameStart;
    private Point frameEnd;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;

    /**
     * constructor of ball.
     *
     * @param center - given point.
     * @param r      - given radius.
     * @param color  = given color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = new GameEnvironment();
        this.hitListeners = new ArrayList<>();
    }

    /**
     * constructor of ball.
     *
     * @param x     - given x value of the center.
     * @param y     - given y value of the center.
     * @param r     - given radius size.
     * @param color - given color.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * the function returns x value of the center.
     *
     * @return x value of the center.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * the function returns y value of the center.
     *
     * @return y value of the center.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * the function returns the size of this ball.
     *
     * @return the value of the size.
     */
    public int getSize() {
        return r;
    }

    /**
     * the function returns the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        if (this.frameStart == null) {
            setFrameStart(new Point(0, 0));
        }
        if (this.frameEnd == null) {
            setFrameEnd(new Point(surface.getWidth(), surface.getHeight()));
        }
        fix();
        surface.setColor(Color.black);
        surface.drawCircle((int) center.getX(), (int) center.getY(), r + 1);
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }

    /**
     * the function handles end cases.
     */
    public void fix() {
        //if the radius is negative number
        if (r < 0) {
            r = r * (-1);
        }
        // if the radius is 0
        if (r == 0) {
            r = 10;
        }
        // if the radius too big for the frame
        if (r > Math.min(frameEnd.getX(), frameEnd.getY()) / 2) {
            r = (int) (Math.min(frameEnd.getX(), frameEnd.getY()) / 2) - 5;
        }
        // if the ball is out of the left border
        if ((center.getX() - this.r) < frameStart.getX()) {
            if (center.getX() < 0) {
                center.setPoint(frameStart.getX() + (-1 * center.getX() + r), center.getY());
            } else {
                center.setPoint(frameStart.getX() + r, center.getY());
            }
        }
        // if the ball is out of the right border
        if ((center.getX() + this.r) > frameEnd.getX()) {
            center.setPoint(frameEnd.getX() - r, center.getY());
        }
        // if the ball is out of the upper border
        if ((center.getY() - this.r) < frameStart.getY()) {
            if (center.getY() < 0) {
                center.setPoint(center.getX(), frameStart.getY() + (-1 * center.getY() + r));
            } else {
                center.setPoint(center.getX(), frameStart.getY() + r);
            }
        }
        // if the ball is out of the lower border
        if ((center.getY() + this.r) > frameEnd.getY()) {
            center.setPoint(center.getX(), frameEnd.getY() - r);
        }
    }

    /**
     * the function set new velocity to the ball.
     *
     * @param v - given velocity.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * the function set new velocity to the ball.
     *
     * @param dx - dx of velocity.
     * @param dy - dy of velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * this function returns the velocity of this ball.
     *
     * @return the velocity of this ball.
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * this function update the frameStart point.
     *
     * @param newPoint - given new point.
     */
    public void setFrameStart(Point newPoint) {
        this.frameStart = newPoint;
    }

    /**
     * this function update the frameEnd point.
     *
     * @param newPoint - given new point.
     */
    public void setFrameEnd(Point newPoint) {
        this.frameEnd = newPoint;
    }

    /**
     * the function update the size of this ball.
     *
     * @param newsize - the new value of the size.
     */
    public void setSize(int newsize) {
        this.r = newsize;
    }

    /**
     * the function returns the frameEnd of the ball.
     *
     * @return frameEnd point of the ball.
     */
    public Point getFrameStart() {
        return frameEnd;
    }

    /**
     * the function returns the frameStart of the ball.
     *
     * @return frameStart point of the ball.
     */
    public Point getFrameEnd() {
        return frameStart;
    }

    /**
     * the function returns the center point of this ball.
     *
     * @return center point.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * the function update the gameEnvironment.
     *
     * @param gameEnvironment - the new given environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * this function moves the ball according the velocity.
     */
    public void moveOneStep() {
        Line line = new Line(center, this.getVelocity().applyToPoint(center));
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(line);
        //CollisionInfo collisionInfo2 = gameEnvironment.isPointInRectangle(center);
        if ((collisionInfo == null)) {
            // move the ball to the end of the trajectory.
            this.center = getVelocity().applyToPoint(center);
        } else {
            // move the ball to "almost" the hit point, but just slightly before it.
            if (v.getDx() > 0 && v.getDy() > 0) {
                this.center.setPoint(collisionInfo.collisionPoint().getX() - 1,
                        collisionInfo.collisionPoint().getY() - 1);
            } else if (v.getDx() < 0 && v.getDy() < 0) {
                this.center.setPoint(collisionInfo.collisionPoint().getX() + 1,
                        collisionInfo.collisionPoint().getY() + 1);
            } else if (v.getDx() > 0 && v.getDy() < 0) {
                this.center.setPoint(collisionInfo.collisionPoint().getX() - 1,
                        collisionInfo.collisionPoint().getY() + 1);
            } else if (v.getDx() < 0 && v.getDy() > 0) {
                this.center.setPoint(collisionInfo.collisionPoint().getX() + 1,
                        collisionInfo.collisionPoint().getY() - 1);
            }
            // update the velocity
            this.v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.v);
        }
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * this function adds this ball to game g.
     *
     * @param g - given game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * this function removes this ball from game g.
     *
     * @param game - given game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
