import java.util.ArrayList;
import java.util.List;

/**
 * @author noy dvori 211908256
 * class Rectangle.
 * Each rectangle has upper-left point, width and height.
 * this class include function that return list of intersections points with specific line.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * the function creates a new rectangle with location and width/height.
     *
     * @param upperLeft - the upper left point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * the function returns a (possibly empty) List of intersection points with the specified line.
     *
     * @param line - given line.
     * @return - a list of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();
        // intersection with the left section of the rectangle
        if (line.isIntersecting(getLeftLine())) {
            list.add(line.intersectionWith(getLeftLine()));
        }
        // intersection with the upper section of the rectangle
        if (line.isIntersecting(getUpperLine())) {
            list.add(line.intersectionWith(getUpperLine()));
        }
        // intersection with the lower section of the rectangle
        if (line.isIntersecting(getLowerLine())) {
            list.add(line.intersectionWith(getLowerLine()));
        }
        // intersection with the right section of the rectangle
        if (line.isIntersecting(getRightLine())) {
            list.add(line.intersectionWith(getRightLine()));
        }
        return list;
    }

    /**
     * the function returns the width of the rectangle.
     *
     * @return - the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * the function returns the height of the rectangle.
     *
     * @return - the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * the function returns the upper-left point of the rectangle.
     *
     * @return - the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * the function returns the lower-right point of the rectangle.
     *
     * @return - the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * the function returns the upper-line of the rectangle.
     *
     * @return the upper-line of the rectangle.
     */
    public Line getUpperLine() {
        return new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
    }

    /**
     * the function returns the left-line of the rectangle.
     *
     * @return the left-line of the rectangle.
     */
    public Line getLeftLine() {
        return new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
    }

    /**
     * the function returns the right-line of the rectangle.
     *
     * @return the right-line of the rectangle.
     */
    public Line getRightLine() {
        return new Line(getLowerRight(), new Point(getLowerRight().getX(), getLowerRight().getY() - height));
    }

    /**
     * the function returns the lower-line of the rectangle.
     *
     * @return the lower-line of the rectangle.
     */
    public Line getLowerLine() {
        return new Line(getLowerRight(), new Point(getLowerRight().getX() - width, getLowerRight().getY()));
    }


}