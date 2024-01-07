package main

import (
	"fmt"
	"os"
	"strconv"
)

// Checks if a number is prime.
func isPrime(n int) bool {
	if n <= 1 {
		return false
	}
	if n == 2 || n == 3 {
		return true
	}
	if n%2 == 0 || n%3 == 0 {
		return false
	}
	// Special for loop here minimises delay.
	for i := 5; i*i <= n; i += 6 {
		if n%i == 0 || n%(i+2) == 0 {
			return false
		}
	}
	return true
}

// Generates all unique rotations of the digits in n.
func generateCombinations(n int) []int {
	str := strconv.Itoa(n)
	combinations := []int{}
	// Cyclic permutations. Appends to list then returned.
	for i := 0; i < len(str); i++ {
		num, _ := strconv.Atoi(str[i:] + str[:i])
		combinations = append(combinations, num)
	}
	return combinations
}

// Dirty function -> calls the generateCombinations function.
func isCircularPrime(prime int) bool {
	// Boils down to this: If the number AND ALL digit rotations of that number (e.g 213, 132, 321) are prime, then it passes this test, and returns true.
	for _, n := range generateCombinations(prime) {
		if !isPrime(n) {
			return false
		}
	}
	return true
}

// Function which counts the circular primes from 0 -> limit
func circularPrimes(limit int) int {
	// We can initialize our set of circulars with the one digit primes as digit arrangement checks are redundant.
	cPrimes := []int{2, 3, 5, 7}
	// For loop which runs over every odd number above 10 and eliminates those numbers which are multipes of 5.
	for i := 11; i < limit; i += 2 {
		str := strconv.Itoa(i)
		// "If the last digit is 5, skip this number"
		if str[len(str)-1] == '5' {
			continue
		}
		isCircular := true
		// Itterate over each character in the stringified number and check if its even.(This can catch numbers like 223 which would pass the test above.)
		for _, c := range str {
			if (c-'0')%2 == 0 {
				isCircular = false
				break
			}
		}
		// We only do this check on a small number of numbers, first the numbers which passed the above tests, then test if their prime, and a tiny minority of numbers make it to the 3rd check, which is where the heavy computation lies.
		if isCircular && isPrime(i) && isCircularPrime(i) {
			cPrimes = append(cPrimes, i)
		}
	}
	return len(cPrimes)
}

func main() {
	args := os.Args
	for i := 1; i < len(args); i++ {
		n, _ := strconv.Atoi(args[i])
		if n < 1 {
			fmt.Println("Please input a number above 0")
		}
		// Prints the number of circular primes below point n.
		fmt.Println("The number of circular primes below", n, "is:", circularPrimes(n))
	}
}
