public class euler_77_prime_summations {

    // function to find the possible ways to create a number as a sum of primes
    private static int finder(int limit, int target) {
        int[] partitions = new int[limit];
        partitions[0] = 1;

        for (int i = 0; i < partitions.length; i++) {
            if (!isPrime(i)) {
                continue;
            }

            for (int j = i; j < partitions.length; j++) {
                partitions[j] += partitions[j - i];
            }
        }

        for (int i = 0; i < limit; i++) {
            if (partitions[i] > target) {
                return i;
            }
        }

        return -1;
    }

    // function to check if a number is prime
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

    // main function
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            int n = Integer.parseInt(args[i]);
            String str = "";
            for (int limit = 1; ; limit *= 2) {
                int result = finder(limit, n);
                if (result != -1) {
                    str = Integer.toString(result);
                    break;
                }
            }
            System.out.println(str + " is the first value of the sum of primes in over " + n + " different ways.");
        }
    }
}
