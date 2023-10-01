import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author noy dvori 211908256
 * class KeyPressStoppableAnimation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor of KeyPressStoppableAnimation.
     *
     * @param keyboardSensor
     * @param key
     * @param animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.stop = false;
        this.keyboardSensor = keyboardSensor;
        this.isAlreadyPressed = true;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboardSensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
}
