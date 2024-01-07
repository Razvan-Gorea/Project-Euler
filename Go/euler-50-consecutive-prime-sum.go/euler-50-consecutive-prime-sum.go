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
	for i := 5; i*i <= n; i += 6 {
		if n%i == 0 || n%(i+2) == 0 {
			return false
		}
	}
	return true
}

// Sieve of Eratosthenes
func makePrimes(n int) []int {
	// Creates a list of length n, default values 0, or false.
	notPrimes := make([]bool, n+1)
	primes := []int{}
	// Goes through every number and if that number is false in our list, it appends it to the primes array, and marks each multiple of that number as notPrime. This gets em all surpisingly.
	for i := 2; i <= n; i++ {
		if !notPrimes[i] {
			primes = append(primes, i)
			// e.g. 2 -> marked as prime, then 4 marked as non-prime, then 6 marked as non-prime, then 8...etc
			for j := i * i; j <= n; j += i {
				notPrimes[j] = true
			}
		}
	}
	return primes
}

// This function does all the dirty stuff. It essentially tries to find the longest sequence of consecutive prime numbers which themselves add up to primes. Returns the sum and its length.
func consecutivePrimeSum(n int) (int, int) {
	maxConsPrime := 2
	longestPrimeSeq := 0
	// Here we have a list of primes generated by our sieve.
	primes := makePrimes(n)
	// For loop essentially boils down to: Start at 0 -> is the sum of primes starting here until the sum is bigger than n longer than the last start point? No? Start + 1 and go again. Yes? Save it and keep checking.
	for currStart := 0; currStart < len(primes); currStart++ {
		sum := 0
		// Start here and add all the primes up consecutively. This function breaks when the sum is larger then n, but if its smaller, it will check against our conditions below.
		for i := currStart; i < len(primes); i++ {
			sum += primes[i]
			if sum > n {
				break
			}
			// Is the sum of this promising chain prime, AND longer than the last longest chain? Then overwrite it.
			if isPrime(sum) && i-currStart+1 > longestPrimeSeq {
				longestPrimeSeq = i - currStart + 1
				maxConsPrime = sum
			}
		}
	}
	return maxConsPrime, longestPrimeSeq
}

func main() {
	args := os.Args[1:]
	for i := 0; i < len(args); i++{
	n, _ := strconv.Atoi(args[i])
	if n < 2 {
		fmt.Println("Input must be greater than 1!")
		return
	}
	primeSum, _ := consecutivePrimeSum(n)
	fmt.Println("The largest consecutive prime sum below", n, "is", primeSum)
	}
}