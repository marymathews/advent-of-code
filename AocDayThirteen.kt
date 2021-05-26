fun main() {
    val buses = ArrayList<Long>()
    val minutesDiff = ArrayList<Int>()
    val timestamp = getPuzzleInput(buses, minutesDiff)
    println(getWaitingTime(buses, timestamp))
    println(getEarliestTimestamp(buses, minutesDiff, buses[0]))
}

fun getPuzzleInput(buses : ArrayList<Long>, minutesDiff : ArrayList<Int>) : Long {
    var minuteCount = 0;
    val timestamp = readLine() ?: "0"
    val input = (readLine() ?: "0").split(",")
    for (bus in input) {
        if(!bus.equals("x")) {
            buses.add(bus.toLong())
            minutesDiff.add(minuteCount)
        }
        minuteCount++;
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

fun getEarliestTimestamp(buses : ArrayList<Long>, minutesDiff : ArrayList<Int>, start : Long) : Long {
    var index : Int = 0;
    var time = start
    var increment : Long = 1L;

    while(index < buses.size) {
        if((time + minutesDiff[index]) % buses[index] != 0L) {
            time += increment;
        } else {
            increment *= buses[index];
            index++;
        }
    }

    return time
}