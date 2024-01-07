package main

import (
	"fmt"
	"os"
	"strconv"
	"strings"
)

// Sieve creates primes and appends only those within our range (below)
func makePrimes(from int, to int) []int {
	notPrimes := make([]bool, to+1)
	primes := []int{}
	for i := 2; i <= to; i++ {
		if !notPrimes[i] {
			for j := i * i; j <= to; j += i {
				notPrimes[j] = true
			}
		}
	}
	for i := from; i <= to; i++ {
		if !notPrimes[i] {
			primes = append(primes, i)
		}
	}
	return primes
}

// function which checks if a number is pandigital -> Simply put, it checks if any of the digits in the number are 0, duplicate, or bigger than what we desire.
func pandigitalChecker(n int, numDigit int) bool {
	digitCounts := make([]bool, numDigit+1)
	digitCounts[0] = true
	str := fmt.Sprintf("%d", n)
	// A number cant be pandigital below n if the amount of digits in the number is > n. So we discard them.
	if len(str) != numDigit {
		return false
	}

	// Itterates over each digit in the now stringified number.
	for _, digit := range str {
		d := int(digit - '0')
		// We can also discard any numbers whos digits are greater than n. E.g 4 digit pandigital will not have a 5 in it.
		if d > numDigit {
			return false
		}
		// Checks for duplication.
		if digitCounts[d] {
			return false
		}
		digitCounts[d] = true
	}
	// Our pandigital numbers must contain one of each and exactly one of each digits equal and less than n. E.g 134 is not pandigital, as 2 is missing, and therefore false in our array.
	for i := 0; i < len(digitCounts)-1; i++ {
		if !digitCounts[i] {
			return false
		}
	}
	return true
}

// Quick and dirty function I made that can find the min and max values for our range given n. E.g n = 4 will output 999, 9999. This reduces time massively, especially for bigger n's.
func minAndMaxNumberFinder(n int) (int, int) {
	smaller := strings.Repeat("9", n-1)
	bigger := smaller + "9"
	min, _ := strconv.Atoi(smaller)
	max, _ := strconv.Atoi(bigger)
	return min, max
}

// Uses the makePrimes function given a range (above) to quickly find primes within our list, then calls the pandigitalChecker function on them and returns the one (largest [i--]) which passes.
func getLargestPandigitalPrime(n int) int {
	primes := makePrimes(minAndMaxNumberFinder(n))
	for i := len(primes) - 1; i > 0; i-- {
		if pandigitalChecker(primes[i], n) {
			return primes[i]
		}
	}
	return 0
}

func main() {
	arg := os.Args[1]
	argInt, _ := strconv.Atoi(arg)
	if argInt > 9 {
		fmt.Println("Input must be less than 10.")
		return
	}
	if argInt < 1 {
		fmt.Println("Input must be larger than 0.")
		return
	}
	result := getLargestPandigitalPrime(argInt)
	if result == 0 {
		fmt.Println("There is no", argInt, "digit pandigital prime.")
	} else {
		fmt.Println("The largest", argInt, "digit pandigital prime is", result)
	}
}
