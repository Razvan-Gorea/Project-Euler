package main

import (
    "fmt"
    "os"
    "strconv"
)

func runner(period int) int{
    
   dp := make([][][]int, period+1)

   // Initializing the dp array
   for i := range dp {
       dp[i] = make([][]int, 3)
       for j := range dp[i] {
           dp[i][j] = make([]int, 2)
       }
   }
   
   
   dp[0][0][0] = 1

   // Iterating over the 30 day period
   for i := 0; i < period; i++ {
       for j := 0; j < 3; j++ {
           for k := 0; k < 2; k++ {
               // Adding 'O'
               dp[i+1][0][k] += dp[i][j][k]
               // Adding 'A'
               if j < 2 {
                  dp[i+1][j+1][k] += dp[i][j][k]
               }
               // Adding 'L'
               if k < 1 {
                  dp[i+1][0][k+1] += dp[i][j][k]
               }
           }
       }
   }

   // Finding all the prize strings existing over a 30 day period
   sum := 0
   for j := 0; j < 3; j++ {
       for k := 0; k < 2; k++ {
           sum += dp[period][j][k]
       }
   }
   return sum
}

func main(){
    args := os.Args
    
    for i := 1; i < len(args); i++ {
        period, _ := strconv.Atoi(args[i])
	if period < 1 {
		fmt.Println("Input must be larger than 0!")
		return
	}
        fmt.Println("There are", runner(period), "prize strings that exist over a", period, "day period.")
}
} 
