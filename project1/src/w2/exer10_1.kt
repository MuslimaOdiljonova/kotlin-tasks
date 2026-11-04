package w2

fun main() {
    fun String.shout(): String = "Shouting $this"

    println("Hello".shout())
}