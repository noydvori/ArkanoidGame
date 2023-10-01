import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author noy dvori 211908256
 * class GameLevel.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private BlockRemover blockRemover;
    private Counter ballCounter;
    private BallRemover ballRemover;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private Sleeper sleeper;
    private Color paddleColor;
    private Color ballColor;
    private LevelInformation levelInformation;

    /**
     * constructor of game.
     *
     * @param levelInformation - the given information.
     * @param animationRunner  - the given animationRunner.
     * @param gui              - the given GUI.
     * @param keyboardSensor   - the given keyboardSensor.
     * @param score            - the given score.
     */
    public GameLevel(LevelInformation levelInformation, GUI gui, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.blockRemover = new BlockRemover(this, blockCounter);
        this.ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, ballCounter);
        this.score = score;
        this.gui = gui;
        this.runner = animationRunner;
        this.running = false;
        this.keyboard = keyboardSensor;
        this.sleeper = new Sleeper();
        this.levelInformation = levelInformation;
    }

    /**
     * this function returns this gui.
     *
     * @return this gui.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * this function add collidable to the environment.
     *
     * @param c - given collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * this function add sprite to the sprites.
     *
     * @param s - the given sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * this function update the color of the paddle.
     *
     * @param color - the given color.
     */
    public void setPaddleColor(Color color) {
        this.paddleColor = color;
    }

    /**
     * this function creates the paddle and adds it to gameLevel.
     */
    public void generatePaddle() {
        if (this.paddleColor == null) {
            this.paddleColor = Color.WHITE;
        }
        Point upperleft = new Point(400 - (this.levelInformation.paddleWidth() / 2), 570);
        Paddle paddle = new Paddle(new Block(new Rectangle(upperleft, levelInformation.paddleWidth(),
                10), this.paddleColor), gui, keyboard, levelInformation.paddleSpeed(), levelInformation.paddleWidth());
        paddle.addToGame(this);
        environment.addCollidable(paddle);
    }

    /**
     * this function creates balls (according the given number) and adds them to the gameLevel.
     */
    public void generateBalls() {
        if (this.ballColor == null) {
            this.ballColor = Color.WHITE;
        }
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 560), 3, this.ballColor);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            ballCounter.increase(1);
            ballRemover.setCounter(ballCounter);
        }
    }

    /**
     * this function creates borders and adds it to the gameLevel.
     */
    public void generateBorders() {
        Block up = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.gray);
        Block down = new Block(new Rectangle(new Point(0, 590), 800, 10), Color.WHITE);
        Block left = new Block(new Rectangle(new Point(0, 0), 10, 600), Color.gray);
        Block right = new Block(new Rectangle(new Point(790, 0), 10, 600), Color.gray);
        up.addToGame(this);
        down.addToGame(this);
        right.addToGame(this);
        left.addToGame(this);
        environment.addCollidable(up);
        environment.addCollidable(down);
        environment.addCollidable(right);
        environment.addCollidable(left);
        down.addHitListener(ballRemover);
    }

    /**
     * this function creates score indicator.
     */
    public void generateScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
    }

    /**
     * this function creates line of blocks.
     */
    public void generateGameBlocks() {
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {
            Block cur = this.levelInformation.blocks().get(i);
            cur.addHitListener(blockRemover);
            cur.addToGame(this);
            environment.addCollidable(cur);
            blockCounter.increase(1);
            this.blockRemover.setCounter(this.blockCounter);
            cur.addHitListener(scoreTrackingListener);
        }
    }


    /**
     * initialize a new game: create the Blocks and Ball (and Paddle) and add them to the gameLevel.
     */
    public void initialize() {
        generateBorders();
        generatePaddle();
        generateBalls();
        generateScoreIndicator();
        generateGameBlocks();
    }

    /**
     * this function un the gameLevel - start the animation loop.
     */
    public void run() {
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            runner.runOneFrame(this);
        }
    }

    /**
     * this function removes the given collidable from environment.
     *
     * @param c - the given collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * this function removes the given sprite from sprites.
     *
     * @param s - the given sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.drawBackground(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            runner.run(new KeyPressStoppableAnimation(keyboard, keyboard.SPACE_KEY, new PauseScreen(keyboard)));
        }
        d.setColor(Color.WHITE);
        d.drawText(20, 15, "Level Name: " + this.levelInformation.levelName(), 15);
        // in case there's no more blocks, the user won.
        if (this.blockCounter.getValue() == 0) {
            this.running = false;
            // in case there's no balls, the user lose.
        } else if (ballCounter.getValue() == 0) {
            this.running = false;
        }

    }

    /**
     * this function returns the number of balls.
     *
     * @return the number of balls.
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * this function returns the number of block.
     *
     * @return the number of block.
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * this function draws the background on the given surface, for each level.
     *
     * @param d - given surface
     */
    public void drawBackground(DrawSurface d) {
        this.levelInformation.getBackground().drawOn(d);
        switch (this.levelInformation.levelName()) {
            case "Direct Hit": {
                d.setColor(Color.WHITE);
                d.drawCircle(400, 220, 85);
                d.drawCircle(400, 220, 115);
                d.drawCircle(400, 220, 145);
                d.drawLine(400, 25, 400, 190);
                d.drawLine(400, 250, 400, 415);
                d.drawLine(225, 220, 375, 220);
                d.drawLine(425, 220, 575, 220);
                break;
            }
            case "Wide Easy": {
                d.setColor(new Color(255, 255, 150));
                int endX = 26;
                int endY = 249;
                for (int i = 0; i < 80; i++) {
                    d.drawLine(155, 150, endX, endY);
                    endX += 20;
                }
                d.fillCircle(150, 150, 60);
                d.setColor(new Color(255, 255, 120));
                d.fillCircle(150, 150, 54);
                d.setColor(new Color(253, 253, 21));
                d.fillCircle(150, 150, 48);
                break;
            }
            case "Final Four": {
                int x = 143, y = 425;
                d.setColor(new Color(255, 255, 255));
                for (int i = 0; i < 10; i++) {
                    d.drawLine(x, y, (x - 25), 590);
                    x += 8;
                }
                x = 605;
                y = 485;
                for (int i = 0; i < 10; i++) {
                    d.drawLine(x, y, (x - 35), 590);
                    x += 8;
                }
                d.setColor(new Color(197, 197, 197));
                d.fillCircle(145, 425, 25);
                d.fillCircle(600, 470, 30);
                d.fillCircle(155, 433, 26);
                d.fillCircle(618, 497, 26);
                d.setColor(new Color(225, 225, 225));
                d.fillCircle(173, 413, 24);
                d.fillCircle(635, 479, 35);
                d.setColor(new Color(255, 255, 255));
                d.fillCircle(207, 425, 28);
                d.fillCircle(661, 482, 28);
                d.setColor(new Color(248, 248, 248));
                d.fillCircle(187, 440, 22);
                d.fillCircle(650, 495, 25);
                break;
            }
            default: {
            }
        }
    }
}