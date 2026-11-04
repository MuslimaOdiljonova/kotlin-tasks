package uz.ttpu.composearchlab.ui.taste

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TastePickerViewModel : ViewModel() {

    private val _state = MutableStateFlow(TastePickerState(artists = seedArtists))
    val state: StateFlow<TastePickerState> = _state.asStateFlow()

    fun onGenreClick(genre: String) {
        _state.update {
            it.copy(selectedGenre = if (it.selectedGenre == genre) null else genre)
        }
    }

    fun onArtistLikeToggled(id: Int) {
        _state.update {
            it.copy(likedIds = if (id in it.likedIds) it.likedIds - id else it.likedIds + id)
        }
    }
}
