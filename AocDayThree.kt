import java.util.*

const val right = 3
const val tree = '#'

fun main(args : Array<String>) {
    val puzzleInput : ArrayList<String> = inputPuzzle()
    println(countTrees(puzzleInput))
}

fun inputPuzzle() : ArrayList<String> {
    val input = ArrayList<String>()
    val reader = Scanner(System.`in`)
    while(reader.hasNextLine()) {
        input.add(reader.nextLine())
    }
    return input
}

fun countTrees(puzzle : ArrayList<String>) : Int {
    var countOfTrees = 0
    var row = 0
    var length = puzzle[0].length
    for(column in puzzle) {
        row = row % length
        if(column[row] == tree)
            countOfTrees++
        row += right
    }
    return countOfTrees
}