package Filter;

import Model.Game;

public class FourthFilter {
    public static int f4_counter_TT = 0;
    public static int f4_counter_didnt_coefficient = 0;
    public static int f4_counter_errorRecords = 0;
    public static int f4_counter_filtered = 0;
    public static int f4_counter_success = 0;

    public static void checkCoefficient(Game game) {
        try {
            if ((game.isAwayBigger() || game.isHomeBigger())) {
                if (game.getCoefficients().size() != 0) {
                    for (String text : game.getCoefficients()) {
                        text = text.replace("[", "").replace("]", "");
                        String home = "0.0";
                        String away = "0.0";
                        String lines[] = text.split(", ");
                        for (String line : lines) {
                            String[] coefficients = line.split(" \\| ");
                            for (int i = 0; i < coefficients.length; i++) {
                                if (coefficients[i].length() != 4 && coefficients[i].length() != 5) {
                                    String temp[] = coefficients[i].split(" » ");
                                    coefficients[i] = temp[1];
                                }
                            }
                            home = coefficients[0];
                            away = coefficients[2];
                        }
                        if (game.isHomeBigger() && Double.parseDouble(home) >= 1.5) {
                            game.setFourthFilterComplited(true);
                        } else if (game.isAwayBigger() && Double.parseDouble(away) >= 1.5) {
                            game.setFourthFilterComplited(true);
                        }
                    }
                    if (!game.isFourthFilterComplited()){
                        f4_counter_filtered++;
                    }
                } else {
                    f4_counter_didnt_coefficient++;
                }
            } else {
                f4_counter_TT++;
            }
            if (game.isFourthFilterComplited()) {
                f4_counter_success++;
            }
        } catch (Exception exception) {
            System.err.println(exception);
            f4_counter_errorRecords++;
        }
    }
}
