fun main() {
    val menu = mapOf<String, Int>(
        "Plov" to 45000,
        "Lagman" to 40000,
        "Norin" to 55000,
        "Somsa" to 12000,
        "Pizza" to 110000,
        "Cheese Burger" to 65000
    )

    for ((food, price) in menu) {
        println("$food costs $price")
    }

    println("Total price of whole menu: " + menu.values.sum())

    println("Cheapest meal is: " + menu.entries.firstOrNull() { it.value == menu.values.min() }?.key)
    println("Most expensive meal is: " + menu.entries.firstOrNull() { it.value == menu.values.max() }?.key)
}