package Filter;

import Model.Game;

public class SecondFilter {
    public static void secondFilter(Game game) {

        if (game.isHomeBigger()) {
            if (game.isMissedChangedAway() && game.isScoreChangedAway() &&
                    game.isMissedChangedHome() && game.isScoreChangedHome()) {
                if ((game.getScoredLastFiveMatchesHomeTeam() - game.getMissedLastFiveMatchesHomeTeam() >= 0) &&
                        (game.getScoredLastFiveMatchesAwayTeam() - game.getMissedLastFiveMatchesAwayTeam()) < -2) {
                    game.setSecondFilterComplited(true);
                    System.out.println(game);
                }
            }
        }

        if (game.isAwayBigger()) {
            if (game.isMissedChangedAway() && game.isScoreChangedAway() &&
                    game.isMissedChangedHome() && game.isScoreChangedHome()) {
                if ((game.getScoredLastFiveMatchesAwayTeam() - game.getMissedLastFiveMatchesAwayTeam() >= 0) &&
                        (game.getScoredLastFiveMatchesHomeTeam() - game.getMissedLastFiveMatchesHomeTeam()) < -2) {
                    game.setSecondFilterComplited(true);
                    System.out.println(game);
                }
            }
        }
    }
}