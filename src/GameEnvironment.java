import java.util.ArrayList;
import java.util.List;

/**
 * @author noy dvori 211908256
 * class GameEnvironment.
 * GameEnvironment includes list of collidables.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidablesList = new ArrayList<>();

    /**
     * the function adds the given collidable to the environment.
     *
     * @param c - the given collidable.
     */
    public void addCollidable(Collidable c) {
        collidablesList.add(c);
    }

    /**
     * this function returns the CollidablesList.
     *
     * @return the CollidablesList.
     */
    public ArrayList<Collidable> getCollidablesList() {
        return collidablesList;
    }

    /**
     * If this object will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory - the given line.
     * @return If this object will not collide with any of the collidables in this collection,
     * return null. Else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> list = new ArrayList<>();
        // if the collidable list is empty- returns null
        if (collidablesList == null) {
            return null;
        }
        // make list of all the closest points
        for (Collidable i : collidablesList) {
            list.add(trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle()));
        }
        // if the list is empty- returns null
        if ((trajectory.findClosestIntersectionToStartOfLine(list)) == null) {
            return null;
        }
        // finds the final closest point from list and saves the information
        int index = list.indexOf(trajectory.findClosestIntersectionToStartOfLine(list));
        Collidable collisionObject = collidablesList.get(index);
        return new CollisionInfo(trajectory.findClosestIntersectionToStartOfLine(list), collisionObject);
    }

    /**
     * this function removes the given collidable from collidablesList of this game environment.
     *
     * @param c - the given collidable.
     */
    public void removeCollidable(Collidable c) {
        collidablesList.remove(c);
    }
}