import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{//This class is designed to be replaced by a GUI
    //Code to display - Will be shifted to updating something on the GUI

    ImageIcon background = new ImageIcon(this.getClass().getResource("assets/titlescreenImage.png"));
    public GUI() {
        super("Last Man Standing");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setLayout(null);

        JPanel titleScreen = new JPanel();
        titleScreen.setBounds(0,-16,900,600);
        titleScreen.setLayout(null);
        this.add(titleScreen);

        JLabel backgroundImage = new JLabel();
        backgroundImage.setBounds(0,0,900,600);
        backgroundImage.setIcon(background);
        titleScreen.add(backgroundImage);

        JButton startGameButton = new JButton("Start Game (of doom)");
        startGameButton.setBounds(362,315,175,60);
        startGameButton.setBackground(new Color(0,0,0));
        startGameButton.setForeground(new Color(255,255,255));
        startGameButton.setFocusPainted(false);
        titleScreen.add(startGameButton);


        this.setVisible(true);
    }
    //Temporary main function to test the GUI
    public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception e){

        }
        new GUI();
    }

    //legacy functions
    public static void sayPlayerAction(TestPlayerClass player){System.out.println(player+" pulled lever: "+player.isLeverPulled());}

    //"Rotates" the squasher
    public static void rotate(int x){
        System.out.println("The squaser has rotated "+x+" times.");
    }

    //Says who was squashed
    public static void squash(TestPlayerClass player){System.out.println(player+" was squashed...");}
}


