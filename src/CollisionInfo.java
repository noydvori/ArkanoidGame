/**
 * @author noy dvori 211908256
 * class CollisionInfo.
 * collisionInfo includes the point of collision and the object type.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor of collisionInfo.
     *
     * @param collisionPoint  - given point.
     * @param collisionObject - given object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the function returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * this function update the collision object.
     *
     * @param collisionPoint - the given point.
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * this function update the collision point.
     *
     * @param collisionObject - the given object.
     */
    public void setCollisionObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }

    /**
     * the function returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
