import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author noy dvori 211908256
 * class WideEasy.
 */
public class WideEasy implements LevelInformation {
    private int paddleWidth;
    private int paddleSpeed;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;
    private String levelName;
    private Sprite background;
    private int numberOfBalls;
    private int numberOfBlocksToRemove;

    /**
     * this function generate the blocks of the game.
     */
    public void blocksLine() {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color(215, 1, 1));
        colors.add(new Color(253, 90, 2));
        colors.add(new Color(253, 165, 2));
        colors.add(new Color(253, 240, 2));
        colors.add(new Color(132, 253, 2));
        colors.add(new Color(2, 253, 44));
        colors.add(new Color(2, 253, 194));
        colors.add(new Color(2, 224, 253));
        colors.add(new Color(2, 186, 253));
        colors.add(new Color(2, 140, 253));
        colors.add(new Color(132, 2, 253));
        colors.add(new Color(161, 2, 253));
        colors.add(new Color(228, 2, 253));
        colors.add(new Color(253, 2, 194));
        colors.add(new Color(253, 2, 174));
        int index = 0;
        for (int i = 10; i < 780; i += 52) {
            Block block = new Block(new Rectangle(new Point(i, 240), 52, 26), colors.get(index));
            this.blocks.add(block);
            index++;
        }
    }

    /**
     * Wide Easy level.
     */
    public WideEasy() {
        this.levelName = "Wide Easy";
        this.numberOfBalls = 10;
        this.numberOfBlocksToRemove = 15;
        this.blocks = new ArrayList<>();
        blocksLine();
        this.initialBallVelocities = new ArrayList<>();
        for (int i = 110; i < 260; i += 15) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(i, 5));
        }
        this.paddleSpeed = 10;
        this.paddleWidth = 450;

        this.background = new Block(new Rectangle((new Point(0, 0)), 800, 600), new Color(149, 237, 255, 255));
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
