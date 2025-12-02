import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day01(path: String) {

    private val start = 50

    private val combinations: List<Combo>

    init {
        val input = InputReader().readInput(path)
        combinations = input.map { line ->
            Combo(line.first().toString(), line.drop(1).toIntOrNull() ?: 0)
        }
    }

    fun codePart1(): Long {
        var timesPassedZero: Long = 0
        var currentPosition = start
        combinations.forEach { combo ->
            currentPosition = normalize(currentPosition + combo.value)
            if (currentPosition == 0) {
                timesPassedZero++
            }
        }

        return timesPassedZero
    }

    fun codePart2(): Long {
        var timesPassedZero: Long = 0
        var currentPosition = start
        combinations.forEach { combo ->
            timesPassedZero+= numberOfClicksBetween(currentPosition, combo.value)
            currentPosition = normalize(currentPosition + combo.value)
            if (currentPosition == 0) {
                //Already taken into account
                timesPassedZero--
            }
        }

        return timesPassedZero
    }

    private fun normalize(input: Int): Int {
        //return input.mod(100) // <-- working solution, shorter
        return if (input % 100 == 0) {
            0
        } else if (input < 0) {
            input % 100 + 100
        } else {
            input % 100
        }
    }

    //How many clicks we will go through zero when going start with the offset
    private fun numberOfClicksBetween(start: Int, offset: Int): Int {
        var numberOfClicks: Int = 0
        val min = min(start, start+offset)
        val max = max(start, start+offset)
        for (pointer in min..max) {
            if (pointer % 100 == 0) {
                numberOfClicks++
            }
        }
        return numberOfClicks
    }

    data class Combo(private val direction: String, private val offset: Int) {
        val value: Int = offset * (if (direction.uppercase() == "L") { -1 } else { +1 })
    }
}

private fun main() {
    val day1Sample = Day01("day01_input_sample.txt")
    val day1 = Day01("day01_input.txt")
    println("Sample Task1: ${day1Sample.codePart1()}")
    println("Task1: ${day1.codePart1()}")
    println("Sample Task2: ${day1Sample.codePart2()}")
    println("Task2: ${day1.codePart2()}")
}