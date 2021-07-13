package Model;

import lombok.Data;

@Data
public class Game {
    private String homeTeam;
    private String awayTeam;
    private String gameTime;
    private String id;
    private String homePlaceInTheTable;
    private String awayPlaceInTheTable;
    private boolean firstFilterComplited;
    private boolean secondFilterComplited;
    private boolean thirdFilterComplited;
    private boolean homeBigger;
    private boolean awayBigger;
    private int scoredLastFiveMatchesHomeTeam;
    private int missedLastFiveMatchesHomeTeam;
    private int scoredLastFiveMatchesAwayTeam;
    private int missedLastFiveMatchesAwayTeam;
    private int scoredAllMatchesHomeTeam;
    private int missedAllMatchesHomeTeam;
    private int scoredAllMatchesAwayTeam;
    private int missedAllMatchesAwayTeam;
    private boolean missedChangedHome;
    private boolean missedChangedAway;
    private boolean scoreChangedHome;
    private boolean scoreChangedAway;
}
