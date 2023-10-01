/**
 * @author noy dvori 211908256
 * class Velocity.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor of velocity.
     *
     * @param dx - movement of the ball on x.
     * @param dy - movement of the ball on y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the function returns dx.
     *
     * @return dx.
     */
    public double getDx() {
        return dx;
    }

    /**
     * the function returns dy.
     *
     * @return dy.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p - given point.
     * @return new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * the program specify the velocity in terms and angle and a speed.
     *
     * @param angle - given angle.
     * @param speed - given speed.
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy;

        dx = (speed * Math.sin((Math.toRadians(angle))));
        dy = -(speed * Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * the function update dx value.
     *
     * @param dx - given dx.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * the function update dy value.
     *
     * @param dy - given dy.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
}
