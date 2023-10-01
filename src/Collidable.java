/**
 * @author noy dvori 211908256
 * Collidable interface.
 */
public interface Collidable {
    /**
     * this function returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * this function returns the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param hitter          - given ball.
     * @param collisionPoint  - given collision point.
     * @param currentVelocity - the current velocity.
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}