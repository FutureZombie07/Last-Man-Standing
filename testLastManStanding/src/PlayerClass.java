import javax.swing.*;

public class PlayerClass {//This class is a placeholder
    private Boolean leverPulled = null;
    private boolean squashed = false;
    private char yes = 'a';
    private char no = 'b';
    private String name = "";//Add code to set this to the name of the character they selected
    private boolean bot = false;
    private ImageIcon playerImage;

    private boolean selected = false;


    //-----C O N S T R U C T O R S-----\\

    public PlayerClass(char yes, char no, String name){
        this.yes=yes;
        this.no=no;
        this.name=name;
        this.playerImage = playerImage;
    }

    public PlayerClass(String name){
        this.name=name;
        this.bot=bot;
    }

    public PlayerClass(String name, ImageIcon playerImage, char yes, char no) {
        this.name = name;
        this.playerImage = playerImage;
        this.yes = yes;
        this.no = no;
    }

    //-----G E T T E R S   &   S E T T E R S-----\\


    public boolean isLeverPulled() {return leverPulled;}
    public void setLeverPulled(boolean leverPulled) {this.leverPulled = leverPulled;}

    public boolean isSquashed() {return squashed;}
    public void setSquashed(boolean squashed) {this.squashed = squashed;}

    public char getYes() {return yes;}
    public void setYes(char yes) {this.yes = yes;}

    public char getNo() {return no;}
    public void setNo(char no) {this.no = no;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public boolean isBot() {return bot;}
    public void setBot(boolean bot) {this.bot = bot;}

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ImageIcon getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(ImageIcon playerImage) {
        this.playerImage = playerImage;
    }
}