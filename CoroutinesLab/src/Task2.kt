import kotlinx.coroutines.delay

var callCount = 0

suspend fun fetchName(): String? {
    Thread.sleep(100)
    callCount++
    return if (2 >= callCount) null else "Ada"
}

suspend fun showUser(n: Int) {
    for(k in 1..n) {
        val name = fetchName()
        if (name != null) {
            println("User $name")
            return
        } else {
            println("try $k failed")
        }
    }
}