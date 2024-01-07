package main

import (
	"fmt"
	"os"
	"strconv"
)

// Function which finds finds the number of solutions for x^2 - y^2 - z^2 by looking at the difference variable (d) and itterating over each combination.
// At a computational level we are essentially marking each solution when we reach it, then returning all the solutions we reached 10 times.
func findSolutions(n int) int {
	solutions := make([]int, n)
	for x := 1; x < n*2; x++ {
		for d := x/5 + 1; d < (x+1)/2; d++ {
			tmp := (x - d) * (d*5 - x)
			if tmp >= n {
				break
			}
			solutions[tmp]++
		}
	}
	answer := 0
	// Count the number of solutions x for each n where x == 10
	for _, count := range solutions {
		if count == 10 {
			answer++
		}
	}
	return answer
}

func main() {
	args := os.Args

	for i := 1; i < len(args); i++{
		n, _ := strconv.Atoi(args[i])
		fmt.Println("There are", findSolutions(n), "values less than", n, "that have exactly ten distinct solutions")
}
}