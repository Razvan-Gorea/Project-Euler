import java.util.HashMap;
import java.util.Map;

public class euler_196_prime_triplets {

    // Is prime function, returns true if n is prime.
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }
        return true;
    }

    // Creates the triangle, is a map between [firstdigit][lastdigit] in our triangle per row. Row number is the index of the map.
    public static Map<Integer, int[]> generateTriangle(int n) {
        Map<Integer, int[]> triangle = new HashMap<>();
        int start = 1;

        for (int i = 1; i <= n; i++) {
            int end = start + i - 1;
            triangle.put(i, new int[]{start, end});
            start = end + 1;
        }

        return triangle;
    }

    // Returns the values in a list given a row.
    public static int[] getRowValues(int[] row) {
        int start = row[0];
        int end = row[1];
        int[] values = new int[end - start + 1];
        for (int i = 0; i < values.length; i++) {
            values[i] = start + i;
        }
        return values;
    }

    // Returns a matrix of the neighbours within a 3x3 grid of a given number, provided the row number and the triangle itself.
    public static int[][] findNeighbours(int row, int num, Map<Integer, int[]> triangle) {
        int[][] neighbours = new int[0][];
        int[] currentRowValues = getRowValues(triangle.get(row));
        int[] prevRowValues = (row > 1) ? getRowValues(triangle.get(row - 1)) : new int[0];
        int[] nextRowValues = (row < triangle.size()) ? getRowValues(triangle.get(row + 1)) : new int[0];

        int pos = -1;
        for (int i = 0; i < currentRowValues.length; i++) {
            if (currentRowValues[i] == num) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            return neighbours;
        }

        // This part appends each neighbour found within the 3x3 grid.
        if (pos > 0) {
            neighbours = append(neighbours, new int[]{currentRowValues[pos - 1], row});
        }
        if (pos < currentRowValues.length - 1) {
            neighbours = append(neighbours, new int[]{currentRowValues[pos + 1], row});
        }
        if (prevRowValues.length > 0) {
            if (pos < prevRowValues.length) {
                neighbours = append(neighbours, new int[]{prevRowValues[pos], row - 1});
            }
            if (pos < prevRowValues.length - 1) {
                neighbours = append(neighbours, new int[]{prevRowValues[pos + 1], row - 1});
            }
            if (pos - 1 >= 0) {
                neighbours = append(neighbours, new int[]{prevRowValues[pos - 1], row - 1});
            }
        }
        if (nextRowValues.length > 0) {
            if (pos - 1 >= 0) {
                neighbours = append(neighbours, new int[]{nextRowValues[pos - 1], row + 1});
            }
            neighbours = append(neighbours, new int[]{nextRowValues[pos], row + 1});
            neighbours = append(neighbours, new int[]{nextRowValues[pos + 1], row + 1});
        }
        return neighbours;
    }

    // Primary heavy lifting function, calls the findNeighbours function singlely recursively to find both the neighbours of node n ki and neighbours of node ki.
    public static boolean isPrimeTriplet(int row, int n, Map<Integer, int[]> triangle) {
        int[][] neighbours = findNeighbours(row, n, triangle);
        int count = 1;
        for (int[] neighbour : neighbours) {
            if (isPrime(neighbour[0])) {
                count++;
                int[][] secondNeighbours = findNeighbours(neighbour[1], neighbour[0], triangle);
                for (int[] secondNeighbour : secondNeighbours) {
                    if (secondNeighbour[0] != n && isPrime(secondNeighbour[0])) {
                        count++;
                    }
                }
            }
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }

    // Function which sums each of the numbers in the row which pass the above test.
    public static int sumPrimeTriplets(int n) {
        int sum = 0;
        Map<Integer, int[]> triangle = generateTriangle(n + 2);
        int[] values = getRowValues(triangle.get(n));
        for (int v : values) {
            if (isPrime(v)) {
                if (isPrimeTriplet(n, v, triangle)) {
                    sum += v;
                }
            }
        }
        return sum;
    }

    // Main function takes user input and calls the function against it.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
	if (n < 1) {
		System.out.println("Input must be greater than 0!");
		return;
	}
        System.out.println(sumPrimeTriplets(n));
    }

    private static int[][] append(int[][] array, int[] element) {
        int[][] newArray = new int[array.length + 1][];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = element;
        return newArray;
    }
}
