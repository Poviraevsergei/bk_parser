package Filter;

import Model.Game;

public class FirstFilter {
    public static int f1_counter_errorRecords = 0;
    public static int f1_counter_filtered = 0;
    public static int f1_counter_dontHavePlace = 0;
    public static int f1_counter_success = 0;

    public static Game firstFilter(Game game) {
        try {
            if (game.getHomePlaceInTheTable() != null && game.getAwayPlaceInTheTable() != null) {
                if (Integer.valueOf(game.getHomePlaceInTheTable()) - Integer.valueOf(game.getAwayPlaceInTheTable()) >= 10) {
                    game.setFirstFilterComplited(true);
                    game.setHomeBigger(true);
                    f1_counter_success++;
                } else if (Integer.valueOf(game.getAwayPlaceInTheTable()) - Integer.valueOf(game.getHomePlaceInTheTable()) >= 10) {
                    game.setFirstFilterComplited(true);
                    game.setAwayBigger(true);
                    f1_counter_success++;
                } else {
                    f1_counter_filtered++;
                }
            } else {
                f1_counter_dontHavePlace++;
            }
        } catch (Exception exception) {
            System.err.println(exception);
            f1_counter_errorRecords++;
        }
        return game;
    }
}