class InputReader {

    fun readInput(path: String): List<String> {
        return javaClass.getResourceAsStream(path)
            ?.bufferedReader(Charsets.UTF_8)
            ?.lines()
            ?.toList()
            .orEmpty()
    }
}