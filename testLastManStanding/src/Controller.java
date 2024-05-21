import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    private static GameLoop gameLoop;

    public static String scannerString(){//Currently scans text, will be swapped to elements on a gui
        String toReturn = "";
        toReturn=scanner.nextLine();
        return toReturn;
    }

    public static void startButton(CharacterSelectionView[] views) {
        PlayerClass[] players = new PlayerClass[]{
                new PlayerClass(views[0].getName(),views[0].getCurrentImage(), 'q','w'),
                new PlayerClass(views[1].getName(),views[1].getCurrentImage(), 'c','v'),
                new PlayerClass(views[2].getName(),views[2].getCurrentImage(), 'y','u'),
                new PlayerClass(views[3].getName(),views[3].getCurrentImage(), 'm',',')
        };
        GameLoop gameLoop = new GameLoop(players);
        Controller.gameLoop = gameLoop;
    }

    public static void registerKeyboardListener() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            for (PlayerClass player: Controller.gameLoop.getPlayers()) {
                if (e.getKeyChar() == player.getYes()) {
                    player.setLeverPulled(true);
                } else if (e.getKeyChar() == player.getNo()) {
                    player.setLeverPulled(false);
                }
            }
            return false;
        });
    }
}
