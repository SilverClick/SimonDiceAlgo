package com.example.simondice

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * View Model del juego.
 */
class VM: ViewModel() {
    /**
     * Contexto de la aplicación.
     */

    fun mostrarMensajeToast(mensaje: String) {
        Toast.makeText(MainActivity.context, mensaje, Toast.LENGTH_SHORT).show()
    }





    /**
     * Función que genera un número aleatorio entre 0 y un número menos del máximo.
     * @param maximo Número máximo que se puede generar.
     * @return Número aleatorio entre 0 y el máximo.
     */
    fun generarNumeroAleatorio(maximo:Int): Int {
        return (0..maximo-1).random()
    }
    /**
     * Inicializo el juego.
     */
    fun inicializarJuego() {
        reiniciarRonda()
        reiniciarSecuencia()
        reiniciarSecuenciaUsuario()
        Datos.estado = Estado.INICIO
        Datos.ronda++
        estadoSecuencia()
    }

    /**
     * Aumenta secuencia de colores.
     * Muestra al usuario el color.
     */

    fun estadoSecuencia() {
        Datos.estado = Estado.SECUENCIA
        aumentarSecuencia()
        mostrarSecuencia()
        Datos.estado = Estado.ESPERANDO
    }
    /**
     * Aumenta un color a la secuencia
     */
    fun aumentarSecuencia() {
        Datos.estado = Estado.SECUENCIA
        Datos.secuencia.add(generarNumeroAleatorio(4))

    }


    /**
     * Aumentar la secuencia del usuario.
     */
    fun aumentarSecuenciaUsuario(color: Int) {
        viewModelScope.launch {
            Datos.estado = Estado.ENTRADA
            Datos.secuenciaUsuario.add(color)
        }
    }
    /**
     * Comprueba si la secuencia introducida por el usuario es correcta.
     * @return True si es correcto, false si no lo es.
     */
    fun comprobarSecuencia(){
        Datos.estado = Estado.COMPROBANDO
        if (Datos.secuencia == Datos.secuenciaUsuario) {
            Datos.ronda++
            Log.d("Tag", "Has ganado esta ronda")
            mostrarMensajeToast( "Has ganado esta ronda")
            if (Datos.ronda > Datos.record) {
                Datos.record = Datos.ronda
            }
            reiniciarSecuenciaUsuario()
            aumentarSecuencia()
            mostrarSecuencia()
            Datos.estado = Estado.ESPERANDO
        } else {
            Datos.estado = Estado.FINALIZADO
            mostrarMensajeToast( "Has perdido")
            Log.d("Tag", "Has perdido")
        }

    }

    /**
     * Reiniciar el juego
     */
    fun reiniciarJuego() {
        reiniciarRonda()
        reiniciarSecuencia()
        reiniciarSecuenciaUsuario()
        Datos.estado = Estado.INICIO
    }

    /**
     * Método para mostrar la secuencia.
     */
    fun mostrarSecuencia() {
        viewModelScope.launch {
            var repeticiones = 0
            var colorAnterior: Int? = null

            for (i in Datos.secuencia) {
                if (i == colorAnterior) {
                    repeticiones++
                    if (repeticiones >=2) {
                        // Espera extra si se repite dos veces seguidas el mismo color
                        delay(500) // Puedes ajustar el tiempo de espera según tus necesidades
                    }
                } else {
                    repeticiones = 1
                }

                when (i) {
                    Colores.ROJO.ordinal -> {
                        Colores.ROJO.shiny1.value = true
                        Log.d("corutina", "Rojo")
                        delay(1000)
                        Colores.ROJO.shiny1.value = false
                    }
                    Colores.AZUL.ordinal -> {
                        Colores.AZUL.shiny1.value = true
                        Log.d("corutina", "Azul")
                        delay(1000)
                        Colores.AZUL.shiny1.value = false
                    }
                    Colores.VERDE.ordinal -> {
                        Colores.VERDE.shiny1.value = true
                        Log.d("corutina", "Verde")
                        delay(1000)
                        Colores.VERDE.shiny1.value = false
                    }
                    Colores.AMARILLO.ordinal -> {
                        Colores.AMARILLO.shiny1.value = true
                        Log.d("corutina", "Amarillo")
                        delay(1000)
                        Colores.AMARILLO.shiny1.value = false
                    }
                }

                colorAnterior = i
            }
        }
    }





    /**
     * Función que reinicia la secuencia del usuario.
     */
    fun reiniciarSecuenciaUsuario() {
        Datos.secuenciaUsuario = mutableListOf<Int>()
    }

    /**
     * Función que reinicia la secuencia.
     */
    fun reiniciarSecuencia() {
        Datos.secuencia = mutableListOf<Int>()
    }

    /**
     * Función que reinicia la ronda.
     */

    fun reiniciarRonda() {
        Datos.ronda = 0
    }





}







