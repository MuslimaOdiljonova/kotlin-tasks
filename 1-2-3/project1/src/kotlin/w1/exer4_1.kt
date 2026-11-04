fun main() {
    val list = listOf("August", "Ocean eyes", "The Color Violet", "Photograph", "Roar");
    // list.add("Diet Mountain View"); // Kotlin: Unresolved reference 'add'.
    println(list)

    val likedSongs = mutableListOf<String>();
    likedSongs.add("August")
    likedSongs += "Ocean Eyes"
    likedSongs += ("Roar")
    println(likedSongs)

}