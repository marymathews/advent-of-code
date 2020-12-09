import java.util.*

const val tree = '#'

fun main(args : Array<String>) {
    val puzzleInput : ArrayList<String> = inputPuzzle()
    var count : Long = countTrees(puzzleInput, 1, 3)
    println(count)
    count = count * countTrees(puzzleInput, 2, 1) * countTrees(puzzleInput, 1, 1) * countTrees(puzzleInput, 1, 5) * countTrees(puzzleInput, 1, 7)
    println(count)
}

fun inputPuzzle() : ArrayList<String> {
    val input = ArrayList<String>()
    val reader = Scanner(System.`in`)
    while(reader.hasNextLine()) {
        input.add(reader.nextLine())
    }
    reader.close()
    return input
}

fun countTrees(puzzle : ArrayList<String>, down : Int, right : Int) : Long {
    var countOfTrees = 0L
    var column = 0
    var row = 0
    var length = puzzle[0].length
    while(row <= puzzle.size - 1) {
        column = column % length
        if(puzzle[row][column] == tree)
            countOfTrees++
        column += right
        row += down
    }
    return countOfTrees
}