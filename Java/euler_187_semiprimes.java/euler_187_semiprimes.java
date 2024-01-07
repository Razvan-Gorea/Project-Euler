import java.util.Arrays;

public class euler_187_semiprimes {

    // Function that takes a number and checks if it's prime
    static boolean isPrime(int n) {
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

    // Function that generates all primes up to n
    static int[] generatePrimes(int n) {
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);

        primes[0] = false;
        primes[1] = false;
        for (int p = 2; p * p <= n; p++) {
            if (primes[p]) {
                for (int i = p * p; i <= n; i += p) {
                    primes[i] = false;
                }
            }
        }

        // list `result` will contain all prime numbers
        int count = 0;
        for (boolean prime : primes) {
            if (prime) {
                count++;
            }
        }

        int[] result = new int[count];
        int index = 0;
        for (int i = 0; i < primes.length; i++) {
            if (primes[i]) {
                result[index++] = i;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Feeding the input from arguments to the program
        for (int i = 0; i < args.length; i++) {
            int n = Integer.parseInt(args[i]);
	    if (n < 1) {
		    System.out.println("Input must be greater than 0!");
		    break;
	    }
            int[] primes = generatePrimes((int) Math.sqrt(n));
            boolean[] nums = new boolean[n];

            // Sieve to find all semiprimes
            for (int j = 0; j < nums.length; j++) {
                if (!nums[j] && isPrime(j)) {
                    for (int prime : primes) {
                        int index = j * prime;
                        if (index <= nums.length) {
                            nums[index] = true;
                        }
                    }
                }
            }

            // Counter setup to count the number of semiprimes
            int count = 0;
            for (boolean num : nums) {
                if (num) {
                    count++;
                }
            }
            System.out.println(count + " semiprimes below " + n);
        }
    }
}
