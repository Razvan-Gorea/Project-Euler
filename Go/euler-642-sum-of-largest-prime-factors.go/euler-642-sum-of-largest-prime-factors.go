package main

import (
	"fmt"
    "os"
    "strconv"
)

// Returns if a number is a power of 2. Means that the largest prime factor is 2.
func isPowerOfTwo(n int) bool {
	return n != 0 && (n&(n-1)) == 0
 }

 // Function to find the largest prime factor of n.
func findLargestPrimeFactor(n int) int {

    for n%2 == 0 {
		if isPowerOfTwo (n) {
			return 2
		}
        n = n / 2
    }

    if n == 1 {
        return 2
    }

	var i int
    for i = 3; i*i <= n; i += 2 {
        for n%i == 0 {
            n = n / i
        }
    }

    if n > 2 {
        return n
    }

    return i - 2
}

// Function which runs our function over i <= n
func sumLargestPrimeFactors(n int) int {
    var i, sum int
    for i = 2; i <= n; i++ {
        sum += findLargestPrimeFactor(i)
    }
    return sum
}

func main() {
    args := os.Args

    for i := 1; i < len(args); i++ {
        n, _ := strconv.Atoi(args[i])
        fmt.Println(sumLargestPrimeFactors(n) % 1000000000)
}
}