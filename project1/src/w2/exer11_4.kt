package w2

import kotlin.reflect.typeOf

fun main() {
    val book = Book(pages = 400)
    println(book.title)

    val book1: Book? = Book(pages = 400)
    book?.let{
        println(it.pages)
    }

    val book2 = Book().apply{
        pages = 400
    }
    println(book2.title)

    val book3 = Book().also {
        it.pages = 4000
    }.also {
        println(it.title)
    }
}
