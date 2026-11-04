fun main() {
    val item: String = "item";
    val itemPrice: Int = 3000;
    var itemAmount: Int = 10;
    var profit: Int = 0;

    fun buy(buy: Boolean) {
        if (buy.equals(true)) {
            itemAmount -= 1;
            profit += itemPrice;
        }
    }

    for (i in 1 ..3) {
        buy(true);
        println("Profit so far: " + profit)
    }
    println("Total profit: " + profit);
    println("Item amount: " + itemAmount);
}