package Filter;

import Model.Game;

public class ThirdFilter {
    public static int f3_counter_errorRecords = 0;
    public static int f3_counter_filtered = 0;
    public static int f3_counter_dont_have_data = 0;
    public static int f3_counter_success = 0;

    public static void thirdFilter(Game game) {
        try {
            if (game.getAllScoreHome() != null && game.getAllScoreAway() != null && (game.isHomeBigger() || game.isAwayBigger())) {
                String goalsHome[] = game.getAllScoreHome().split(":");
                game.setDifferenceGoalHome(Integer.parseInt(goalsHome[0]) - Integer.parseInt(goalsHome[1]));

                String goalsAway[] = game.getAllScoreAway().split(":");
                game.setDifferenceGoalAway(Integer.parseInt(goalsAway[0]) - Integer.parseInt(goalsAway[1]));

                if (game.isHomeBigger() && game.getDifferenceGoalHome() >= 4 && game.getDifferenceGoalAway() <= -5) {
                    game.setThirdFilterComplited(true);
                    f3_counter_success++;
                } else if (game.isAwayBigger() && game.getDifferenceGoalAway() >= 4 && game.getDifferenceGoalHome() <= -5) {
                    game.setThirdFilterComplited(true);
                    f3_counter_success++;
                } else {
                    f3_counter_filtered++;
                }
            } else {
                f3_counter_dont_have_data++;
            }
        } catch (Exception exception) {
            System.err.println(exception);
            f3_counter_errorRecords++;
        }
    }
}
