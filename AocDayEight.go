package main

import (
    "bufio"
    "os"
    "fmt"
    "strconv"
)

func main() {
    var puzzleInput []string = getPuzzleInput()
    fmt.Println(runCode(0, puzzleInput))
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

func runCode(accumulator int, instructions []string) int {
    for index := 0; index < len(instructions); {
        if instructions[index] == "#" {
            return accumulator
        }
        increment := 1
        value, _ := strconv.Atoi(instructions[index][4:])
        if instructions[index][0] == 'j' {
            increment = value
        } else if instructions[index][0] == 'a' {
            accumulator += value
        } 
        instructions[index] = "#"
        index += increment
    }
    return accumulator
}