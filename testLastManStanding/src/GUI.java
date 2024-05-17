import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame{//This class is designed to be replaced by a GUI
    //Code to display - Will be shifted to updating something on the GUI

    String[] imagepaths = new String[]{"assets/lastManStandingBG.png", "assets/titlescreenImage.png"};
    ArrayList<ImageIcon> images = new ArrayList<>();
    Font bestFont = new Font("Monospaced", Font.PLAIN, 24);
    ImageIcon background = new ImageIcon(this.getClass().getResource("assets/titlescreenImage.png"));
    public GUI() {
        super("Last Man Standing");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setLayout(null);
        this.setResizable(false);

        JPanel titleScreen = new JPanel();
        titleScreen.setBounds(0,-16,900,600);
        titleScreen.setLayout(null);
        this.add(titleScreen);

        for (String i : imagepaths) {
            images.add(new ImageIcon(this.getClass().getResource(i)));
        }

        JPanel characterSelectionScreen = new JPanel();
        characterSelectionScreen.setBounds(0,0,900,600);
        characterSelectionScreen.setLayout(null);

        JLabel backgroundImage = new JLabel();
        backgroundImage.setBounds(0,0,900,600);
        backgroundImage.setIcon(background);
        titleScreen.add(backgroundImage);

        JButton startGameButton = new JButton("<html><div style=\"text-align:center;\">Start Game (of doom)</div></html>");
        startGameButton.setBounds(350,325,200,75);
        startGameButton.setBackground(new Color(0,0,0));
        startGameButton.setForeground(new Color(255,255,255));
        startGameButton.setFocusPainted(false);
        startGameButton.setFont(bestFont);
        startGameButton.addActionListener(e -> {
            remove(titleScreen);
            add(characterSelectionScreen);
            repaint();
            revalidate();
        });
        titleScreen.add(startGameButton);



        JLabel testingText = new JLabel("testing");
        testingText.setBounds(0,0,10,10);
        characterSelectionScreen.add(testingText);

        CharacterSelectionView p1View = new CharacterSelectionView(100,25, 300, 300, images);

        characterSelectionScreen.add(p1View);



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


