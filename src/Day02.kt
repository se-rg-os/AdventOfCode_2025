class Day02(path: String) {

    private var ranges: List<Range>

    init {
        val input = InputReader().readInput(path)
        val rangesStrings = input.first().split(",")
        ranges = rangesStrings.mapNotNull { rangeString ->
            val possibleRange = rangeString.split("-")
            if (possibleRange.size != 2) {
                null
            }
            var start = possibleRange.first().toLongOrNull()
            var end = possibleRange.last().toLongOrNull()
            if (start != null && end != null) {
                Range(start = start, end = end)
            } else {
                null
            }
        }

    }

    fun part1(): Long {
        return ranges.sumOf { range -> range.allInvalidsPart1() }
    }

    fun part2(): Long {
        return ranges.sumOf { range -> range.allInvalidsPart2() }
    }


    data class Range(val start: Long, val end: Long) {

        fun allInvalidsPart1(): Long {
            return (start..end).filter { isInvalidPart1(it) }.sum()
        }

        private fun isInvalidPart1(id: Long): Boolean {
            val stringRepresentation = id.toString()
            if (stringRepresentation.length % 2 != 0) {
                return false
            }
            val middle: Int = stringRepresentation.length / 2
            return stringRepresentation.take(middle) == stringRepresentation.substring(middle)
        }


        fun allInvalidsPart2(): Long {
            return (start..end).filter { isInvalidPart2(it) }.sum()
        }

        private fun isInvalidPart2(id: Long): Boolean {
            // Loop max through half of length
            // Check that the rest contains only prefix
            val stringRepresentation = id.toString()
            val middle: Int = stringRepresentation.length / 2

            var isInvalid = false

            for (start in 1..middle) {
                val prefix = stringRepresentation.take(start)
                if (stringRepresentation.replace(prefix, "").isBlank()) {
                    isInvalid = true
                    break
                }
            }

            return isInvalid
        }
    }

}

private fun main() {
    val day2Sample = Day02("day02_input_sample.txt")
    val day2 = Day02("day02_input.txt")

    println("Sample Task1: ${day2Sample.part1()}")
    println("Task1: ${day2.part1()}")

    println("Sample Task2: ${day2Sample.part2()}")
    println("Task2: ${day2.part2()}")
}