package Filter;

import Model.Game;

public class ThirdFilter {
    public static void thirdFilter(Game game) {
        try {
            if (game.getAllScoreHome() != null || game.getAllScoreAway() != null) {
                String goalsHome[] = game.getAllScoreHome().split(":");
                game.setDifferenceGoalHome(Integer.parseInt(goalsHome[0]) - Integer.parseInt(goalsHome[1]));

                String goalsAway[] = game.getAllScoreAway().split(":");
                game.setDifferenceGoalAway(Integer.parseInt(goalsAway[0]) - Integer.parseInt(goalsAway[1]));

                if (game.isHomeBigger() && game.getDifferenceGoalHome() >= 4 && game.getDifferenceGoalAway() <= -5) {
                    game.setThirdFilterComplited(true);
                    System.out.println(game);
                }

                if (game.isAwayBigger() && game.getDifferenceGoalAway() >= 4 && game.getDifferenceGoalHome() <= -5) {
                    game.setThirdFilterComplited(true);
                    System.out.println(game);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception ThirdFilter");
        }
    }
}
