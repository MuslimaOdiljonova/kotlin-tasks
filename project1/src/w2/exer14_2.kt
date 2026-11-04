package w2

class User(var name: String) {
    companion object Obj{
        fun create(name: String): User = User(name)
    }
}

fun main() {
    var user1 = User.create("Muslima")
    println(user1.name)
}