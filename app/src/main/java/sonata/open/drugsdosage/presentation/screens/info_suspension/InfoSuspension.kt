package sonata.open.drugsdosage.presentation.screens.info_suspension

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import sonata.open.drugsdosage.R

@Composable
fun InfoSuspension(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.info_susp),
        contentDescription = stringResource(id = R.string.instruction),
        modifier = modifier.fillMaxSize(),
    )
}