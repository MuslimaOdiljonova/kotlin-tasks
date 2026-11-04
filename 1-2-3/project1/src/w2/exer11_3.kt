package w2

fun main() {
    val book1: Book = Book(title="Atomic Habits", author="J.K.Rowling", ).also {
        println("Created book ${it.title}")
    }
}