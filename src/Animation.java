import biuoop.DrawSurface;

/**
 * @author noy dvori 211908256
 * class Animation.
 */
public interface Animation {
    /**
     * game-specific logic and stopping conditions are handle.
     *
     * @param d - given surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * this function is in charge of stopping condition.
     *
     * @return - true/false.
     */
    boolean shouldStop();
}
