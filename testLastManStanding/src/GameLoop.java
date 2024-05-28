import javax.swing.*;
import java.util.Random;

public class GameLoop extends Thread {
    //-----I N I T I A L I Z A T I O N-----\\
    private int playerCount = 0;
    private PlayerClass[] players = {};

    private int smasherPosition = 3;


    private int leverSwitches;

    private GUI gui;

    //-----C O N S T R U C T O R-----\\
    public GameLoop(PlayerClass[] array, GUI gui) {
        this.players = array;
        this.playerCount = array.length;
        this.gui = gui;
    }

    //-----M E T H O D S-----\\
    @Override
    public void run() {//Code to run the game's main loop once
        while (true) {
            for (int x = 0; x < 4; x++) {
                players[x].setSquashed(false);
                playerCount = 4;
            }

            while (playerCount > 1) {//This runs the main loop multiple times
                Animator animator = new Animator(gui);
                for (int x = 0; x < 4; x++) {
                    players[x].setLeverPulled(false);
                    players[x].setSelected(false);
                }//Resets everyone's lever pulls
                /*playerCount = 0;
                for (int x = 0; x < 4; x++) {
                    if (!players[x].isSquashed()) {
                        playerCount++;
                    }
                }*/
                Random random = new Random();
                leverSwitches = random.nextInt(3);
                Animator.setCurrentSwitches(leverSwitches);//Keeps track of how many lever switches have happened
                gui.redrawEverything();
                synchronized (Notifier.notifier) {
                    while (!allPlayersSelected()) {
                        try {
                            System.out.println("received notify");
                            Notifier.notifier.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }

                System.out.println("all players selected!");


                //This loop displays what everyone selected
                for (int x = 0; x < playerCount; x++) {
                    if (players[x].isLeverPulled() && !players[x].isSquashed()) {
                        leverSwitches++;
                    }
                }
                //This loop says how many times the squasher rotated


                //Code to squash & eliminate a player
                //Code goes Here


                animator.setCurrentSwitches(leverSwitches);
                System.out.println("joined animator");
                animator.start();
                synchronized (Controller.getGameLoop()) {
                    try {
                        Controller.getGameLoop().wait();
                    } catch (InterruptedException e) {

                    }
                }

                smasherPosition = Animator.getCurrentGUISmasherPosition();
                System.out.println(smasherPosition);
                //players[smasherPosition].setSquashed(true);
                playerCount = 0;
                for (int x = 0; x < 4; x++) {
                    if (!players[x].isSquashed()) {
                        playerCount++;
                    }
                }
            }
            PlayerClass winningPlayer = null;
            for (PlayerClass player : players) {
                if (!player.isSquashed()) {
                    winningPlayer = player;
                    break;
                }
            }
            int response = JOptionPane.showOptionDialog(null, winningPlayer.getName() + "wins! Play Again?", "Winner", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            if (response == 1) {
                System.exit(0);
            }

            //Code to end or rematch

        }
    }

    private void playerInputCheck(String input, int playerNum) {//Code to check a player's input
        if (input.equals(players[playerNum].getYes())) {
            players[playerNum].setLeverPulled(true);
        } else if (input.equals(players[playerNum].getNo())) {
            players[playerNum].setLeverPulled(false);
        } else {
            System.out.println("Player " + playerNum + ", please input your yes or no key");
            input = Controller.scannerString();
            playerInputCheck(input, playerNum);//If the player inputted an invalid response this repeats the scenario
        }
    }

    public PlayerClass[] getPlayers() {
        return this.players;
    }

    public boolean allPlayersSelected() {
        for (PlayerClass player : players) {
            if (!player.isSelected() && !player.isSquashed()) {
                return false;
            }
        }
        return true;
    }

    public int getSmasherPosition() {
        return this.smasherPosition;
    }

    public void setSmasherPosition(int smasherPosition) {
        this.smasherPosition = smasherPosition;
    }

    public int getLeverSwitches() {
        return leverSwitches;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}