import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val single = newSingleThreadContext("MyThread")

    launch {
        println("Inherited: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {
        println("Default: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.IO) {
        println("IO: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        println("Unconfined: ${Thread.currentThread().name}")
    }

    launch(single) {
        println("SingleThread before delay: ${Thread.currentThread().name}")
        delay(100)
        println("SingleThread after delay: ${Thread.currentThread().name}")
    }

    delay(200)
    single.close()
}