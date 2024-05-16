import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);

    public static String scannerString(){//Currently scans text, will be swapped to elements on a gui
        String toReturn = "";
        toReturn=scanner.nextLine();
        return toReturn;
    }
}
