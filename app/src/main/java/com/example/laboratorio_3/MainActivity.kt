package com.example.laboratorio_3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio_3.ui.theme.Laboratorio_3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio_3Theme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Background()
                    ListaTareasScreen()
                }
            }
        }
    }
}

@Composable
fun ListaTareasScreen() {
    val context = LocalContext.current
    var nuevaTarea by remember { mutableStateOf("") }
    val tareas = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.titulos),
            modifier = Modifier.padding(top = 10.dp, start = 110.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = colorResource(id = R.color.navy)
        )

        LazyColumn(
            modifier = Modifier
                .width(300.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (tareas.isEmpty()) Arrangement.Top else Arrangement.Top
        ) {
            if (tareas.isEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.lista_vacia),
                        modifier = Modifier.padding(top = 58.dp),
                        color = colorResource(id = R.color.navy),
                        fontWeight = FontWeight.W300,
                    )
                }
            } else {
                itemsIndexed(tareas) { index, tarea ->
                    Text(
                        text = tarea,
                        color = colorResource(id = R.color.navy),
                        fontWeight = FontWeight.W300,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = if (index == 0) 58.dp else 8.dp,
                                bottom = 8.dp,
                                start = 4.dp,
                                end = 8.dp
                            ),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

        TextField(
            value = nuevaTarea,
            onValueChange = { nuevaTarea = it },
            placeholder = { Text(stringResource(R.string.agregar_tarea)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = ""
                )
            },
            modifier = Modifier.width(300.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (nuevaTarea.isBlank()) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_tarea_vacia),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    tareas.add("✰ ${nuevaTarea.trim()}")
                    nuevaTarea = ""
                }
            },
            modifier = Modifier
                .width(200.dp)
                .height(90.dp)
                .padding(bottom = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.teal),
                contentColor = colorResource(id = R.color.beige)
            )
        ) {
            Text(
                stringResource(R.string.agregar_tarea),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun Background() {
    // Imagen de fondo
    Image(
        painter = painterResource(id = R.drawable.fondo),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewListaTareas() {
    Laboratorio_3Theme {
        Box(modifier = Modifier.fillMaxSize()) {
            Background()
            ListaTareasScreen()
        }
    }
}
