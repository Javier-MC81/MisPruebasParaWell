package com.jmoreno.mispruebasparawell.First

import android.graphics.Paint
import android.os.Bundle
import android.text.Selection.setSelection
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.jmoreno.mispruebasparawell.BloodGroupSelectorWithDialog
import com.jmoreno.mispruebasparawell.ImplantSelectorWithDialog
import com.jmoreno.mispruebasparawell.R
import com.jmoreno.mispruebasparawell.ReligionSelectorWithDialog
import com.jmoreno.mispruebasparawell.ui.theme.MisPruebasParaWellTheme
import live.wellconnect.wellconnect.presentation.SignInState

class OnBoardingFirst : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MisPruebasParaWellTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OnBoardingFirst(state = SignInState(false,"")){}


                }
            }
        }
    }
}

@Composable
fun OnBoardingFirst(
    state: SignInState,
    navigateToOnBoardingSecond: () -> Unit
) {

    var textValue by remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current

    val genderOptions = listOf("Soy hombre", "Soy mujer", "Prefiero no decirlo")
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top= 16.dp, bottom = 8.dp) // Ajusta el valor según el margen deseado
        ) {
            NumberedProgressBar(
                steps = 3,
                currentStep = 0,
                lineColor = Color.LightGray,
                activeColor = colorResource(id = R.color.grisOscuroskyBlue),
                inactiveColor = Color.LightGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text= "Vamos a crear tu perfil",
                style = TextStyle(
                    color = colorResource(id = R.color.grisOscuroskyBlue),
                    fontSize = 24.sp, // Cambia el tamaño del texto aquí
                    fontWeight = FontWeight.Bold // Puedes ajustar el peso de la fuente si es necesario
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Necesitamos tus datos",
                style = TextStyle(
                    color = colorResource(id = R.color.grisOscuroskyBlue),
                    fontSize = 16.sp,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Hola XXXXX",
                style = TextStyle(
                    color = colorResource(id = R.color.grisOscuroskyBlue),
                    fontSize = 16.sp,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 92.dp)
                .wrapContentSize(align = Alignment.CenterStart),
            verticalArrangement = Arrangement.spacedBy(16.dp),

            ) {
            var tValName by remember { mutableStateOf("") }
            var tValSex by remember { mutableStateOf("") }
            var tValAge by remember { mutableStateOf("") }
            var tValCountry by remember { mutableStateOf("") }
            var tValPhoneNumber by remember { mutableStateOf("") }
            var tValBloodGroup by remember { mutableStateOf("") }
            var isDialogOpen by remember { mutableStateOf(false) }

            EditTextField(
                value = tValName,
                onValueChange = { tValName = it },
                hint = "Tu nombre"
            )
            DropDownTextFieldWithDialog("Indica tu sexo")
            EditTextField(
                value = tValAge,
                onValueChange = { tValAge = it },
                hint = "Edad"
            )
            CountrySelectorWithDialog("Indica tu país de origen")
            EditTextField(
                value = tValPhoneNumber,
                onValueChange = { tValPhoneNumber = it },
                hint = "Teléfono"
            )
            ReligionSelectorWithDialog("Religión")
            BloodGroupSelectorWithDialog("Indica tu grupo sanguíneo")
            ImplantSelectorWithDialog("Implantes")

            Spacer(modifier = Modifier.height(30.dp))

        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = navigateToOnBoardingSecond,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.grisOscuroskyBlue),
                contentColor = Color.White
            ) //

        ) {
            Text(text = "Siguiente")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OnBoardingFirst_Preview() {
    OnBoardingFirst(SignInState(true,null)){}

}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .border(1.dp, colorResource(id = R.color.grisOscuroskyBlue), RoundedCornerShape(8.dp))
        .background(Color.White, shape = MaterialTheme.shapes.medium)
) {
    var isHintVisible by remember { mutableStateOf(value.isEmpty()) }
    var isEditable by remember { mutableStateOf(true) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isHintVisible = it.isEmpty()
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Ocultar el teclado
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 12.dp) // Ajuste del padding en todos los lados
                .onFocusChanged {
                    isHintVisible = it.isFocused

                }
        )

        if (value.isEmpty()) {
            Text(
                text = hint,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 12.dp)
            )
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
            )
        }


    }
}
@Preview(showBackground = true)
@Composable
fun EditTextField_Preview() {
    EditTextField("",{}, "Introduce tus datos")
}
@Composable
fun DropDownTextFieldWithDialog(sex: String) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(sex) }

    DropDownTextField(
        value = selectedGender,
        onValueChange = { selectedGender = it },
        hint = "Selecciona tu género",
        options = listOf("Masculino", "Femenino", "No binario", "Prefiero no decirlo"),
        onOptionSelected = {
            selectedGender = it
            isDialogOpen = false
        }
    )

    if (isDialogOpen) {
        GenderOptionsDialog(
            options = listOf("Soy hombre", "Soy mujer", "No binario", "Prefiero no decirlo"),
            onOptionSelected = {
                selectedGender = it
                isDialogOpen = false
            },
            onDismissRequest = { isDialogOpen = false }
        )
    }
}
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
        .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
) {
    var isHintVisible by remember { mutableStateOf(value.isEmpty()) }
    var expanded by remember { mutableStateOf(false) }

    val selectedOptionIndex = options.indexOf(value)

    Box(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .shadow(
                6.dp,
                shape = MaterialTheme.shapes.medium
            )
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(start = 12.dp, end = 12.dp)
    ) {

        ClickableText(
            text = AnnotatedString(value),
            onClick = {
                expanded = true // Activar el diálogo al hacer clic en el campo
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp)
                .align(Alignment.CenterStart)
                .wrapContentHeight(Alignment.CenterVertically),
            style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontSize = 16.sp
            )
        )
        Icon(
            painter = painterResource(id = R.drawable.caretdown),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterEnd) // Centrar verticalmente y alinear a la derecha
                .padding(end = 12.dp),
            tint = colorResource(id = R.color.grisOscuroskyBlue)
        )

        if (expanded) {
            GenderOptionsDialog(
                options = options,
                onOptionSelected = {
                    onOptionSelected(it)
                    expanded = false // Cerrar el diálogo después de seleccionar una opción
                },
                onDismissRequest = {
                    expanded = false // Cerrar el diálogo si se descarta
                }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderOptionsDialog(
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(
            text = "Selecciona tu género",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        ) },
        shape = MaterialTheme.shapes.medium,
        text = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                options.forEach { gender ->

                    Button(
                        onClick = {
                            onOptionSelected(gender)
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = colorResource(id = R.color.grisOscuroskyBlue)

                        ),
                        border = BorderStroke(1.dp, colorResource(id = R.color.azul))//

                    ) {
                        Text(
                            text = gender,
                            color = colorResource(id = R.color.azul)
                        )
                    }
                }
            }
        }
            , confirmButton = {},
        dismissButton = {}
    )
}
@Preview
@Composable
fun GenderOptionsDialogPreview() {
    val genderOptions = listOf("Masculino", "Femenino", "No binario", "Prefiero no decirlo")

    GenderOptionsDialog(
        options = genderOptions,
        onOptionSelected = {},
        onDismissRequest = {}
    )
}


