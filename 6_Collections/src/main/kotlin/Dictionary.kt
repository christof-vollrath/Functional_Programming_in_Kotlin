import java.nio.charset.StandardCharsets

fun loadDictionary(): List<String> =
        Object::class.java.getResource("/linuxwords.txt")
                .readText(StandardCharsets.UTF_8).lines()
                .filter { it.length != 0 }
                .filter { it.toList().all { it.isLetter() } }
