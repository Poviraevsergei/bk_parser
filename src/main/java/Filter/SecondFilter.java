package Filter;

import Model.Game;

public class SecondFilter {
    public static int f2_counter_errorRecords = 0;
    public static int f2_counter_filtered = 0;
    public static int f2_counter_dont_have_data = 0;
    public static int f2_counter_success = 0;

    public static void secondFilter(Game game) {
        try {
            if (game.isHomeBigger()) {
                if (game.isMissedChangedAway() && game.isScoreChangedAway() &&
                        game.isMissedChangedHome() && game.isScoreChangedHome()) {
                    if ((game.getScoredLastFiveMatchesHomeTeam() - game.getMissedLastFiveMatchesHomeTeam() >= 0) &&
                            (game.getScoredLastFiveMatchesAwayTeam() - game.getMissedLastFiveMatchesAwayTeam()) < -2) {
                        game.setSecondFilterComplited(true);
                        f2_counter_success++;
                    } else {
                        f2_counter_filtered++;
                    }
                }
            } else if (game.isAwayBigger()) {
                if (game.isMissedChangedAway() && game.isScoreChangedAway() &&
                        game.isMissedChangedHome() && game.isScoreChangedHome()) {
                    if ((game.getScoredLastFiveMatchesAwayTeam() - game.getMissedLastFiveMatchesAwayTeam() >= 0) &&
                            (game.getScoredLastFiveMatchesHomeTeam() - game.getMissedLastFiveMatchesHomeTeam()) < -2) {
                        game.setSecondFilterComplited(true);
                        f2_counter_success++;
                    } else {
                        f2_counter_filtered++;
                    }
                }
            } else {
                f2_counter_dont_have_data++;
            }
        } catch (Exception exception) {
            System.err.println(exception);
            f2_counter_errorRecords++;
        }
    }
}