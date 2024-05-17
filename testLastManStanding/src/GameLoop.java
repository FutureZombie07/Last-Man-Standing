import java.util.ArrayList;
import java.util.Random;

public class GameLoop {
    //-----I N I T I A L I Z A T I O N-----\\
    private int playerCount = 0;
    private static PlayerClass array[] = {};
    private static ArrayList<PlayerClass> players = new ArrayList<>();
    private static Random random = new Random();

    //-----C O N S T R U C T O R-----\\
    public GameLoop(PlayerClass[] array){
        this.array=array;
        this.playerCount=array.length;
    }

    //-----M E T H O D S-----\\

    public void run(){//Code to run the game's main loop once
        repopulatePlayers();
        playerCount= players.size();
        while(playerCount>1) {//This runs the main loop multiple times
            //Resets everyone's lever pulls
            for(int x = 0;x<playerCount;x++){players.get(x).setLeverPulled(false);}
            playerCount=0;
            for (int x=0;x<4;x++){
                if(!players.get(x).isSquashed()){
                    playerCount++;
                }
            }
            int leverSwitches = 0;//Keeps track of how many lever switches have happened
            String scannerOutput="";

            //This loop goes around & receives everyone's input
            for (int x = 0; x < playerCount; x++) {
                if(!players.get(x).isBot()){
                    //Section for players making a choice
                    scannerOutput = Controller.scannerString();
                    if(playerInputCheck(scannerOutput, x)){
                        leverSwitches++;
                    }
                }else{
                    //Section for bots making a choice
                    int botDecision = random.nextInt(2);
                    if(botDecision==1){
                        players.get(x).setLeverPulled(true);
                        leverSwitches++;
                    }
                }
            }

            //This loop displays what everyone selected
            for (int x = 0;x<playerCount; x++) {GUI.sayPlayerAction(players.get(x));}

            //This loop says how many times the squasher rotated
            for (int x = 0;x<leverSwitches;x++){GUI.rotate(x);}

            //Code to squash & eliminate a player
            //Code goes Here

            if(leverSwitches>playerCount){leverSwitches=0;}
            players.get(leverSwitches).setSquashed(true);
            GUI.squash(players.get(leverSwitches));
            players.remove(leverSwitches);
        }

        //Code to end or rematch

    }

    //-----M E T H O D S-----\\
    //Code to check a player's input
    private static boolean playerInputCheck(String input, int playerNum){
        boolean pulled = false;
        if(input.equals(players.get(playerNum).getYes())){
            players.get(playerNum).setLeverPulled(true);
            pulled=true;
        }else if (input.equals(players.get(playerNum).getNo())){
            players.get(playerNum).setLeverPulled(false);
        }else {
            //
            System.out.println(players.get(playerNum).getName()+", please input your yes or no key");//Temp code
            input = Controller.scannerString();
            playerInputCheck(input, playerNum);//If the player inputted an invalid response this repeats the scenario
        }
        return pulled;
    }

    private static void repopulatePlayers(){
        for(int x = 0;x < array.length;x++){players.add(array[x]);}
    }

}