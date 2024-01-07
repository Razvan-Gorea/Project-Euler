import java.util.Arrays;

public class euler_41_pandigital_prime {

    // Sieve creates primes and appends only those within our range (below)
    public static int[] makePrimes(int from, int to) {
        boolean[] notPrimes = new boolean[to + 1];
        int[] primes = new int[0];

        for (int i = 2; i <= to; i++) {
            if (!notPrimes[i]) {
                for (int j = i * i; j <= to; j += i) {
                    if (j > 0 && j <= to) {
                        notPrimes[j] = true;
                    }
                }
            }
        }

        for (int i = from; i <= to; i++) {
            if (!notPrimes[i]) {
                primes = Arrays.copyOf(primes, primes.length + 1);
                primes[primes.length - 1] = i;
            }
        }

        return primes;
    }

    // function which checks if a number is pandigital
    public static boolean pandigitalChecker(int n, int numDigit) {
        boolean[] digitCounts = new boolean[numDigit + 1];
        digitCounts[0] = true;
        String str = Integer.toString(n);

        // A number can't be pandigital below n if the amount of digits in the number is > n.
        // So we discard them.
        if (str.length() != numDigit) {
            return false;
        }

        // Iterates over each digit in the now stringified number.
        for (char digit : str.toCharArray()) {
            int d = Character.getNumericValue(digit);

            // We can also discard any numbers whose digits are greater than n.
            // E.g 4 digit pandigital will not have a 5 in it.
            if (d > numDigit) {
                return false;
            }

            // Checks for duplication.
            if (digitCounts[d]) {
                return false;
            }

            digitCounts[d] = true;
        }

        // Our pandigital numbers must contain one of each and exactly one of each digit
        // equal and less than n. E.g 134 is not pandigital, as 2 is missing, and therefore false in our array.
        for (int i = 0; i < digitCounts.length - 1; i++) {
            if (!digitCounts[i]) {
                return false;
            }
        }

        return true;
    }

    // Quick and dirty function I made that can find the min and max values for our range given n.
    // E.g n = 4 will output 999, 9999. This reduces time massively, especially for bigger n's.
    public static int[] minAndMaxNumberFinder(int n) {
        String smaller = "9".repeat(n - 1);
        String bigger = smaller + "9";
        int min = Integer.parseInt(smaller);
        int max = Integer.parseInt(bigger);
        return new int[]{min, max};
    }

    // Uses the makePrimes function given a range (above) to quickly find primes within our list,
    // then calls the pandigitalChecker function on them and returns the one (largest [i--]) which passes.
    public static int getLargestPandigitalPrime(int n) {
        int[] range = minAndMaxNumberFinder(n);
        int[] primes = makePrimes(range[0], range[1]);

        for (int i = primes.length - 1; i >= 0; i--) {
            if (pandigitalChecker(primes[i], n)) {
                return primes[i];
            }
        }

        return 0;
    }

    // Main function which takes user arguments and calls our function against it.
    public static void main(String[] args) {
        int argInt = Integer.parseInt(args[0]);
	if (argInt < 1) {
		System.out.println("Input must be greater than 0!");
		return;
	}
        int result = getLargestPandigitalPrime(argInt);
        if (result == 0) {
            System.out.println("There is no " + argInt + " digit pandigital prime.");
        } else {
            System.out.println("The largest " + argInt + " digit pandigital prime is " + result);
            return;
        }
    }
   
}
