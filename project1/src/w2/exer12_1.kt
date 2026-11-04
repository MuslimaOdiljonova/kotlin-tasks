package w2

fun buildString(action: StringBuilder.() -> Unit): String {
    return StringBuilder().append("Hello, ").apply(action).toString()
}

fun main() {
    val res = buildString {append("Muslima")}
    println(res)
}