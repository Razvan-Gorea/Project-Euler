import java.util.ArrayList;
import java.util.List;

public class euler_50_consecutive_prime_sum {

	// Function checks whether n is prime. Returns true if n is prime.
    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    // Sieve creates a list of primes below n.
    private static List<Integer> makePrimes(int n) {
        boolean[] notPrimes = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!notPrimes[i]) {
                primes.add(i);
                for (int j = i * i; j <= n; j += i) {
                    notPrimes[j] = true;
                }
            }
        }
        return primes;
    }

    // Function essentially itterates over starting points in our primes array (above) and finds the longest sequence it can make of primes before sum > n. Then moves the start index over by 1.
    private static int[] consecutivePrimeSum(int n) {
        int maxConsPrime = 2;
        int longestPrimeSeq = 0;
        List<Integer> primes = makePrimes(n);
        for (int currStart = 0; currStart < primes.size(); currStart++) {
            int sum = 0;
            for (int i = currStart; i < primes.size(); i++) {
                sum += primes.get(i);
                if (sum > n) {
                    break;
                }
                if (isPrime(sum) && i - currStart + 1 > longestPrimeSeq) {
                    longestPrimeSeq = i - currStart + 1;
                    maxConsPrime = sum;
                }
            }
        }
        return new int[]{maxConsPrime, longestPrimeSeq};
    }

    // Main function takes user input and calls our function against it.
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            int n = Integer.parseInt(args[i]);
	    if (n < 2) {
		    System.out.println("Input must be greater than 1!");
		    continue;
	    }
            int[] result = consecutivePrimeSum(n);
            System.out.println("The largest consecutive prime sum below " + n + " is " + result[0]);
        }
    }
}
