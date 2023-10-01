import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author noy dvori 211908256
 * class AnimationRunner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor of AnimationRunner.
     *
     * @param gui - given GUI.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * this function runs the given animation.
     *
     * @param animation - the given animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * this function runs one frame of the given animation.
     *
     * @param animation = the given animation.
     */
    public void runOneFrame(Animation animation) {
        DrawSurface d = gui.getDrawSurface();
        animation.doOneFrame(d);
        gui.show(d);
    }
}
