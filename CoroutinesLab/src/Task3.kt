import kotlinx.coroutines.*

fun main() = runBlocking {
    val job: Job = launch {
        try {
            repeat(5) { i ->
                println("tick $i")
                delay(100)
            }
        } finally {
            println("cleanup")
        }
    }

    delay(250)
    println("Cancelling job...")
//    job.cancelAndJoin()
    job.join()
    println("isCancelled=${job.isCancelled}")
}