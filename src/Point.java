/**
 * @author noy dvori 211908256
 * class Ball.
 * A point has an x and a y value, and can measure the distance to other points, and if it is equal to another point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor of point.
     *
     * @param x - x value of the point.
     * @param y - y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the function calculates the distance of this point to the other point.
     *
     * @param other - another point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * the function checks if this point and other point are equal.
     *
     * @param other - another point.
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return !((other == null) || (this.x != other.getX()) || (this.y != other.getY()));
    }

    /**
     * the function returns the x value.
     *
     * @return x value.
     */
    public double getX() {
        return x;
    }

    /**
     * the function returns the y value.
     *
     * @return y value.
     */
    public double getY() {
        return y;
    }

    /**
     * the function update the x value.
     *
     * @param x - the given value.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * the function returns the y value.
     *
     * @param y - the given value.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * the function set new values to this point.
     *
     * @param x - new value for x.
     * @param y - new value for y.
     */
    public void setPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
}