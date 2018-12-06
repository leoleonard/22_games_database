public class Game {
    private int ID;
    private String name;
    private String producent;
    private String type;
    private int ranking;

    public Game(int ID, String name, String producent, String type, int ranking) {
        this.ID = ID;
        this.name = name;
        this.producent = producent;
        this.type = type;
        this.ranking = ranking;
    }

    public Game() {

    }
}
