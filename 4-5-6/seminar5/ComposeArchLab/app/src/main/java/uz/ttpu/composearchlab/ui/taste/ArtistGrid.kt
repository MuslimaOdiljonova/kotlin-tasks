package uz.ttpu.composearchlab.ui.taste

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ArtistGrid(
    artists: List<Artist>,
    likedIds: Set<Int>,
    onLikeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier) {
        items(artists, key = { it.id }) { artist ->
            ArtistCard(
                artist = artist,
                liked = artist.id in likedIds,
                onLikeClick = { onLikeClick(artist.id) }
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 800)
@Composable
private fun ArtistGridPreview() {
    ArtistGrid(seedArtists, setOf(1, 5), onLikeClick = {})
}
