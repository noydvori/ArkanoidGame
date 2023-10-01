/**
 * @author noy dvori 211908256
 * class ScoreTrackingListener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor of ScoreTrackingListener.
     *
     * @param scoreCounter - the given score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * this function update the score if there's hit event.
     *
     * @param beingHit - given block.
     * @param hitter   - given ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}