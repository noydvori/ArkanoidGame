import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author noy dvori 211908256
 * class Green3.
 */
public class Green3 implements LevelInformation {
    private int paddleWidth;
    private int paddleSpeed;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;
    private Sprite background;
    private String levelName;
    private int numberOfBalls;
    private int numberOfBlocksToRemove;


    /**
     * this function creates line of blocks.
     *
     * @param x     - x value of the start point.
     * @param y     - y value of the start point.
     * @param color - given color of block.
     */
    public void generateLineBlocks(int x, int y, Color color) {
        for (int i = x; i <= 740; i += 50) {
            Block block = new Block(new Rectangle(new Point(i, y), 50, 20), color);
            blocks.add(block);
        }
    }

    /**
     * this function generate the blocks in game.
     */
    public void generateGameBlocks() {
        generateLineBlocks(290, 100, new Color(30, 148, 41, 255));
        generateLineBlocks(340, 120, new Color(90, 194, 116, 255));
        generateLineBlocks(390, 140, new Color(161, 241, 181, 255));
        generateLineBlocks(440, 160, new Color(209, 253, 220, 255));
        generateLineBlocks(490, 180, new Color(253, 255, 254, 255));
    }

    /**
     * Green3 level.
     */
    public Green3() {
        this.levelName = "Green 3";
        this.numberOfBalls = 2;
        this.numberOfBlocksToRemove = 15;
        this.blocks = new ArrayList<>();
        generateGameBlocks();
        this.initialBallVelocities = new ArrayList<>();
        for (int i = 140; i < 230; i += 80) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(i, 5));
        }
        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        this.background = new Block(new Rectangle((new Point(0, 0)), 800, 600), new Color(28, 101, 44, 255));
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
