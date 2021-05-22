import java.io.File;

fun main() {
    val puzzle : Collection<String> = getPuzzleInput()
    updatePosition(puzzle)
}

fun getPuzzleInput() : Collection<String> = 
    File("input_twelve.txt").useLines { it.toList() }

fun updatePosition(puzzle : Collection<String>) {
    var north : Int = 0
    var south : Int = 0
    var east : Int = 0
    var west : Int = 0
    var currentDirection : String = "E"

    for(instruction in puzzle) {
        var value = Integer.valueOf(instruction.substring(1))
        var action = instruction[0]
        
        if(action == 'N' || (action == 'F' && currentDirection.equals("N"))) {
           val updates = updateCoordinates(north, south, value)
           north = updates[0]
           south = updates[1]
        }
        else if(action == 'S' || (action == 'F' && currentDirection.equals("S"))) {
            val updates = updateCoordinates(south, north, value)
            south = updates[0]
            north = updates[1]     
        }
        else if(action == 'E' || (action == 'F' && currentDirection.equals("E"))) {
            val updates = updateCoordinates(east, west, value)
            east = updates[0]
            west = updates[1] 
        }
        else if(action == 'W' || (action == 'F' && currentDirection.equals("W"))) {
            val updates = updateCoordinates(west, east, value)
            west = updates[0]
            east = updates[1]       
        }
        else {
            val times = (value / 90) % 4
            for(i in 0..(times - 1)) {
                if((currentDirection.equals("N") && action == 'R') || (currentDirection.equals("S") && action == 'L'))
                    currentDirection = "E"
                else if((currentDirection.equals("S") && action == 'R') || (currentDirection.equals("N") && action == 'L'))
                    currentDirection = "W"
                else if((currentDirection.equals("E") && action == 'R') || (currentDirection.equals("W") && action == 'L'))
                    currentDirection = "S"
                else if((currentDirection.equals("W") && action == 'R') || (currentDirection.equals("E") && action == 'L'))
                    currentDirection = "N"
            }
        }
    }

    println(north + south + east + west)
}

fun updateCoordinates(mainDirection : Int, oppositeDirection : Int, value : Int) : Array<Int> {
    var main = mainDirection
    var opp = oppositeDirection

    if(opp == 0)
        main += value
    else if(opp >= value) 
        opp -= value
    else {
        main = main + (value - opp)
        opp = 0
    }

    return arrayOf(main, opp)
}