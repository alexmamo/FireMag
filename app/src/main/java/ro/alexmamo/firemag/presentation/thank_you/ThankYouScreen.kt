package ro.alexmamo.firemag.presentation.thank_you

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ro.alexmamo.firemag.components.LargeButton
import ro.alexmamo.firemag.components.Message
import ro.alexmamo.firemag.core.AppConstants.BACK_TO_HOME
import ro.alexmamo.firemag.core.AppConstants.THANK_YOU_MESSAGE

@Composable
fun ThankYouScreen(
    navigateBackToMainScreen: () -> Unit
) {
    Message(
        text = THANK_YOU_MESSAGE
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LargeButton(
            text = BACK_TO_HOME,
            onClick = navigateBackToMainScreen
        )
    }
}