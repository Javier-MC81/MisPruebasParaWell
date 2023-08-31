package com.jmoreno.mispruebasparawell

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jmoreno.mispruebasparawell.First.EditTextField
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReligionSelectorWithDialog(selectedReligion: String) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedReligionName by remember { mutableStateOf(selectedReligion) }

    ReligionSelectorTextField(
        value = selectedReligionName,
        onValueChange = { selectedReligionName = it },
        hint = "Selecciona tu religión",
        onSelectorClick = {
            isDialogOpen = true
        }
    )

    if (isDialogOpen) {
        ReligionOptionsDialog(
            options = listOf(
                "Cristianismo", "Islam", "Hinduismo", "Budismo", "Sijismo", "Judaísmo", "Ateísmo","Testigo de Jehová",
                "Otra"
            ), // Implementa una función que devuelve la lista de religiones
            selectedReligion = selectedReligionName,
            onOptionSelected = {
                selectedReligionName = it
                isDialogOpen = false
            },
            onDismissRequest = { isDialogOpen = false }
        )
    }
}

@Composable
fun ReligionSelectorTextField(
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
            .padding(horizontal = 12.dp, vertical = 12.dp)
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
fun ReligionOptionsDialog(
    options: List<String>,
    selectedReligion: String,
    onOptionSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Selecciona tu religión",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = MaterialTheme.shapes.medium,
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(options) { index, religion ->
                    val isSelected = religion == selectedReligion

                    Text(
                        text = religion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                onOptionSelected(religion)
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
fun ReligionSelectorTextField_Preview() {
    ReligionSelectorTextField(
        value = "Cristianismo",
        onValueChange = {},
        hint = "Selecciona tu religión",
        onSelectorClick = {}
    )
}

@Composable
@Preview
fun ReligionOptionsDialog_Preview() {
    val religionOptions = listOf(
        "Cristianismo", "Islam", "Hinduismo", "Budismo", "Sijismo", "Judaísmo", "Ateísmo",
        "Otra"
    )
    ReligionOptionsDialog(
        options = religionOptions,
        selectedReligion = "Cristianismo",
        onOptionSelected = {},
        onDismissRequest = {}
    )
}
@Composable
fun BloodGroupSelectorWithDialog(selectedBloodGroup: String) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedBloodGroupName by remember { mutableStateOf(selectedBloodGroup) }

    BloodGroupSelectorTextField(
        value = selectedBloodGroupName,
        onValueChange = { selectedBloodGroupName = it },
        hint = "Selecciona tu grupo sanguíneo",
        onSelectorClick = {
            isDialogOpen = true
        }
    )

    if (isDialogOpen) {
        BloodGroupOptionsDialog(
            options = listOf("A", "B", "AB", "O"),
            selectedBloodGroup = selectedBloodGroupName,
            onOptionSelected = {
                selectedBloodGroupName = it
                isDialogOpen = false
            },
            onDismissRequest = { isDialogOpen = false }
        )
    }
}

@Composable
fun BloodGroupSelectorTextField(
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
            .padding(horizontal = 12.dp, vertical = 12.dp)
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
fun BloodGroupOptionsDialog(
    options: List<String>,
    selectedBloodGroup: String,
    onOptionSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Selecciona tu grupo sanguíneo",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = MaterialTheme.shapes.medium,
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(options) { index, bloodGroup ->
                    val isSelected = bloodGroup == selectedBloodGroup

                    Text(
                        text = bloodGroup,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                onOptionSelected(bloodGroup)
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
fun BloodGroupSelectorTextField_Preview() {
    BloodGroupSelectorTextField(
        value = "A",
        onValueChange = {},
        hint = "Selecciona tu grupo sanguíneo",
        onSelectorClick = {}
    )
}

@Composable
@Preview
fun BloodGroupOptionsDialog_Preview() {
    val bloodGroupOptions = listOf("A", "B", "AB", "O")
    BloodGroupOptionsDialog(
        options = bloodGroupOptions,
        selectedBloodGroup = "A",
        onOptionSelected = {},
        onDismissRequest = {}
    )
}
@Composable
fun ImplantSelectorWithDialog(selectedImplant: String) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedImplantName by remember { mutableStateOf(selectedImplant) }

    ImplantSelectorTextField(
        value = selectedImplantName,
        onValueChange = { selectedImplantName = it },
        hint = "Selecciona un implante",
        onSelectorClick = {
            isDialogOpen = true
        }
    )

    if (isDialogOpen) {
        ImplantOptionsDialog(
            options = listOf(
                "Bypass cardíaco", "Prótesis de rodilla", "Prótesis de cadera", "Stent coronario",
                "Pacemaker", "Implante coclear", "Implante dental", "Implante de catarata"
            ), // Implementa una función que devuelve la lista de tipos de implantes
            selectedImplant = selectedImplantName,
            onOptionSelected = {
                selectedImplantName = it
                isDialogOpen = false
            },
            onDismissRequest = { isDialogOpen = false }
        )
    }
}

@Composable
fun ImplantSelectorTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onSelectorClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectorClick() }
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
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
fun ImplantOptionsDialog(
    options: List<String>,
    selectedImplant: String,
    onOptionSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Selecciona un tipo de implante",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = MaterialTheme.shapes.medium,
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(options) { index, implant ->
                    val isSelected = implant == selectedImplant

                    Text(
                        text = implant,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                onOptionSelected(implant)
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
fun ImplantSelectorTextField_Preview() {
    ImplantSelectorTextField(
        value = "Bypass cardíaco",
        onValueChange = {},
        hint = "Selecciona un implante",
        onSelectorClick = {}
    )
}

@Composable
@Preview
fun ImplantOptionsDialog_Preview() {
    val implantOptions = listOf(
        "Bypass cardíaco", "Prótesis de rodilla", "Prótesis de cadera", "Stent coronario",
        "Pacemaker", "Implante coclear", "Implante dental", "Implante de catarata", "Otro"
    )
    ImplantOptionsDialog(
        options = implantOptions,
        selectedImplant = "Bypass cardíaco",
        onOptionSelected = {},
        onDismissRequest = {}
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myDropDownMenu(allergyType: String) {
    var isExpanded by remember { mutableStateOf(false)}
    var allergy by remember { mutableStateOf(allergyType)}
    var isCheckedPolen by remember { mutableStateOf(false) }
    var showConfirmation by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ){

    }
    if (showConfirmation) {
        Text(
            text = "Tu alergia ha sido registrada correctamente",
            modifier = Modifier
                .padding(16.dp)
        )
    }
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it}) {
            TextField(
                value = allergy,
                onValueChange = {newValue ->
                    allergy = newValue
                },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )

        ExposedDropdownMenu(
            expanded = isExpanded ,
            onDismissRequest = { isExpanded = false }
        ) {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                text = {
                    TextFieldWithCheckbox(
                        text = "Polen",
                        isChecked = isCheckedPolen,
                        onCheckedChange = { newValue ->
                            isCheckedPolen = newValue
                            if (newValue) {
                                allergy = "Polen"
                                isExpanded = false
                            }
                        },
                        onDialogOpen = {  }
                    )
                       },
                onClick = {
                    allergy = "Polen"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    TextFieldWithCheckbox(
                        text = "Ácaros",
                        isChecked = false,
                        onCheckedChange = { },
                        onDialogOpen = {  }
                    )
                },
                onClick = {
                    allergy = "Ácaros"
                    isExpanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    TextFieldWithCheckbox(
                        text = "Picaduras de avispa",
                        isChecked = false,
                        onCheckedChange = { },
                        onDialogOpen = {  }
                    )
                },
                onClick = {
                    allergy = "Picaduras de avispa"
                    isExpanded = false
                }
            )
        }
    }
}

@Composable
@Preview
fun myDropDownMenu_Preview(){
    myDropDownMenu("Alergia medicamentos")
}
@Composable
fun TextFieldWithCheckbox(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDialogOpen: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onDialogOpen() }
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Composable
fun DropDownTextFieldForAlergies(
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
            AlergiesOptionsDialog(
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
fun AlergiesOptionsDialog(
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var showConfirmation by remember { mutableStateOf(false) }
    var showEdiTextAlergie by remember { mutableStateOf(false) }
    var etAlergy by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    var resetDropDownMenus by remember { mutableStateOf(false) }

    LaunchedEffect(showConfirmation) {
        if (showConfirmation) {
            scope.launch {
                delay(3000) // Espera durante 3 segundos
                showConfirmation = false
            }
        }
    }

        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Selecciona tus alergias",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.align(Alignment.Top)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cerrar",
                            tint = Color.Gray
                        )
                    }

                }
            },
            shape = MaterialTheme.shapes.medium,
            text = {
                Box(
                    modifier = Modifier.height(450.dp)
                ) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    if (showConfirmation) {
                        Text(
                            text = "Tu alergia ha sido registrada correctamente",
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center
                        )
                    }
                    if (showEdiTextAlergie && !showConfirmation) {
                        EditTextField(
                            value = etAlergy,
                            onValueChange = { newValue ->
                                etAlergy = newValue
                            },
                            hint = "Tengo alergia a:"
                        )
                    }

                    options.forEach { allergy ->
                        myDropDownMenu(allergyType = allergy)
                    }

                    Button(
                        onClick = {
                            showConfirmation = false
                            showEdiTextAlergie = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.grisOscuroskyBlue),
                            contentColor = Color.White
                        ) //

                    ) {
                        Text(text = "Añadir otro tipo de alergia")
                    }

                    Button(
                        onClick = {
                            showConfirmation = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = colorResource(id = R.color.grisOscuroskyBlue)
                        ),
                        border = BorderStroke(1.dp, colorResource(id = R.color.grisOscuroskyBlue))

                    ) {
                        Text(
                            text = "Guardar"
                        )
                    }

                }
                }
            },
            confirmButton = {},
            dismissButton = {}
        )

}
@Preview
@Composable
fun AlergiesOptionsDialogPreview() {
    val allergyOptions = listOf("Polen", "Ácaros", "Picaduras de avispa", "Otros")

    AlergiesOptionsDialog(
        options = allergyOptions,
        onOptionSelected = {},
        onDismissRequest = {}
    )
}
