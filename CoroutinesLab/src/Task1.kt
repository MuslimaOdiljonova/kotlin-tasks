import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


    fun task1() = runBlocking<Unit> {
        launch {
            delay(200)
            println("A done")
        }
        launch {
            delay(100)
            println("B done")
        }
        println("Start")
    }
