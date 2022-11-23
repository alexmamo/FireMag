package ro.alexmamo.firemag.presentation.main.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import ro.alexmamo.firemag.components.ProgressBar
import ro.alexmamo.firemag.core.Utils.Companion.print
import ro.alexmamo.firemag.domain.model.Response.*
import ro.alexmamo.firemag.domain.repository.Brands
import ro.alexmamo.firemag.presentation.main.MainViewModel

@Composable
@ExperimentalMaterial3Api
fun Brands(
    viewModel: MainViewModel = hiltViewModel(),
    brandsContent: @Composable (brands: Brands) -> Unit
) {
    when(val brandsResponse = viewModel.brandsResponse) {
        is Loading -> ProgressBar()
        is Success -> brandsResponse.data?.let { brands ->
            brandsContent(brands)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(brandsResponse.e)
        }
    }
}