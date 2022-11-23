package ro.alexmamo.firemag.presentation.main.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.components.ProgressBar
import ro.alexmamo.firemag.core.Utils.Companion.print
import ro.alexmamo.firemag.domain.model.Response.*
import ro.alexmamo.firemag.domain.repository.Banners
import ro.alexmamo.firemag.presentation.main.MainViewModel

@Composable
@ExperimentalMaterial3Api
fun Banners(
    viewModel: MainViewModel = hiltViewModel(),
    bannersContent: @Composable (banners: Banners) -> Unit
) {
    when(val bannersResponse = viewModel.bannersResponse) {
        is Loading -> ProgressBar()
        is Success -> bannersResponse.data?.let { banners ->
            bannersContent(banners)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(bannersResponse.e)
        }
    }
}