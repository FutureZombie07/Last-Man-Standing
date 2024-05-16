public class Main {
    public static void main(String[] args) {
        TestPlayerClass player1 = new TestPlayerClass("z","x");
        TestPlayerClass player2 = new TestPlayerClass("c","v");
        TestPlayerClass player3 = new TestPlayerClass("b","n");
        TestPlayerClass player4 = new TestPlayerClass("m",",");

        TestPlayerClass[] players = {player1, player2, player3, player4};
        GameLoop gameLoop = new GameLoop(players);
        gameLoop.run();

    }
}
