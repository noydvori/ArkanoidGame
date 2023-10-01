import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * @author noy dvori 211908256
 * class GameFlow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter score;

    /**
     * constructor of GameFlow.
     *
     * @param ar
     * @param ks
     * @param gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.score = new Counter();
    }

    /**
     * this function runs the levels.
     *
     * @param levels - given list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        Sleeper sleeper = new Sleeper();
        for (int i = 0; i < levels.size(); i++) {
            GameLevel level = new GameLevel(levels.get(i), gui, this.keyboardSensor, this.animationRunner, this.score);
            level.initialize();
            level.run();
            if (level.getBallCounter().getValue() == 0) {
                sleeper.sleepFor(1000);
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY,
                        new EndScreen(keyboardSensor, score, false, gui)));
                break;
            } else if (level.getBlockCounter().getValue() == 0 && i == levels.size() - 1) {
                sleeper.sleepFor(1000);
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY,
                        new EndScreen(keyboardSensor, score, true, gui)));
            } else {
                sleeper.sleepFor(1000);
            }
        }
        gui.close();
    }
}

