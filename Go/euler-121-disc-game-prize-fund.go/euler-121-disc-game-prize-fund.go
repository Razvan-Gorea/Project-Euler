package main

import (
	"fmt"
	"strconv"
	"os"
)

func possibleCombinations(turns int) [][]int {

	//2D slice to store the possible combinations of numbers for a number of turns
	combinations := make([][]int, turns+1)
	for i := range combinations {
		combinations[i] = make([]int, turns+1)
	}

	combinations[0][0] = 1

	for i := 1; i <= turns; i++ {
		for j := 0; j <= i; j++ {
			tmp := 0
			if j < i {
				tmp += combinations[i-1][j] * i
			}
			if j > 0 {
				tmp += combinations[i-1][j-1]
			}
			combinations[i][j] = tmp
		}
	}
	return combinations
}

// function that calculates the factorial of a float64 number
func factorial(n float64) float64 {
	if n == 0 {
		return 1
	} else {
		return n * factorial(n-1)
	}
}

// function that calculates the prize fund
func fundCalculator(combinations [][]int, turns int) string {
	possibilitiesSum := 0
	for i := turns/2 + 1; i <= turns; i++ {
		possibilitiesSum += combinations[turns][i]
	}

	totalFactorial := int(factorial(float64(turns + 1)))
	result := strconv.Itoa(totalFactorial / possibilitiesSum)

	//prize fund is the denominator divided by the numerator
	return result
}

// main function that simply calls the other functions
func main() {
	args := os.Args
	for i := 1; i < len(args); i++{
		turns, _ := strconv.Atoi(args[i])
		if turns < 1 {
			fmt.Println("Turns must be greater than 0!")
			return
		}
		result := possibleCombinations(turns)
		fmt.Println("Maximum prize fund for a", turns, "turn game:", "£" + fundCalculator(result, turns))

}
}
