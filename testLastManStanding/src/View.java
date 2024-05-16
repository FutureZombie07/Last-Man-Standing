public class View {//This class is designed to be replaced by a GUI
    //Code to display - Will be shifted to updating something on the GUI

    //Says what a player does
    public static void sayPlayerAction(TestPlayerClass player){System.out.println(player+" pulled lever: "+player.isLeverPulled());}

    //"Rotates" the squasher
    public static void rotate(int x){
        System.out.println("The squaser has rotated "+x+" times.");
    }

    //Says who was squashed
    public static void squash(TestPlayerClass player){System.out.println(player+" was squashed...");}
}
