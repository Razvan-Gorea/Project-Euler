public class euler_642_sum_of_largest_prime_factors {

    // Returns if a number is a power of 2. Means that the largest prime factor is 2.
    private static boolean isPowerOfTwo(int n) {
        return n != 0 && (n & (n - 1)) == 0;
    }

    // Function to find the largest prime factor of n.
    private static int findLargestPrimeFactor(int n) {
        while (n % 2 == 0) {
            if (isPowerOfTwo(n)) {
                return 2;
            }
            n = n / 2;
        }

        if (n == 1) {
            return 2;
        }

        int i;
        for (i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                n = n / i;
            }
        }

        if (n > 2) {
            return n;
        }

        return i - 2;
    }

    // Function which runs our function over i <= n
    private static int sumLargestPrimeFactors(int n) {
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum += findLargestPrimeFactor(i);
        }
        return sum;
    }

    // main function which calls our function against user input and pints the modules 10^9.
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            int n = Integer.parseInt(args[i]);
            System.out.println(sumLargestPrimeFactors(n) % 1000000000);
        }
    }
}
