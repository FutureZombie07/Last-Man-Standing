import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);

    public static String scannerString(){//Currently scans text, will be swapped to elements on a gui
        String toReturn = "";
        toReturn=scanner.nextLine();
        return toReturn;
    }

    public static void startButton(CharacterSelectionView[] views) {
        PlayerClass[] players = new PlayerClass[]{
                new PlayerClass(null,null,null,views[0].getCurrentImage()),
                new PlayerClass(null,null,null,views[1].getCurrentImage()),
                new PlayerClass(null,null,null,views[2].getCurrentImage()),
                new PlayerClass(null,null,null,views[3].getCurrentImage())
        };
    }
}
