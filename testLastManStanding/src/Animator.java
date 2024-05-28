public class Animator extends Thread {

    private static int currentSwitches = 0;

    private static int currentGUISmasherPosition = 0;

    private GUI gui;

    private static boolean isSmasherSmashing = false;

    public Animator(GUI gui) {
        super();
        this.gui = gui;
    }

    public void animateEverything() {
    }

    @Override
    public void run() {
        int totalSwitches = currentSwitches;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
        for (int i = currentSwitches; i >0; i--) {
            currentSwitches--;
            Animator.currentGUISmasherPosition++;
            Animator.currentGUISmasherPosition = Animator.currentGUISmasherPosition % 4;
            while (Controller.getGameLoop().getPlayers()[Animator.currentGUISmasherPosition].isSquashed()) {
                Animator.currentGUISmasherPosition++;
                Animator.currentGUISmasherPosition = Animator.currentGUISmasherPosition % 4;
            }
            gui.redrawEverything();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {

            }
        }

        isSmasherSmashing = true;
        gui.redrawEverything();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        isSmasherSmashing = false;
        Controller.getGameLoop().getPlayers()[Animator.currentGUISmasherPosition].setSquashed(true);
        Animator.currentGUISmasherPosition++;
        Animator.currentGUISmasherPosition = Animator.currentGUISmasherPosition % 4;
        while (Controller.getGameLoop().getPlayers()[Animator.currentGUISmasherPosition].isSquashed()) {
            System.out.println("was squashed");
            Animator.currentGUISmasherPosition++;
            Animator.currentGUISmasherPosition = Animator.currentGUISmasherPosition % 4;
        }
        //notofiy game loop to keep going cuz join didnt work
        synchronized (Controller.getGameLoop()) {
            Controller.getGameLoop().notify();
        }
    }

    public void redrawEverything() {
        gui.redrawEverything();
    }

    public static void setCurrentSwitches(int currentSwitches) {
        Animator.currentSwitches = currentSwitches;
    }

    public static int getCurrentSwitches() {
        return currentSwitches;
    }

    public static int getCurrentGUISmasherPosition() {
        return currentGUISmasherPosition;
    }

    public static void setCurrentGUISmasherPosition(int currentGUISmasherPosition) {
        Animator.currentGUISmasherPosition = currentGUISmasherPosition;
    }

    public static boolean isSmasherSmashing() {
        return isSmasherSmashing;
    }
}
