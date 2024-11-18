package uk.ac.tees.mad.w9624019.cinimaapp.utils.navigation

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner

private val localBackPressedDispatcher = staticCompositionLocalOf<OnBackPressedDispatcherOwner?> {
    null
}

private class ComposableBackNavigationHandler(enabled: Boolean) : OnBackPressedCallback(enabled) {
    lateinit var onBackPressed: () -> Unit

    override fun handleOnBackPressed() {
        onBackPressed()
    }
}

@Composable
internal fun ComposableHandler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit
) {
    val dispatcher = (localBackPressedDispatcher.current ?: return).onBackPressedDispatcher
    val handler = remember { ComposableBackNavigationHandler(enabled) }

    DisposableEffect(dispatcher) {
        dispatcher.addCallback(handler)
        onDispose { handler.remove() }
    }

    LaunchedEffect(enabled) {
        handler.isEnabled = enabled
        handler.onBackPressed = { onBackPressed() }
    }
}

@Composable
internal fun SystemBackButtonHandler(onBackPressed: () -> Unit) {
    CompositionLocalProvider(
        localBackPressedDispatcher provides LocalLifecycleOwner.current as ComponentActivity
    ) {
        ComposableHandler(enabled = true, onBackPressed = onBackPressed)
    }
}
