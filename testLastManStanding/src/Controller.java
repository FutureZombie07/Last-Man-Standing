import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    private static GameLoop gameLoop;

    private static long lastWhen = 0L;

    public static String scannerString(){//Currently scans text, will be swapped to elements on a gui
        String toReturn = "";
        toReturn=scanner.nextLine();
        return toReturn;
    }

    public static void startButton(CharacterSelectionView[] views, GUI gui) {
        PlayerClass[] players = new PlayerClass[]{
                new PlayerClass(views[0].getName(),views[0].getCurrentImage(), 'q','w'),
                new PlayerClass(views[1].getName(),views[1].getCurrentImage(), 'c','v'),
                new PlayerClass(views[2].getName(),views[2].getCurrentImage(), 'y','u'),
                new PlayerClass(views[3].getName(),views[3].getCurrentImage(), 'm',',')
        };
        GameLoop gameLoop = new GameLoop(players, gui);
        Controller.gameLoop = gameLoop;
        gameLoop.start();
        Controller.registerKeyboardListener(gui);
    }

    public static void registerKeyboardListener(GUI gui) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getWhen() - lastWhen < 200) {
                return false;
            } else {
                lastWhen = e.getWhen();
            }
            boolean validOperation = false;
            for (PlayerClass player: Controller.gameLoop.getPlayers()) {
                if (e.getKeyChar() == player.getYes()) {
                    player.setLeverPulled(true);
                    player.setSelected(true);
                    validOperation = true;
                } else if (e.getKeyChar() == player.getNo()) {
                    player.setLeverPulled(false);
                    player.setSelected(true);
                    validOperation = true;
                }
            }
            if (validOperation) {
                new Notifier();
                gui.redrawEverything();
            }
            for (PlayerClass player : Controller.gameLoop.getPlayers()) {
                if (!player.isSelected()) {
                    return false;
                }
            }


            return false;
        });
    }

    public static GameLoop getGameLoop() {
        return Controller.gameLoop;
    }

}
