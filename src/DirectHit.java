import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author noy dvori 211908256
 * class DirectHit.
 */
public class DirectHit implements LevelInformation {
    private int paddleWidth;
    private int paddleSpeed;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;
    private String levelName;
    private int numberOfBalls;
    private int numberOfBlocksToRemove;
    private Sprite background;

    /**
     * Direct Hit level.
     */
    public DirectHit() {
        this.levelName = "Direct Hit";
        this.numberOfBalls = 1;
        this.numberOfBlocksToRemove = 1;
        this.blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(380, 200), 40.0, 40.0), new Color(197, 3, 3)));
        this.initialBallVelocities = new ArrayList<>();
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(0, 5));
        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        this.background = new Block(new Rectangle((new Point(0, 0)), 800, 600), new Color(40, 44, 47));
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

}
