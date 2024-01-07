public class euler_191_prize_strings {

    public static int runner(int period) {

        int[][][] dp = new int[period + 1][3][2];

        // Initializing the dp array
        for (int i = 0; i <= period; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = 0;
                }
            }
        }

        dp[0][0][0] = 1;

        // Iterating over the period
        for (int i = 0; i < period; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 2; k++) {
                    // Adding 'O'
                    dp[i + 1][0][k] += dp[i][j][k];
                    // Adding 'A'
                    if (j < 2) {
                        dp[i + 1][j + 1][k] += dp[i][j][k];
                    }
                    // Adding 'L'
                    if (k < 1) {
                        dp[i + 1][0][k + 1] += dp[i][j][k];
                    }
                }
            }
        }

        // Finding all the prize strings existing over a period
        int sum = 0;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 2; k++) {
                sum += dp[period][j][k];
            }
        }
        return sum;
    }

    // Main function takes user input and calls the function against it.
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            int period = Integer.parseInt(args[i]);
	    if (period < 1) {
		    System.out.println("Input must be greater than 0!");
		    break;
	    }
            System.out.println("There are " + runner(period) + " prize strings that exist over a " + period + " day period.");
        }
    }
}
