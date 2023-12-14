package com.example.simondice

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
 * Clase que almacena los datos del juego.
 * @property ronda Número de ronda actual.
 * @property secuencia Secuencia de colores.
 * @property secuenciaUsuario Secuencia de colores que ha introducido el usuario.
 *@property record Record de puntuación.
 * @property estado Estado del juego.
 */
object Datos {
    var ronda by mutableStateOf(0);
    var secuencia = mutableListOf<Int>()
    var secuenciaUsuario = mutableListOf<Int>()
    var record = 0
    var estado = Estado.INICIO






}

/**
 * Enum con los estados del juego.
 */
enum class Estado {
    INICIO,
    SECUENCIA,
    ESPERANDO,
    ENTRADA,
    COMPROBANDO,
    FINALIZADO
}
/**
 * Colores utilizados en el juego.
 */
enum class Colores(val color: MutableState<Color>,var shiny1 : MutableState<Boolean> = mutableStateOf(false),var shiny2 : MutableState<Boolean> = mutableStateOf(false)){

    AZUL(color= mutableStateOf(Color.Blue)),
    VERDE(color= mutableStateOf(Color.Green)),
    ROJO(color= mutableStateOf(Color.Red)),
    AMARILLO(color= mutableStateOf(Color.Yellow))
}