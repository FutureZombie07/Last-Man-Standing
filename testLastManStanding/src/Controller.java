import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);

    //-----M E N U   C O N T R O L L E R   C L A S S-----\\



    //-----G A M E   C O N T R O L L E R   C L A S S-----\\
    public static String scannerString(){//Currently scans text, will be swapped to elements on a gui
        String toReturn = "";
        toReturn=scanner.nextLine();
        return toReturn;
    }
}
