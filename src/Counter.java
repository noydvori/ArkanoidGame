/**
 * @author noy dvori 211908256
 * class Counter.
 * Counter is a simple class that is used for counting things
 */
public class Counter {
    private int counter;

    /**
     * default constructor of ball.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * constructor of ball.
     *
     * @param counter - given counter.
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * this function add number to current count.
     *
     * @param number - the given number.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * this function subtract number from current count.
     *
     * @param number - the given number.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * this function gets the current count.
     *
     * @return current count.
     */
    public int getValue() {
        return this.counter;
    }
}