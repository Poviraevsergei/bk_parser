package Filter;

import Model.Game;

import java.util.ArrayList;
import java.util.List;

public class ThirdFilter {
    public static List<Game> thirdFilter(List<Game> games) {
        List<Game> resultGames = new ArrayList<>();
        for (Game game:games) {
            String goalsHome[] = game.getAllScoreHome().split(":");
            game.setDifferenceGoalHome(Integer.parseInt(goalsHome[0]) - Integer.parseInt(goalsHome[1]));

            String goalsAway[] = game.getAllScoreAway().split(":");
            game.setDifferenceGoalAway(Integer.parseInt(goalsAway[0]) - Integer.parseInt(goalsAway[1]));

            if (game.isHomeBigger() && game.getDifferenceGoalHome() >= 4 && game.getDifferenceGoalAway() <= -5) {
                game.setThirdFilterComplited(true);
                resultGames.add(game);
            }

            if (game.isAwayBigger() && game.getDifferenceGoalAway() >= 4 && game.getDifferenceGoalHome() <= -5) {
                game.setThirdFilterComplited(true);
                resultGames.add(game);
            }
        }
        return resultGames;
    }
}
