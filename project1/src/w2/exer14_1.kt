package w2

object AppConfig {
    val Appname = "Name"
    var isDebug = false

    fun toggleDebug() {
        isDebug = !isDebug
    }
}

fun main() {
    println(AppConfig.isDebug)
    AppConfig.toggleDebug()
    println(AppConfig.isDebug)
}