/**
 * @author noy dvori 211908256
 * class BallRemover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor of BallRemover.
     *
     * @param game         - given game.
     * @param removedBalls - given counter.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }

    /**
     * this function update the counter value.
     *
     * @param counter - the updated counter.
     */
    public void setCounter(Counter counter) {
        this.remainingBalls = counter;
    }
}
