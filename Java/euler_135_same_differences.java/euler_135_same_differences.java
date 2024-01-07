public class euler_135_same_differences {

    // function which takes a given n limit and finds the solutions which occur 10 times and returns the count of how many it found..
    private static int findSolutions(int n) {
        int[] solutions = new int[n];
        for (int x = 1; x < n * 2; x++) {
            for (int d = x / 5 + 1; d < (x + 1) / 2; d++) {
                int tmp = (x - d) * (d * 5 - x);
                if (tmp >= n) {
                    break;
                }
                solutions[tmp]++;
            }
        }
        int answer = 0;
        for (int count : solutions) {
            if (count == 10) {
                answer++;
            }
        }
        return answer;
    }
    
    // Main function takes user input and calls the function against it.
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            int n = Integer.parseInt(args[i]);
            System.out.println("There are " + findSolutions(n) + " values less than " + n + " that have exactly ten distinct solutions");
        }
    }
}
