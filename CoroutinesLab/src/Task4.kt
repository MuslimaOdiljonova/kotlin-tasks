import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun loadName(): String {
    delay(100)
    return "Ada"
}

suspend fun loadAge(): Int {
    delay(100)
    return 36
}

fun main() = runBlocking {
    println("1 by 1")
    val timeSequential = measureTimeMillis {
        val name = loadName()
        val age = loadAge()
        println("Result: User $name, Age $age")
    }
    println("Time: $timeSequential ms\n")

    println("async await")
    val timeConcurrent = measureTimeMillis {
        val nameDeferred = async { loadName() }
        val ageDeferred = async { loadAge() }
        val name = nameDeferred.await()
        val age = ageDeferred.await()
        println("Result: User $name, Age $age")
    }
    println("Time: $timeConcurrent ms\n")

    println("await after async")
    val timeImmediateAwait = measureTimeMillis {
        val name = async { loadName() }.await()
        val age = async { loadAge() }.await()
        println("Result: User $name, Age $age")
    }
    println("Time: $timeImmediateAwait ms")
}