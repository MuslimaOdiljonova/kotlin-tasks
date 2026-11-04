fun main() {
    fun notify(message: String, channel: String = "Email"): String {
        return "[<$channel>] <$message>"
    }
    println(notify("Hello World!"))
    println(notify(message = "Muslima", channel = "Email"))
    println(notify(channel = "Email", message = "Kotlin"))
}