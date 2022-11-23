package ro.alexmamo.firemag.presentation.product_search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.job
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.core.AppConstants.SEARCH

@Composable
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
fun ProductSearchTopBar(
    search: TextFieldValue,
    onSearchTextChanged: (newSearchText: TextFieldValue) -> Unit,
    onCloseIconClick: () -> Unit,
    navigateBack: () -> Unit,
) {
    val focusRequester = FocusRequester()
    val keyboard = LocalSoftwareKeyboardController.current

    TopAppBar(
        title = {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(1f)
                    .padding(
                        vertical = 2.dp
                    )
                    .focusRequester(focusRequester),
                value = search,
                onValueChange = { newSearchText ->
                    onSearchTextChanged(newSearchText)
                },
                placeholder = {
                    Text(
                        text = SEARCH
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    placeholderColor = Color.LightGray,
                    textColor = Color.White,
                    cursorColor = colorResource(R.color.accent)
                ),
                trailingIcon = {
                    IconButton(
                        onClick = onCloseIconClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboard?.hide()
                    }
                )
            )
            LaunchedEffect(Unit) {
                coroutineContext.job.invokeOnCompletion {
                    focusRequester.requestFocus()
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            //
        }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.primary),
            navigationIconContentColor = Color.White
        )
    )
}