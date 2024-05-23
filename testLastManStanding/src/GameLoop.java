public class GameLoop extends Thread {
    //-----I N I T I A L I Z A T I O N-----\\
    private int playerCount = 0;
    private PlayerClass[] players = {};


    //-----C O N S T R U C T O R-----\\
    public GameLoop(PlayerClass[] array) {
        this.players = array;
        this.playerCount = array.length;
    }

    //-----M E T H O D S-----\\
    @Override
    public void run() {//Code to run the game's main loop once
        while (playerCount > 1) {//This runs the main loop multiple times
            for (int x = 0; x < playerCount; x++) {
                players[x].setLeverPulled(false);
                players[x].setSelected(false);
            }//Resets everyone's lever pulls
            playerCount = 0;
            for (int x = 0; x < 4; x++) {
                if (!players[x].isSquashed()) {
                    playerCount++;
                }
            }
            int leverSwitches = 0;//Keeps track of how many lever switches have happened
            String scannerOutput = "";

            while (!allPlayersSelected()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            //This loop goes around & receives everyone's input
            /*
            for (int x = 0; x < playerCount; x++) {
                scannerOutput = Controller.scannerString();
                playerInputCheck(scannerOutput, x);
            }*/

            //This loop displays what everyone selected
            for (int x = 0; x < playerCount; x++) {
                if (players[x].isLeverPulled()) {
                    leverSwitches++;
                }
            }

            //This loop says how many times the squasher rotated
            /*for (int x = 0; x < leverSwitches; x++) {
                GUI.rotate(x);
            }*/

            //Code to squash & eliminate a player
            //Code goes Here

            players[leverSwitches].setSquashed(true);
        }

        //Code to end or rematch

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
            if (!player.isSelected()) {
                return false;
            }
        }
        return true;
    }

}