package main

import (
	"fmt"
	"math"
	"os"
	"strconv"
)

// Function that takes a number and checks if it's prime
func isPrime(n int) bool {
	//checks
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

// Function that generates all primes up to n
func generatePrimes(n int) []int {
	primes := make([]bool, n+1)
	for i := range primes {
		primes[i] = true
	}

	primes[0], primes[1] = false, false
	for p := 2; p*p <= n; p++ {
		if primes[p] {
			for i := p * p; i <= n; i += p {
				primes[i] = false
			}
		}
	}

	// list `result` will contain all prime numbers
	var result []int
	for i := 0; i < len(primes); i++ {
		if primes[i] {
			result = append(result, i)
		}
	}

	return result
}

func main() {
	args := os.Args

	//Feeding the input from arguements to the program
	for i := 1; i < len(args); i++ {
		n, _ := strconv.Atoi(args[i])
		if n < 1 {
			fmt.Println("Input must be greater than 0!")
			return
		}
		primes := generatePrimes(int(math.Sqrt(float64(n))))
		nums := make([]bool, n)
		
		// Sieve to find all semiprimes
		for i, v := range nums {
			if v == false && isPrime(i) {
				for _, p := range primes {
					index := i * p
					if index <= len(nums) {
						nums[index] = true
				}
			}
		}
	}
	
	    // Counter setup to count the number of semiprimes
		count := 0
		for i := 0; i < len(nums); i++ {
			if nums[i] == true {
				count++
			}
		}
		fmt.Println(count, "semiprimes below", n)
}
}
