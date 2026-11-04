package w2

import kotlin.math.sqrt

fun main() {
    fun Int.isPrime(): Boolean {
        if (this <= 1) return false;
        else if (this == 2 || this == 3) return true;
        else {
            val limit = sqrt(this.toDouble()).toInt()
            for (i in 2..limit) {
                if (this % i == 0) return false
            }
            return true
        }
    }

    for(i in 2..20) {
        println("$i: " + i.isPrime())
    }
}