package sonata.open.drugsdosage.presentation.utils

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import sonata.open.drugsdosage.R

@Composable
fun DoubleBackPressHandler(enabled: Boolean = true) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isBackPressed by remember { mutableStateOf(false) }
    val toast =  Toast.makeText(context, stringResource(id = R.string.press_back_to_exit), Toast.LENGTH_SHORT)
    BackHandler(enabled && !isBackPressed) {
        isBackPressed = true
        toast.show()
        scope.launch {
            delay(2000L)
            isBackPressed = false
        }
    }
}