import java.util.List;

/**
 * @author noy dvori 211908256
 * class Line.
 * A line connects two points - a start point and an end point.
 * the class include functions that calculate the length of the line, intersect with other lines,
 * and equals between lines.
 */
public class Line {
    private Point start;
    private Point end;
    private final double epsilon = Math.pow(10, -6);

    /**
     * constructors of line.
     *
     * @param start - starting point of the line.
     * @param end   - ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructors of line.
     *
     * @param x1 - x value of the starting point.
     * @param y1 - y value of the starting point.
     * @param x2 - x value of the ending point.
     * @param y2 - y value of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * the function returns the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * the function returns the middle point of the line.
     *
     * @return the middle point of the line/
     */
    public Point middle() {
        return new Point((end.getX() + start.getX()) / 2, (end.getY() + start.getY()) / 2);
    }

    /**
     * the function returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * the function returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return end;
    }

    /**
     * the function returns true if the lines intersect, false otherwise.
     *
     * @param other - another line.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }

    /**
     * the function returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other - another line.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point newp;
        double x, y;
        // if this line is vertical:
        if ((incline() == Integer.MAX_VALUE)) {
            // checks if the other line is horizontal
            if ((other.incline() == 0)) {
                y = other.constant();
                x = this.start.getX();
                newp = new Point(x, y);
                if (isOnLine(newp) && other.isOnLine(newp)) {
                    return newp;
                }
            }
            // other cases
            if (other.incline() != Integer.MAX_VALUE) {
                y = (start.getX() * other.incline()) + other.constant();
                x = (y - other.constant()) / other.incline();
                newp = new Point(x, y);
                if (isOnLine(newp) && other.isOnLine(newp)) {
                    return newp;
                }
            }
        }
        // if the other line is vertical:
        if (other.incline() == Integer.MAX_VALUE) {
            // checks if this line is horizontal.
            if (incline() == 0) {
                y = constant();
                x = other.start.getX();
                newp = new Point(x, y);
                if (isOnLine(newp) && other.isOnLine(newp)) {
                    return newp;
                }
            }
            // other cases.
            if (incline() != Integer.MAX_VALUE) {
                y = (other.start.getX() * incline()) + constant();
                x = (y - constant()) / incline();
                newp = new Point(x, y);
                if (isOnLine(newp) && other.isOnLine(newp)) {
                    return newp;
                }
            }
        }
        // if both lines are vertical:
        if ((incline() == Integer.MAX_VALUE) && (other.incline() == Integer.MAX_VALUE)) {
            if ((this.start.equals(other.start)) || this.start.equals(other.end)) {
                return start;
            }
            if (this.end.equals(other.end) || this.end.equals(other.start)) {
                return end;
            }
        }
        // if both lines are horizontal:
        if ((incline() == 0) && (other.incline() == 0)) {
            if ((this.start.equals(other.start)) || this.start.equals(other.end)) {
                return start;
            }
            if (this.end.equals(other.end) || this.end.equals(other.start)) {
                return end;
            }
        }
        if ((other.incline() != incline())) {
            x = ((this.constant() - other.constant()) / (other.incline() - this.incline()));
            y = ((this.incline() * x) + this.constant());
            newp = new Point(x, y);
            if (isOnLine(newp) && other.isOnLine(newp)) {
                return newp;
            }
        }
        return null;
    }

    /**
     * the function return true is the lines are equal, false otherwise.
     *
     * @param other - another line.
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((incline() == other.incline()) && (constant() == other.constant())
                && (length() == other.length()));
    }

    /**
     * the function checks if the point is on the line.
     *
     * @param point - given point.
     * @return true if the given point is on this line, otherwise returns false.
     */
    public boolean isOnLine(Point point) {
        if (((start.distance(point) + end.distance(point)) <= (start.distance(end) + epsilon))
                && ((start.distance(point) + end.distance(point)) >= (start.distance(end) - epsilon))) {
            return true;
        }
        return false;
    }

    /**
     * the function returns the incline of the line.
     *
     * @return the incline of this line.
     */
    public double incline() {
        if (end.getX() == start.getX()) {
            return Integer.MAX_VALUE;
        }
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    /**
     * the funtion returns the intersection of line with y.
     *
     * @return the intersection of line with y.
     */
    public double constant() {
        return (((-1) * incline() * start.getX()) + start.getY());
    }

    /**
     * if this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rectangle - given rectangle.
     * @return null if there's no intersection. otherwise, return the closest intersection point to the
     * start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rectangle) {
        List<Point> list = rectangle.intersectionPoints(new Line(start, end));
        return findClosestIntersectionToStartOfLine(list);
    }

    /**
     * this function return the closest intersection point to the start of the line from the given list of points.
     *
     * @param list - the given points list.
     * @return the closest intersection point to the start of the line.
     */
    public Point findClosestIntersectionToStartOfLine(List<Point> list) {
        // initialize min value.
        double min = length();
        Point closestPoint = null;
        for (Point p : list) {
            if ((p != null) && ((start.distance(p)) <= min)) {
                closestPoint = p;
                min = start.distance(p);
            }
        }
        return closestPoint;
    }


}