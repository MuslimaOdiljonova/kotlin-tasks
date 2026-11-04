package w2

open class Vehicle(val make: String, val model: String) {
    open fun honk(): String = "Beep!"
}

class Car(make:String, model:String) : Vehicle(make, model){
    override fun honk(): String {
        return "Honk honk!"
    }
}