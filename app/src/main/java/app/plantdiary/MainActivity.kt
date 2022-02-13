package app.plantdiary

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.plantdiary.ui.theme.MyPlantDiaryTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.fetchPlants()
            val plants by viewModel.plants.observeAsState(initial = emptyList())
            MyPlantDiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxWidth()) {
                    SpecimenFacts("Android")
                }
                var foo = plants
                var i = 1 + 1
            }
        }
    }
}

@Composable
fun SpecimenFacts(name: String) {
    var plantName by remember { mutableStateOf("")}
    var location by remember { mutableStateOf("")}
    var description by remember { mutableStateOf("")}
    var datePlanted by remember { mutableStateOf("")}
    val context = LocalContext.current
    Column {
        OutlinedTextField(
            value = plantName,
            onValueChange = {plantName = it},
            label = { Text(stringResource(R.string.plantName))},
            modifier = Modifier.fillMaxWidth()
            )
        OutlinedTextField(
            value = location,
            onValueChange = {location = it},
            label = { Text(stringResource(R.string.location))},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = {description = it},
            label = { Text(stringResource(R.string.description))},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = datePlanted,
            onValueChange = {datePlanted = it},
            label = { Text(stringResource(R.string.datePlanted))},
            modifier = Modifier.fillMaxWidth()
        )
        Button (
            onClick = {
                Toast.makeText(context, "$plantName $location $description $datePlanted", Toast.LENGTH_LONG).show()
            }
        ){
            Text(text = "Save")
        }
    }
}

@Preview(name="Light Mode", showBackground=true)
@Preview(uiMode= Configuration.UI_MODE_NIGHT_YES, showBackground = true, name="Dark Mode")
@Composable
fun DefaultPreview() {
    MyPlantDiaryTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxWidth()) {
            SpecimenFacts("Android")
        }
    }
}