package w2

abstract class Shape() {
    abstract fun area(): Double
    fun describe(): String {
        return "This shape has an area of ${area()}"
    }
}

class Circle(val radius: Double): Shape() {
    override fun area(): Double {
        return Math.PI * radius * radius
    }
}

class Rectangle(val width: Double, val height: Double) : Shape(){
    override fun area(): Double {
        return 2*(width+height)
    }
}

fun main() {
    var shape1 = Circle(10.0)
    println(shape1.describe())

    val shape2 = Rectangle(20.0, 45.0).also{
        println(it.describe())
    }
}