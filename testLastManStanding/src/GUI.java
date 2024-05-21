import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame{//This class is designed to be replaced by a GUI
    //Code to display - Will be shifted to updating something on the GUI

    String[] imagepaths = new String[]{"assets/lastManStandingBG.png", "assets/titlescreenImage.png"};
    ArrayList<ImageIcon> images = new ArrayList<>();
    static Font bestFont = new Font("Monospaced", Font.PLAIN, 24);
    ImageIcon background = new ImageIcon(this.getClass().getResource("assets/titlescreenImage.png"));
    ImageIcon background2 = new ImageIcon(this.getClass().getResource("assets/characterSelectionScreenTemplate.png"));
    final int characterViewWidth = 402;
    final int characterViewHeight = 280;
    public GUI() {
        super("Last Man Standing");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(null);
        this.setResizable(false);

        JPanel titleScreen = new JPanel();
        titleScreen.setBounds(0,0,1280,720);
        titleScreen.setLayout(null);
        this.add(titleScreen);

        for (String i : imagepaths) {
            images.add(new ImageIcon(new ImageIcon(this.getClass().getResource(i)).getImage().getScaledInstance((int) (characterViewWidth*0.8 + 1), characterViewHeight, Image.SCALE_DEFAULT)));
        }

        JPanel characterSelectionScreen = new JPanel();
        characterSelectionScreen.setBounds(0,0,1280,720);
        characterSelectionScreen.setLayout(null);



        JButton startGameButton = new JButton("<html><div style=\"text-align:center;\">Start Game (of doom)</div></html>");
        startGameButton.setBounds(535,400,200,75);
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

        JLabel backgroundImage = new JLabel();
        backgroundImage.setBounds(0,0,1280,720);
        backgroundImage.setIcon(background);
        titleScreen.add(backgroundImage);

        CharacterSelectionView[] views = new CharacterSelectionView[4];
        CharacterSelectionView p1View = new CharacterSelectionView(30,18, characterViewWidth, characterViewHeight, images);
        views[0] =p1View;
        characterSelectionScreen.add(p1View);
        //TODO: add 3 more views



        JButton startButton = new JButton("<html><div style=\"text-align:center;\">Begin (of doom)</div></html>");
        startButton.setBounds(500,310,200,100);
        startButton.setBackground(new Color(0,0,0));
        startButton.setForeground(new Color(255,255,255));
        startButton.setFocusPainted(false);
        startButton.setFont(bestFont);
        startButton.addActionListener(e -> {
            Controller.startButton(views);
        });
        characterSelectionScreen.add(startButton);


        JLabel backgroundImage2 = new JLabel();
        backgroundImage2.setBounds(-12,-16,1280,720);
        backgroundImage2.setIcon(background2);
        characterSelectionScreen.add(backgroundImage2);
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


