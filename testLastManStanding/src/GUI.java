import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame{//This class is designed to be replaced by a GUI
    //Code to display - Will be shifted to updating something on the GUI
    //TODO: make animateevents function that is threaded and animates everything
    String[] imagepaths = new String[]{"assets/lastManStandingBG.png", "assets/titlescreenImage.png"};
    ArrayList<ImageIcon> images = new ArrayList<>();
    static Font bestFont = new Font("Monospaced", Font.PLAIN, 24);
    ImageIcon background = new ImageIcon(this.getClass().getResource("assets/titlescreenImage.png"));
    ImageIcon background2 = new ImageIcon(this.getClass().getResource("assets/characterSelectionScreenTemplate.png"));

    ImageIcon backgroundIngame = new ImageIcon(this.getClass().getResource("assets/lastManStandingBG.png"));
    final int characterViewWidth = 402;
    final int characterViewHeight = 280;

    JPanel ingameScreen;
    public GUI() {
        super("Last Man Standing");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(null);
        this.setResizable(false);

        ingameScreen = new JPanel();
        ingameScreen.setBounds(0,0,1280,720);
        ingameScreen.setLayout(null);

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

        CharacterSelectionView p2View = new CharacterSelectionView(830,18, characterViewWidth, characterViewHeight, images);
        views[1] =p2View;
        characterSelectionScreen.add(p2View);

        CharacterSelectionView p3View = new CharacterSelectionView(26,387, characterViewWidth, characterViewHeight, images);
        views[2] =p3View;
        characterSelectionScreen.add(p3View);

        CharacterSelectionView p4View = new CharacterSelectionView(836,387, characterViewWidth, characterViewHeight, images);
        views[3] =p4View;
        characterSelectionScreen.add(p4View);
        //TODO: add 3 more views

        JButton startButton = new JButton("<html><div style=\"text-align:center;\">Begin (of doom)</div></html>");
        startButton.setBounds(545,310,200,100);
        startButton.setBackground(new Color(0,0,0));
        startButton.setForeground(new Color(255,255,255));
        startButton.setFocusPainted(false);
        startButton.setFont(bestFont);
        startButton.addActionListener(e -> {
            Controller.startButton(views);
            this.redrawEverything();
            remove(characterSelectionScreen);
            add(ingameScreen);
            repaint();
            revalidate();
        });
        characterSelectionScreen.add(startButton);


        JLabel backgroundImage2 = new JLabel();
        backgroundImage2.setBounds(-12,-16,1280,720);
        backgroundImage2.setIcon(background2);
        characterSelectionScreen.add(backgroundImage2);


        this.redrawEverything();


        this.setVisible(true);
    }

    public void redrawEverything() {//Updates screen according to the GameLoop
        this.ingameScreen.removeAll();
        if (Controller.getGameLoop() != null) {
            if (!Controller.getGameLoop().getPlayers().get(0).isSquashed()) {
                PlayerClass player = Controller.getGameLoop().getPlayers().get(0);
                JLabel playerLabel = new JLabel();
                playerLabel.setBounds(200,400,100,100);
                playerLabel.setIcon(player.getPlayerImage());
                this.ingameScreen.add(playerLabel);

                JLabel isSelectedLabel = new JLabel();
                isSelectedLabel.setBounds(300,500,50,50);
                isSelectedLabel.setText("TESTING ONE TEW");
                this.ingameScreen.add(isSelectedLabel);
            }
        }

        JLabel backgroundImageIngame = new JLabel();
        backgroundImageIngame.setBounds(0,0,1280,720);
        backgroundImageIngame.setIcon(backgroundIngame);
        this.ingameScreen.add(backgroundImageIngame);



        repaint();
        revalidate();
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
    public static void sayPlayerAction(PlayerClass player){System.out.println(player+" pulled lever: "+player.isLeverPulled());}

    //"Rotates" the squasher
    public static void rotate(int x){
        System.out.println("The squaser has rotated "+x+" times.");
    }

    //Says who was squashed
    public static void squash(PlayerClass player){System.out.println(player+" was squashed...");}
}

