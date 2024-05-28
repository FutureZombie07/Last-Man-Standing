import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class GUI extends JFrame{//This class is designed to be replaced by a GUI
    //Code to display - Will be shifted to updating something on the GUI
    //TODO: make animateevents function that is threaded and animates everything
    String[] imagepaths = new String[]{"assets/characters/Wizard_Character.png", "assets/characters/planet_character.png", "assets/characters/muffin_Character.png", "assets/characters/Knight_Character.png"};

    String[] numberpaths = new String[]{"assets/numbers/ZERO.png", "assets/numbers/ONE.png", "assets/numbers/TWO.png", "assets/numbers/THREE.png", "assets/numbers/FOUR.png", "assets/numbers/FIVE.png", "assets/numbers/SIX.png", "assets/numbers/SEVEN.png"};
    ArrayList<ImageIcon> images = new ArrayList<>();

    ArrayList<ImageIcon> numbers = new ArrayList<>();
    static Font bestFont = new Font("Monospaced", Font.PLAIN, 24);
    ImageIcon background = new ImageIcon(this.getClass().getResource("assets/titlescreenImage.png"));
    ImageIcon background2 = new ImageIcon(this.getClass().getResource("assets/characterSelectionScreenTemplate.png"));
    ImageIcon checkIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("assets/CHECKMARK.png")).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));

    ImageIcon smasherIcon = new ImageIcon(this.getClass().getResource("assets/squasher.png"));

    ImageIcon xIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("assets/XMARK.png")).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));

    ImageIcon backgroundIngame = new ImageIcon(this.getClass().getResource("assets/newbackgroundimage.png"));
    public static final int characterViewWidth = 402;
    public static final int characterViewHeight = 280;

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
            images.add(new ImageIcon(this.getClass().getResource(i)));
        }

        for (String i : numberpaths) {
            numbers.add(new ImageIcon(new ImageIcon(this.getClass().getResource(i)).getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT)));
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
            Controller.startButton(views, this);
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

    public void redrawEverything() {
        this.ingameScreen.removeAll();
        if (Controller.getGameLoop() != null) {
            //lever number
            JLabel leverNumberLabel = new JLabel();
            leverNumberLabel.setIcon(numbers.get(Animator.getCurrentSwitches()));
            leverNumberLabel.setBounds(1080, 0, 200, 200);
            this.ingameScreen.add(leverNumberLabel);

            //smasher
            JLabel smasherLabel = new JLabel();
            smasherLabel.setIcon(new ImageIcon(this.smasherIcon.getImage().getScaledInstance(400,600,Image.SCALE_DEFAULT)));
            smasherLabel.setSize(400,600);
            switch (Animator.getCurrentGUISmasherPosition()) {
                case 0:
                    smasherLabel.setLocation(85,-350);
                    break;
                case 1:
                    smasherLabel.setLocation(450,-425);
                    break;
                case 2:
                    smasherLabel.setLocation(750,-300);
                    break;
                case 3:
                    smasherLabel.setLocation(450,-350);
                    break;
                default:
                    smasherLabel.setLocation(300,0);
                    break;
            }
            if (Animator.isSmasherSmashing()) {
                smasherLabel.setLocation(smasherLabel.getX(), smasherLabel.getY()+ 400);
            }
            this.ingameScreen.add(smasherLabel);
            //player 4
            if (!Controller.getGameLoop().getPlayers()[3].isSquashed()) {
                PlayerClass player = Controller.getGameLoop().getPlayers()[3];
                JLabel playerLabel = new JLabel();
                playerLabel.setBounds(530,380,250,250);
                playerLabel.setIcon(player.getPlayerImage());
                this.ingameScreen.add(playerLabel);

                JLabel isSelectedLabel = new JLabel();
                isSelectedLabel.setBounds(750,580,100,100);
                if (player.isSelected()) {
                    isSelectedLabel.setIcon(checkIcon);
                } else {
                    isSelectedLabel.setIcon(xIcon);
                }

                this.ingameScreen.add(isSelectedLabel);

                JLabel playerNameLabel = new JLabel(player.getName());
                playerNameLabel.setForeground(new Color(255, 255, 255));
                playerNameLabel.setBackground(new Color(0, 0, 0));
                playerNameLabel.setOpaque(true);
                playerNameLabel.setFont(bestFont);
                playerNameLabel.setBounds(550, 630, 200,40);
                this.ingameScreen.add(playerNameLabel);

            }
            //player 1
            if (!Controller.getGameLoop().getPlayers()[0].isSquashed()) {
                PlayerClass player = Controller.getGameLoop().getPlayers()[0];
                JLabel playerLabel = new JLabel();
                playerLabel.setBounds(160,190,250,250);
                playerLabel.setIcon(player.getPlayerImage());
                this.ingameScreen.add(playerLabel);

                JLabel isSelectedLabel = new JLabel();
                isSelectedLabel.setBounds(135,550,100,100);
                if (player.isSelected()) {
                    isSelectedLabel.setIcon(checkIcon);
                } else {
                    isSelectedLabel.setIcon(xIcon);
                }

                this.ingameScreen.add(isSelectedLabel);

                JLabel playerNameLabel = new JLabel(player.getName());
                playerNameLabel.setForeground(new Color(255, 255, 255));
                playerNameLabel.setBackground(new Color(0, 0, 0));
                playerNameLabel.setOpaque(true);
                playerNameLabel.setFont(bestFont);
                playerNameLabel.setBounds(225, 580, 200,40);
                this.ingameScreen.add(playerNameLabel);
            }
            //player2
            if (!Controller.getGameLoop().getPlayers()[1].isSquashed()) {
                PlayerClass player = Controller.getGameLoop().getPlayers()[1];
                JLabel playerLabel = new JLabel();
                playerLabel.setBounds(530,160,250,250);
                playerLabel.setIcon(player.getPlayerImage());
                this.ingameScreen.add(playerLabel);

                JLabel isSelectedLabel = new JLabel();
                isSelectedLabel.setBounds(750,350,100,100);
                if (player.isSelected()) {
                    isSelectedLabel.setIcon(checkIcon);
                } else {
                    isSelectedLabel.setIcon(xIcon);
                }

                this.ingameScreen.add(isSelectedLabel);
            }
            //player 3
            if (!Controller.getGameLoop().getPlayers()[2].isSquashed()) {
                PlayerClass player = Controller.getGameLoop().getPlayers()[2];
                JLabel playerLabel = new JLabel();
                playerLabel.setBounds(830,290,250,250);
                playerLabel.setIcon(player.getPlayerImage());
                this.ingameScreen.add(playerLabel);

                JLabel isSelectedLabel = new JLabel();
                isSelectedLabel.setBounds(1050,550,100,100);
                if (player.isSelected()) {
                    isSelectedLabel.setIcon(checkIcon);
                } else {
                    isSelectedLabel.setIcon(xIcon);
                }

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


