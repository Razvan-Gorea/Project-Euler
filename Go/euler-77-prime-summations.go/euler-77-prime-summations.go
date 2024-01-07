package main

import (
	"fmt"
	"os"
	"strconv"
)

// function to find the possible ways to create a number as a sum of primes
func finder(limit, target int) int {
	partitions := make([]int, limit)
	partitions[0] = 1

	for i := 0; i < len(partitions); i++ {
		if !isPrime(i) {
			continue
		}

		for j := i; j < len(partitions); j++ {
			partitions[j] += partitions[j-i]
		}
	}

	for i := 0; i < limit; i++ {
		if partitions[i] > target {
			return i
		}
	}

	return -1
}

// function to check if a number is prime
func isPrime(n int) bool {
	//prime checkers
	if n <= 1 {
		return false
	}

	if n == 2 || n == 3 {
		return true
	}

	if n%2 == 0 || n%3 == 0 {
		return false
	}

	for i := 5; i*i <= n; i += 6 {
		if n%i == 0 || n%(i+2) == 0 {
			return false
		}
	}

	return true
}

// main function
func main() {
	args := os.Args

	for i := 1; i < len(args); i++ {
		n, _ := strconv.Atoi(args[i])
		var str string
		for limit := 1; ; limit *= 2 {
			result := finder(limit, n)
			if result != -1 {
				str = fmt.Sprintf("%d", result)
				break
		}
	}
	fmt.Println(str, "is the first sum of primes in over", n, "different ways.")
}
}
