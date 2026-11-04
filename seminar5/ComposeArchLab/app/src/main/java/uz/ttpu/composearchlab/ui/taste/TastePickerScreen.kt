package uz.ttpu.composearchlab.ui.taste

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GenreChips(
    genres: List<String>,
    selectedGenre: String?,
    onGenreClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres) { genre ->
            FilterChip(
                selected = genre == selectedGenre,
                onClick = { onGenreClick(genre) },
                label = { Text(genre) }
            )
        }
    }
}

@Composable
fun TastePickerScreen(
    state: TastePickerState,
    onGenreClick: (String) -> Unit,
    onLikeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            TastePickerBottomBar(
                likedCount = state.likedCount,
                required = REQUIRED_LIKES,
                canContinue = state.canContinue,
                onContinueClick = { Log.d("TastePicker", "Continue clicked") },
                onSkipClick = { Log.d("TastePicker", "Later clicked") }
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            GenreChips(state.genres, state.selectedGenre, onGenreClick)
            ArtistGrid(state.visibleArtists, state.likedIds, onLikeClick)
        }
    }
}

@Composable
fun TastePickerRoute(viewModel: TastePickerViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TastePickerScreen(
        state = state,
        onGenreClick = viewModel::onGenreClick,
        onLikeClick = viewModel::onArtistLikeToggled
    )
}
