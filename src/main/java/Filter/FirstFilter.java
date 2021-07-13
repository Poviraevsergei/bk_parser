package Filter;

import Model.Game;

public class FirstFilter {

    public static void firstFilter(Game game) {
        if (game.getHomePlaceInTheTable() != null && game.getAwayPlaceInTheTable() != null) {
            if (Integer.valueOf(game.getHomePlaceInTheTable()) - Integer.valueOf(game.getAwayPlaceInTheTable()) >= 10) {
                game.setFirstFilterComplited(true);
                game.setHomeBigger(true);
                System.out.println(game);
            }
            if (Integer.valueOf(game.getAwayPlaceInTheTable()) - Integer.valueOf(game.getHomePlaceInTheTable()) >= 10) {
                game.setFirstFilterComplited(true);
                game.setAwayBigger(true);
                System.out.println(game);
            }
        }
    }
}