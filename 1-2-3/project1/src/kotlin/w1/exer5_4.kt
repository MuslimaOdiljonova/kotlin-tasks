fun main() {
    for( i in 1 .. 30) {
//        if (i % 3 == 0 && i % 5 == 0) {
//            println("FizzBuzz $i")
//        }
//        else if (i % 5 == 0) {
//            println("Buzz $i")
//        }
//        else if (i % 3 == 0) {
//            println("Fizz $i")
//        }
//    }
    when {
        i % 3 == 0 && i % 5 == 0 -> println("FizzBuzz $i")
        i % 3 == 0 -> println("Fizz $i")
        i % 5 == 0 -> println("Buzz $i")


    }    }
}