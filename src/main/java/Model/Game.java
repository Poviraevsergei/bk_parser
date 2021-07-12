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
}
