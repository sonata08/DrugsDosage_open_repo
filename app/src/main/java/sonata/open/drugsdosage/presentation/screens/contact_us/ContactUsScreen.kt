package sonata.open.drugsdosage.presentation.screens.contact_us

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sonata.open.drugsdosage.R
import sonata.open.drugsdosage.presentation.common.components.MainButton
import sonata.open.drugsdosage.presentation.ui.theme.DrugsDosageTheme

@Composable
fun ContactUsScreen(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var subject by rememberSaveable { mutableStateOf("") }
    var message by rememberSaveable { mutableStateOf("") }
    var isButtonClicked by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier
            .padding(dimensionResource(R.dimen.screen_margin))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.contact_message),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it},
            label = { Text(stringResource(R.string.name)) },
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it},
            label = { Text(stringResource(R.string.message_subject)) },
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text(stringResource(R.string.message)) },
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            isError = message.isBlank() && isButtonClicked,
            supportingText = {
                if (message.isBlank() && isButtonClicked) {
                    Text(text = stringResource(R.string.empty_message))
                }
            },
            minLines = 4
        )
        MainButton(
            label = stringResource(R.string.send_message),
            onClick = {
                isButtonClicked = true
                if (message.isNotBlank()) {
                    composeEmail(context, name, subject, message)
                }
            },
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.margin_large))
        )

    }
}



private fun composeEmail(context: Context, name: String, subject: String, message: String) {
    // creates email subject: app name + user's Subject
    val fullSubject = "${context.getString(R.string.app_name)}: $subject"

    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        // type of message - email
        data = Uri.parse("mailto:") // only email apps should handle this
        putExtra(Intent.EXTRA_EMAIL, arrayOf("info@testresult.org")) // recipients
        putExtra(Intent.EXTRA_SUBJECT, fullSubject) // Email subject"
        putExtra(
            Intent.EXTRA_TEXT, """
                ${context.getString(R.string.name)}: $name

                $message
                """.trimIndent()
        )
    }

    try {
        context.startActivity(emailIntent)
    } catch (e: ActivityNotFoundException) {
        // Display some error message
        Toast.makeText(
            context,
            context.getString(R.string.cant_send_email),
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun ContactUsScreenPreview() {
    DrugsDosageTheme {
        ContactUsScreen()
    }
}