package com.example.simondice


import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun IU(VM: VM = VM()) {

    var textoBoton by remember { mutableStateOf("Start") }
    val iuScope = rememberCoroutineScope()
    val shineAlpha by animateColorAsState(
        targetValue = Color.Gray,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        Log.d("Tag", "Estoy en animateColorAsState").toString()
    )
    val shineAlpha2 by animateColorAsState(
        targetValue = Color.Magenta,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        Log.d("Tag", "Estoy en animateColorAsState2").toString()

    )


    Column(
        modifier = Modifier
            .size(32.dp)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Ronda",
                fontSize = 35.sp,
            )
            Text(
                text = "Record",
                fontSize = 35.sp,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${Datos.ronda}",
                color = Color.DarkGray,
                fontSize = 25.sp,
            )
            Text(
                text = "${Datos.record}",
                color = Color.DarkGray,
                fontSize = 25.sp,
            )
        }


        // Fila 1: Botones Azul y Verde
        //Boton Azul
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botón Azul
            Button(
                onClick = {
                    iuScope.launch {
                        if (Datos.estado == Estado.ENTRADA || Datos.estado == Estado.ESPERANDO) {
                            Log.d("Tag", "Estoy en Onclick boton azul")
                            VM.aumentarSecuenciaUsuario(Colores.AZUL.ordinal)
                            Colores.AZUL.shiny2.value = true
                            delay(500)
                            Colores.AZUL.shiny2.value = false
                        }
                    }
                },
                modifier = Modifier
                    .size(64.dp)
                    .background(if (Colores.AZUL.shiny1.value) shineAlpha else if (Colores.AZUL.shiny2.value) shineAlpha2 else Colores.AZUL.color.value),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {

            }

            // Botón Verde
            Button(

                onClick = {
                    iuScope.launch {
                        if (Datos.estado == Estado.ENTRADA || Datos.estado == Estado.ESPERANDO) {
                            Log.d("Tag", "Estoy en boton verde")
                            VM.aumentarSecuenciaUsuario(Colores.VERDE.ordinal)
                            Colores.VERDE.shiny2.value = true
                            delay(500)
                            Colores.VERDE.shiny2.value = false
                        }
                    }
                },
                modifier = Modifier
                    .size(64.dp)
                    .background(if (Colores.VERDE.shiny1.value) shineAlpha else if (Colores.VERDE.shiny2.value) shineAlpha2 else Colores.VERDE.color.value),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {

            }
        }

        // Fila 2: Botones Rojo y Amarillo
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botón Rojo
            Button(
                onClick = {
                    iuScope.launch {
                        if (Datos.estado == Estado.ENTRADA || Datos.estado == Estado.ESPERANDO) {
                            Log.d("Tag", "Estoy en Onclick boton rojo")
                            VM.aumentarSecuenciaUsuario(Colores.ROJO.ordinal)

                            Colores.ROJO.shiny2.value = true
                            delay(500)
                            Colores.ROJO.shiny2.value = false
                        }
                    }
                },
                modifier = Modifier
                    .size(64.dp)
                    .background(if (Colores.ROJO.shiny1.value) shineAlpha else if (Colores.ROJO.shiny2.value) shineAlpha2 else Colores.ROJO.color.value),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {

            }

            // Botón Amarillo
            Button(
                onClick = {
                    iuScope.launch {
                        if (Datos.estado == Estado.ENTRADA || Datos.estado == Estado.ESPERANDO) {
                            Log.d("Tag", "Estoy en Onclick boton amarillo")
                            VM.aumentarSecuenciaUsuario(Colores.AMARILLO.ordinal)
                            Colores.AMARILLO.shiny2.value = true
                            delay(500)
                            Colores.AMARILLO.shiny2.value = false
                        }
                    }
                },
                modifier = Modifier
                    .size(64.dp)
                    .background(if (Colores.AMARILLO.shiny1.value) shineAlpha else if (Colores.AMARILLO.shiny2.value) shineAlpha2 else Colores.AMARILLO.color.value),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {

            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            Button(
                onClick = {
                    if (textoBoton == "Start") {
                        VM.inicializarJuego()
                        textoBoton = "Reset"

                    } else {
                        VM.reiniciarJuego()
                        textoBoton = "Start"
                    }
                },
                modifier = Modifier


                    .height(64.dp)
            )
            {
                androidx.compose.material3.Text(textoBoton)
            }



                Button(
                    onClick = {
                        if (Datos.estado == Estado.ENTRADA) {
                            VM.comprobarSecuencia()
                        }
                    },
                    modifier = Modifier
                        .height(64.dp)
                        .background(Color.Cyan),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable._04729),
                        contentDescription = "Sumar una ronda",
                        modifier = Modifier
                            .size(48.dp)

                    )



            }

        }
    }
}

