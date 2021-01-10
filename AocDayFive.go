package main

import (
    "bufio"
    "os"
    "fmt"
)

func main() {
    var puzzleInput []string = getPuzzleInput()
    
    maxSeatNumber := 0
    for i := 0; i < len(puzzleInput); i++ {
        var seatNumber = getSeatNumber(puzzleInput[i])
        if seatNumber > maxSeatNumber {
            maxSeatNumber = seatNumber
        }
    }
    fmt.Println(maxSeatNumber);
}

func getPuzzleInput() []string {
    scanner := bufio.NewScanner(os.Stdin)
    var puzzleInput []string
    for scanner.Scan() {
        line := scanner.Text()
        if line == "" {
            break
        }
        puzzleInput = append(puzzleInput, line)
    }
    return puzzleInput
}

func getSeatNumber(seatData string) int {
    minRowNumber := 0
    maxRowNumber := 127
    minColNumber := 0
    maxColNumber := 7
    for i := 0; i < len(seatData); i++ {
        if i <= 6 {
            if seatData[i] == 'F' {
                maxRowNumber = (minRowNumber + maxRowNumber) / 2;
            } else {
                minRowNumber = (minRowNumber + maxRowNumber) / 2 + 1;
            }
        } else {
            if seatData[i] == 'L' {
                maxColNumber = (minColNumber + maxColNumber) / 2;
            } else {
                minColNumber = (minColNumber + maxColNumber) / 2 + 1;
            }
        }
    }
    var seatNumber int = minRowNumber * 8 + minColNumber;
    return seatNumber
}
