package w2
import kotlin.apply


class Book(var title: String = "", var author: String = "", var pages: Int = 0)


fun main() {
    val book = Book().apply {
        title = "1984"
        author = "J. Orwell"
        pages = 384
    }
    println(book.title)
}