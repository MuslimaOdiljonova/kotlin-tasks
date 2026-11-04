package uz.ttpu.composearchlab.ui.taste

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ArtistCard(
    artist: Artist,
    liked: Boolean,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(88.dp)
                .background(
                    Color.hsv((artist.id * 47 % 360).toFloat(), 0.5f, 0.8f),
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(artist.name.first().toString(), style = MaterialTheme.typography.headlineMedium)
        }
        Text(
            text = artist.name,
            maxLines = 2,
            textAlign = TextAlign.Center
        )
        IconButton(onClick = onLikeClick) {
            Icon(
                imageVector = if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (liked) "Remove ${artist.name} from liked" else "Like ${artist.name}"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArtistCardNotLikedPreview() {
    ArtistCard(seedArtists[0], liked = false, onLikeClick = {})
}

@Preview(showBackground = true)
@Composable
private fun ArtistCardLikedPreview() {
    ArtistCard(seedArtists[0], liked = true, onLikeClick = {})
}
