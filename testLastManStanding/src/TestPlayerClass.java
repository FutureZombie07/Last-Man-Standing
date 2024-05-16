public class TestPlayerClass {//This class is a placeholder
    private boolean leverPulled = false;
    private boolean squashed = false;
    private String yes = "a";
    private String no = "b";

    //-----C O N S T R U C T O R S-----\\

    public TestPlayerClass(String yes, String no){
        this.yes=yes;
        this.no=no;
    }

    public TestPlayerClass(){}

    //-----G E T T E R S   &   S E T T E R S-----\\


    public boolean isLeverPulled() {return leverPulled;}
    public void setLeverPulled(boolean leverPulled) {this.leverPulled = leverPulled;}

    public boolean isSquashed() {return squashed;}
    public void setSquashed(boolean squashed) {this.squashed = squashed;}

    public String getYes() {return yes;}
    public void setYes(String yes) {this.yes = yes;}

    public String getNo() {return no;}
    public void setNo(String no) {this.no = no;}
}
