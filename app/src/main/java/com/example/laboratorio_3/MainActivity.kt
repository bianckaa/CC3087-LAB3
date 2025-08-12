package com.example.laboratorio_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
    var nuevaTarea by remember { mutableStateOf("") }
    val tareas = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nuevaTarea,
            onValueChange = { nuevaTarea = it },
            label = { Text("Escribe una tarea") },
            modifier = Modifier.width(300.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (nuevaTarea.isNotBlank()) {
                    tareas.add(nuevaTarea.trim())
                    nuevaTarea = ""
                }
            },
            modifier = Modifier.width(150.dp)
        ) {
            Text(stringResource(R.string.agregar_tarea),
                fontWeight = FontWeight.Bold, 
                fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(tareas) { tarea ->
                Text(
                    text = tarea,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                )
            }
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
