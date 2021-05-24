fun main() {
    val buses = ArrayList<Long>()
    val timestamp = getPuzzleInput(buses) 
    println(getWaitingTime(buses, timestamp))
}

fun getPuzzleInput(buses : ArrayList<Long>) : Long {
    val timestamp = readLine() ?: "0"
    val input = (readLine() ?: "0").split(",")
    for (bus in input) {
        if(!bus.equals("x"))
            buses.add(bus.toLong())
    }
    return timestamp.toLong()
}

fun getWaitingTime(buses : ArrayList<Long>, timestamp : Long) : Long {
    var waitingTime : Long = 0
    var closest : Long = Long.MAX_VALUE

    for(time in buses) {
        if(time % timestamp != 0L) {
            var timeDiff : Long = Math.ceil(timestamp.toDouble() / time.toDouble()).toLong()
            timeDiff = timeDiff * time - timestamp
            if(timeDiff < closest) {
                closest = timeDiff
                waitingTime = time
            }
        }
    }

    return waitingTime * closest
}