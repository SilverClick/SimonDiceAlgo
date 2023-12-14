# Juego de Simon Dice

## Descripción General

Este repositorio contiene el código para un juego simple de Simon Dice (Simon Says) implementado en Kotlin mediante Jetpack Compose. 
El juego permite al jugador seguir una secuencia de botones de colores y luego replicar la secuencia.
El código está estructurado en un componente de interfaz de usuario (IU) y un modelo de vista (VM) que maneja la lógica del juego.

## Interfaz de Usuario (IU)

La interfaz de usuario está implementada utilizando Jetpack Compose, un moderno conjunto de herramientas de interfaz de usuario para Android. 
Consiste en botones de colores que representan diferentes acciones y muestra información sobre la ronda actual y la puntuación más alta.

### Estructura de la IU

La IU está estructurada de la siguiente manera:

- **Visualización de Ronda y Record:** Muestra la ronda actual y la puntuación más alta.

  ```kotlin
  Text(
      text = "Ronda ${Datos.ronda}",
      fontSize = 35.sp,
  )
  Text(
      text = "Record ${Datos.record}",
      fontSize = 35.sp,
  )
  ```

- **Filas de Botones de Colores:** Divididas en dos filas, cada una conteniendo dos botones de colores. Los colores son azul, verde, rojo y amarillo.

  ```kotlin
  // Fila 1: Botones Azul y Verde
  // Botón Azul
  Button(
      onClick = {
          // Lógica para el botón azul
      },
      modifier = Modifier
          .size(64.dp)
          .background(
              if (Colores.AZUL.shiny1.value) shineAlpha
              else if (Colores.AZUL.shiny2.value) shineAlpha2
              else Colores.AZUL.color.value
          ),
      colors = ButtonDefaults.buttonColors(
          containerColor = Color.Transparent,
          contentColor = Color.White
      )
  ) {
      // Contenido del botón azul
  }

  // Botón Verde
  Button(
      onClick = {
          // Lógica para el botón verde
      },
      modifier = Modifier
          .size(64.dp)
          .background(
              if (Colores.VERDE.shiny1.value) shineAlpha
              else if (Colores.VERDE.shiny2.value) shineAlpha2
              else Colores.VERDE.color.value
          ),
      colors = ButtonDefaults.buttonColors(
          containerColor = Color.Transparent,
          contentColor = Color.White
      )
  ) {
      // Contenido del botón verde
  }
  ```

- **Fila de Botones de Control:** Contiene los botones "Iniciar/Reiniciar" y "Verificar".

  ```kotlin
  // Botón Iniciar/Reiniciar
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
  ) {
      Text(textoBoton)
  }

  // Botón Verificar
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
  ```

## Modelo de Vista (VM)

El modelo de vista (VM) maneja la lógica del juego.

### Métodos del Modelo de Vista

- **`inicializarJuego()`:** Inicia el juego, reinicia la ronda, la secuencia y el estado del juego.

  ```kotlin
  fun inicializarJuego() {
      reiniciarRonda()
      reiniciarSecuencia()
      reiniciarSecuenciaUsuario()
      Datos.estado = Estado.INICIO
      Datos.ronda++
      estadoSecuencia()
  }
  ```

- **`aumentarSecuenciaUsuario(color: Int)`:** Aumenta la secuencia del usuario con el color proporcionado.

  ```kotlin
  fun aumentarSecuenciaUsuario(color: Int) {
      viewModelScope.launch {
          Datos.estado = Estado.ENTRADA
          Datos.secuenciaUsuario.add(color)
      }
  }
  ```

- **`comprobarSecuencia()`:** Comprueba si la secuencia introducida por el usuario es correcta.

  ```kotlin
  fun comprobarSecuencia() {
      Datos.estado = Estado.COMPROBANDO
      if (Datos.secuencia == Datos.secuenciaUsuario) {
          // Lógica para secuencia correcta
      } else {
          // Lógica para secuencia incorrecta
      }
  }
  ```

### Clase Datos

La clase `Datos` almacena los datos del juego.

```kotlin
object Datos {
    var ronda by mutableStateOf(0)
    var secuencia = mutableListOf<Int>()
    var secuenciaUsuario = mutableListOf<Int>()
    var record = 0
    var estado = Estado.INICIO
}
```

- **`ronda`:** Número de la ronda actual.
- **`secuencia`:** Secuencia de colores generada por el juego.
- **`secuenciaUsuario`:** Secuencia de colores ingresada por el usuario.
- **`record`:** Puntuación más alta alcanzada.
- **`estado`:** Estado actual del juego (INICIO, SECUENCIA, ESPERANDO, ENTRADA, COMPROBANDO, FINALIZADO).

### Enumeración de Estados

```kotlin
enum class Estado {
    INICIO,
    SECUENCIA,
    ESPERANDO,
    ENTRADA,
    COMPROBANDO,
    FINALIZADO
}
```

### Enumeración de Colores

```kotlin
enum class Colores(val color: MutableState<Color>, var shiny1: MutableState<Boolean> = mutableStateOf(false), var shiny2: MutableState<Boolean> = mutableStateOf(false)) {
    AZUL(color = mutableStateOf(Color.Blue)),
    VERDE(color = mutableStateOf(Color.Green)),
    ROJO(color = mutableStateOf(Color.Red)),
    AMARILLO(color = mutableStateOf(Color.Yellow))
}
```

En resumen, este código implementa un juego de Simon Dice con una interfaz de usuario intuitiva y un modelo de vista que gestiona la lógica del juego. 
La clase `Datos` almacena la información del juego, como la secuencia actual, la secuencia del usuario y el estado del juego.