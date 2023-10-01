/**
 * @author noy dvori 211908256
 * HitListener interface.
 * Objects that want to be notified of hit events, should implement the HitListener interface,
 * and register themselves with a HitNotifier object
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit - given block.
     * @param hitter   - given ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}