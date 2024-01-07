import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class euler_35_circular_primes {

   /*  Method that checks if a number is a prime  */
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

   /*  Method that generates all the combinations of a number  */
   private static List<Integer> generateCombinations(int n) {
       String str = Integer.toString(n);
       List<Integer> combinations = new ArrayList<>();
       for (int i = 0; i < str.length(); i++) {
           String rotated = str.substring(i) + str.substring(0, i);
           combinations.add(Integer.parseInt(rotated));
       }
       return combinations;
   }

    /*  Method that checks if a number is a circular prime  */
   private static boolean isCircularPrime(int prime) {
       List<Integer> combinations = generateCombinations(prime);
       for (int n : combinations) {
           if (!isPrime(n)) {
               return false;
           }
       }
       return true;
   }

    /*  Method that returns the number of circular primes below a limit  */
   public static int circularPrimes(int limit) {
       List<Integer> cPrimes = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
       for (int i = 11; i < limit; i += 2) {
           String str = Integer.toString(i);
           if (str.charAt(str.length() - 1) == '5') {
               continue;
           }
           boolean isCircular = true;
           for (char c : str.toCharArray()) {
               if ((c - '0') % 2 == 0) {
                  isCircular = false;
                  break;
               }
           }
           if (isCircular && isPrime(i) && isCircularPrime(i)) {
               cPrimes.add(i);
           }
       }
       return cPrimes.size();
   }

   /* Arguements from the commandline become input for the program */
   public static void main(String[] args) {
       for (String arg : args) {
	   if (Integer.parseInt(arg) <= 0) {
		   System.out.println("Input must be greater than 0!");
		   continue;
	   }
           int n = Integer.parseInt(arg);
           System.out.println("There are " + circularPrimes(n) + " circular primes below " + n );
       }
   }
}
