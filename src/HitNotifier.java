/**
 * @author noy dvori 211908256
 * HitNotifier interface.
 * The HitNotifier interface indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * thos function adds hl as a listener to hit events.
     *
     * @param hl - the given HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * this function removes hl from the list of listeners to hit events.
     *
     * @param hl - the given HitListener.
     */
    void removeHitListener(HitListener hl);
}