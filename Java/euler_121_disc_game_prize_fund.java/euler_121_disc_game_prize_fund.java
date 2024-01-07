public class euler_121_disc_game_prize_fund {

    public static long[][] possibleCombinations(int turns) {
        // 2D array to store the possible combinations of numbers for a number of turns
        long[][] combinations = new long[turns + 1][turns + 1];
        for (int i = 0; i <= turns; i++) {
            for (int j = 0; j <= turns; j++) {
                combinations[i][j] = 0;
            }
        }

        combinations[0][0] = 1;

        for (int i = 1; i <= turns; i++) {
            for (int j = 0; j <= i; j++) {
                long tmp = 0;
                if (j < i) {
                    tmp += combinations[i - 1][j] * i;
                }
                if (j > 0) {
                    tmp += combinations[i - 1][j - 1];
                }
                combinations[i][j] = tmp;
            }
        }


        return combinations;
    }

    // Function that calculates the factorial of a double number
    public static long factorial(long n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    // Function that calculates the prize fund
    public static String fundCalculator(long[][] combinations, int turns) {
        long possibilitiesSum = 0;
        for (int i = turns / 2 + 1; i <= turns; i++) {
            possibilitiesSum += combinations[turns][i];
        }

        long totalFactorial = factorial(turns + 1);

        String result = Long.toString(totalFactorial / possibilitiesSum);

        // Prize fund is the denominator divided by the numerator
        return result;
    }

    // Main function that simply calls the other functions
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            int turns = Integer.parseInt(args[i]);
	    if (turns < 1) {
		    System.out.println("Input must be greater than 0!");
		    break;
	    }
            long[][] result = possibleCombinations(turns);
            System.out.println("Maximum prize fund for a " + turns + " turn game: " + "£" + fundCalculator(result, turns));
        }
    }
}
