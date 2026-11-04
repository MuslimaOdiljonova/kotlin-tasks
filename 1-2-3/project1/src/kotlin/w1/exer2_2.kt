fun main() {

    fun printReceipt(item: String, price: Int) {
        println("""
            Item name: $item
            Price: $$price
            Thank you!
        """.trimIndent())
    }

    printReceipt("Sneakers", 145)
    printReceipt("iWatch", 800)
}