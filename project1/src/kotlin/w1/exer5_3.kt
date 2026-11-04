fun main() {
    val pins = listOf(1111, 4321, 9999)
    val PIN = 4321
    var i = 0
    var unlocked = false

    while (i < pins.size && !unlocked) {
        if(pins[i] == PIN) {
            unlocked = true
        } else {
            println("Wrong PIN ${pins[i]}")
        }
        i++
    }
    println(if (unlocked) "Card unlocked" else "Card locked")
}