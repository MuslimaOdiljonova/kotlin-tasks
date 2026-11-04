package w2

interface Shape1 {
    fun area(): Double
}

class Circle1(val radius: Double) : Shape1 {
    override fun area(): Double = Math.PI * radius * radius
}

class Rectangle1(val width: Double, val height: Double) : Shape1 {
    override fun area(): Double = width * height
}