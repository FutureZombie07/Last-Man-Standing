public class PlayerClass {//This class is a placeholder
    private boolean leverPulled = false;
    private boolean squashed = false;
    private String yes = "a";
    private String no = "b";
    private String name = "";//Add code to set this to the name of the character they selected
    private boolean bot = false;

    //-----C O N S T R U C T O R S-----\\

    public PlayerClass(String yes, String no, String name){
        this.yes=yes;
        this.no=no;
        this.name=name;
    }

    public PlayerClass(String name){
        this.name=name;
        this.bot=bot;
    }

    //-----G E T T E R S   &   S E T T E R S-----\\


    public boolean isLeverPulled() {return leverPulled;}
    public void setLeverPulled(boolean leverPulled) {this.leverPulled = leverPulled;}

    public boolean isSquashed() {return squashed;}
    public void setSquashed(boolean squashed) {this.squashed = squashed;}

    public String getYes() {return yes;}
    public void setYes(String yes) {this.yes = yes;}

    public String getNo() {return no;}
    public void setNo(String no) {this.no = no;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public boolean isBot() {return bot;}
    public void setBot(boolean bot) {this.bot = bot;}
}
