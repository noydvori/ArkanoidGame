import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author noy dvori 211908256
 * class Ass6Game.
 */
public class Ass6Game {
    /**
     * @param args - command line.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
            for (String str : args) {
                switch (str) {
                    case "1": {
                        levels.add(new DirectHit());
                    }
                    case "2": {
                        levels.add(new WideEasy());
                    }
                    case "3": {
                        levels.add(new Green3());
                    }
                    case "4": {
                        levels.add(new FinalFour());
                    }
                    default: {
                    }
                }
            }
           if (levels.isEmpty()) {
               levels.add(new DirectHit());
               levels.add(new WideEasy());
               levels.add(new Green3());
               levels.add(new FinalFour());
           }
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor(), gui);
        gameFlow.runLevels(levels);
    }
}