@Composable
fun ProgressScreen(progress: Float) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ProgressScreen_Preview() {
    ProgressScreen(progress = 0.7f) // Cambia el valor de "progress" según tu necesidad (de 0.0f a 1.0f)
}
@Composable
fun NumberedProgressBar(
    steps: Int,
    currentStep: Int,
    lineColor: Color = Color.Gray,
    activeColor: Color = Color.Blue,
    inactiveColor: Color = Color.LightGray
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until steps) {
                val isCurrentStep = i == currentStep

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(if (isCurrentStep) activeColor else inactiveColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (i + 1).toString(),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }

                if (i < steps - 1) {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .height(2.dp)
                            .background(lineColor)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun NumberedProgressBarPreview() {
    NumberedProgressBar(
        steps = 3,
        currentStep = 0,
        lineColor = Color.Gray,
        activeColor = Color.Blue,
        inactiveColor = Color.LightGray
    )
}

@Composable
fun CountrySelectorWithDialog(selectedCountry: String) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedCountryName by remember { mutableStateOf(selectedCountry) }

    CountrySelectorTextField(
        value = selectedCountryName,
        onValueChange = { selectedCountryName = it },
        hint = "Selecciona tu país",
        onSelectorClick = {
            isDialogOpen = true
        }
    )

    if (isDialogOpen) {
        CountryOptionsDialog(
            options = listOf(
                "Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "Guyana",
                "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela", "Belize", "Costa Rica",
                "El Salvador", "Guatemala", "Honduras", "Mexico", "Nicaragua", "Panama"
            ), // Implementa una función que devuelve la lista de países
            selectedCountry = selectedCountryName,
            onOptionSelected = {
                selectedCountryName = it
                isDialogOpen = false
            },
            onDismissRequest = { isDialogOpen = false }
        )
    }
}
@Composable
fun CountrySelectorTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onSelectorClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable { onSelectorClick() }
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(start = 12.dp, end = 12.dp)

    ) {
        Text(
            text = if (value.isNotEmpty()) value else hint,
            color = if (value.isNotEmpty()) Color.Black else Color.Gray,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.Start)
        )
        Icon(
            painter = painterResource(id = R.drawable.caretdown),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically), // Centrar verticalmente y alinear a la derecha,
            tint = colorResource(id = R.color.grisOscuroskyBlue)
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryOptionsDialog(
    options: List<String>,
    selectedCountry: String,
    onOptionSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Selecciona tu país",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = MaterialTheme.shapes.medium,
        text = {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().height(250.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(options) { index, country ->
                    val isSelected = country == selectedCountry

                    Text(
                        text = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                onOptionSelected(country)
                                onDismissRequest()
                            },
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    )
                }
            }
        },
        confirmButton = {},
        dismissButton = {}
    )
}
@Composable
@Preview
fun CountrySelectorTextField_Preview() {
    CountrySelectorTextField(
        value = "United States",
        onValueChange = {},
        hint = "Selecciona tu país",
        onSelectorClick = {}
    )
}

@Composable
@Preview
fun CountryOptionsDialog_Preview() {
    val countriesOfSouthAndCentralAmerica = listOf(
        "Argentina", "Bolivia", "Brazil", "Chile", "Colombia", "Ecuador", "Guyana",
        "Paraguay", "Peru", "Suriname", "Uruguay", "Venezuela", "Belize", "Costa Rica",
        "El Salvador", "Guatemala", "Honduras", "Mexico", "Nicaragua", "Panama"
    )
    CountryOptionsDialog(
        options = countriesOfSouthAndCentralAmerica,
        selectedCountry = "United States",
        onOptionSelected = {},
        onDismissRequest = {}
    )
}
