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
   i := 5
   for i*i <= n {
       if n%i == 0 || n%(i+2) == 0 {
           return false
       }
       i += 6
   }
   return true
}

// Creates the triangle as a map of integers from [first digit][last digit] and the row number is the index.
func generateTriangle(n int) map[int][2]int {
	triangle := make(map[int][2]int)
	start := 1

	for i := 1; i <= n; i++ {
		end := start + i - 1
		triangle[i] = [2]int{start, end}
		start = end + 1
	}

	return triangle
}

// function to get all values in a row
func getRowValues(row [2]int) []int {
	start, end := row[0], row[1]
	values := make([]int, end-start+1)
	for i := range values {
		values[i] = start + i
	}
	return values
}

// The easiest way to describe this function is that it returns a matrix of neighbours within a 3x3 grid around a number.
func findNeighbours(row int, num int, triangle map[int][2]int) [][]int {
	neighbours := [][]int{}
	currentRowValues := getRowValues(triangle[row])
	var prevRowValues, nextRowValues []int

	if row > 1 {
		prevRowValues = getRowValues(triangle[row-1])
	}
	if row < len(triangle) {
		nextRowValues = getRowValues(triangle[row+1])
	}
	// finds the position of the number in the row
	pos := -1
	for i, val := range currentRowValues {
		if val == num {
			pos = i
			break
		}
	}

	if pos == -1 {
		return neighbours
	}
	// Appending each of the neighbours to the matrix based on index
	if pos > 0 {
		neighbours = append(neighbours, []int{currentRowValues[pos-1], row})
	}
	if pos < len(currentRowValues)-1 {
		neighbours = append(neighbours, []int{currentRowValues[pos+1], row})
	}
	if len(prevRowValues) > 0 {
		if pos < len(prevRowValues) {
			neighbours = append(neighbours, []int{prevRowValues[pos], row - 1})

		}
		if pos < len(prevRowValues)-1 {
			neighbours = append(neighbours, []int{prevRowValues[pos+1], row - 1})
		}
		if pos-1 >= 0 {
			neighbours = append(neighbours, []int{prevRowValues[pos-1], row - 1})
		}
	}
	if len(nextRowValues) > 0 {
		if pos-1 >= 0 {
			neighbours = append(neighbours, []int{nextRowValues[pos-1], row + 1})
		}
		neighbours = append(neighbours, []int{nextRowValues[pos], row + 1})
		neighbours = append(neighbours, []int{nextRowValues[pos+1], row + 1})
	}
	return neighbours
}

// Using a singely recursive function it finds the neighbours of neighbours and returns true or false given a number and its neighbours whether it is part of any prime triplet. Heavy function, very inefficient.
func isPrimeTriplet(row int, n int, triangle map[int][2]int) bool {
	neighbours := findNeighbours(row, n, triangle)

	count := 1
	for _, neighbour := range neighbours {
		if isPrime(neighbour[0]) {
			count++
			secondNeighbours := findNeighbours(neighbour[1], neighbour[0], triangle)
			for _, secondNeighbour := range secondNeighbours {
				if secondNeighbour[0] != n && isPrime(secondNeighbour[0]) {
					count++
				}
			}
		}
		if count >= 3 {
			return true
		}
	}
	return false
}

// Quick function adds up each of the primes that pass the above test within the specified row. S(n) in the spec.
func sumPrimeTriplets(n int) int {
	var sum int
	triangle := generateTriangle(n + 2)
	values := getRowValues(triangle[n])
	for _, v := range values {
		if isPrime(v) {
			if isPrimeTriplet(n, v, triangle) {
				sum += v
			}
		}
	}
	return sum
}

func main() {
	args := os.Args
	n, _ := strconv.Atoi(args[1])
	fmt.Println(sumPrimeTriplets(n))
}
