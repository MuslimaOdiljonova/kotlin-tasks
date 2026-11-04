package w2

fun main() {
    val maybeName: String? = null
    println(maybeName?.uppercase().let({
        println(it)
    }))
}