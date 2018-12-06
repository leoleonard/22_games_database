import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    private List<Game> games = new ArrayList<>();

    public void add(Game game) {
        games.add(game);
    }
}
