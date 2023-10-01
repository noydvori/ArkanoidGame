import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * @author noy dvori 211908256
 * class FinalFour.
 */
public class FinalFour implements LevelInformation {
    private int paddleWidth;
    private int paddleSpeed;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;
    private Sprite background;
    private String levelName;
    private int numberOfBalls;
    private int numberOfBlocksToRemove;

    /**
     * this function adds to the game the blocks pattern of this level.
     */
    public void blocksLines() {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color(2, 155, 250));
        colors.add(new Color(92, 189, 250));
        colors.add(new Color(132, 207, 253));
        colors.add(new Color(193, 231, 255));
        colors.add(new Color(214, 185, 255));
        colors.add(new Color(189, 144, 253));
        colors.add(new Color(164, 99, 255));
        int index = 0;
        for (int j = 80; j <= 200; j += 20) {
            for (int i = 10; i < 780; i += 52) {
                Block block = new Block(new Rectangle(new Point(i, j), 52, 20), colors.get(index));
                this.blocks.add(block);
            }
            index++;
        }
    }

    /**
     * Final Four level.
     */
    public FinalFour() {
        this.levelName = "Final Four";
        this.numberOfBalls = 3;
        this.numberOfBlocksToRemove = 15;
        this.blocks = new ArrayList<>();
        blocksLines();
        this.initialBallVelocities = new ArrayList<>();
        for (int i = 170; i < 215; i += 10) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(i, 5));
        }
        this.paddleSpeed = 10;
        this.paddleWidth = 80;
        this.background = new Block(new Rectangle((new Point(0, 0)), 800, 600), new Color(133, 245, 253, 255));
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