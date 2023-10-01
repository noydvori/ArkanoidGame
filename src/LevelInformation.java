import java.util.List;

/**
 * @author noy dvori 211908256
 * interface LevelInformation.
 */
public interface LevelInformation {
    /**
     * this function returns the number of balls.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * this function returns the list of velocities.
     *
     * @return the list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * this function returns the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * this function returns the paddle width.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * this function returns the name of the level.
     *
     * @return - the name of the level.
     */
    String levelName();

    /**
     * this function returns the background.
     *
     * @return the background.
     */
    Sprite getBackground();

    /**
     * this function returns the list of blocks.
     *
     * @return the list of blocks.
     */
    List<Block> blocks();

    /**
     * this function returns the number of the remaining blocks.
     *
     * @return the number of the remaining blocks.
     */
    int numberOfBlocksToRemove();
}