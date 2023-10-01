/**
 * @author noy dvori 211908256
 * class BlockRemover.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count of the number of blocks
 * that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor of BlockRemover.
     *
     * @param game          - given game.
     * @param removedBlocks - given counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }

    /**
     * this function update the counter value.
     *
     * @param counter - the updated counter.
     */
    public void setCounter(Counter counter) {
        this.remainingBlocks = counter;
    }
}
