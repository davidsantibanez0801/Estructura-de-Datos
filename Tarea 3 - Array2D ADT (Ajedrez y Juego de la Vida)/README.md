# Tarea 3 - Array2D ADT (Ajedrez y Juego de la Vida)

Dos problemas resueltos con un **ADT bidimensional propio** (`Array2DADT<T>`),
construido siguiendo el mismo estilo del `ArrayADT<T>` de 1 dimensión visto
en clase, pero para matrices.

## Problema A — Tablero de ajedrez (no funcional)

Un `Array2DADT<Character>` de 8x8 que guarda el carácter Unicode de cada
pieza en su posición inicial (bloque "Chess Symbols", U+2654-U+265F). El
programa **solo imprime la posición inicial** — no hay movimientos, no hay
turnos, no es jugable.

## Problema B — Juego de la Vida

Un `Array2DADT<Boolean>` representa la cuadrícula (`true` = célula viva). La
población inicial se lee de un **archivo CSV** (matriz de 0s y 1s). Se
implementan las 4 reglas de Conway y el programa calcula y muestra **10
generaciones**.

Reglas aplicadas:
1. Célula viva con 2 o 3 vecinos vivos → sobrevive.
2. Célula viva con 0 o 1 vecinos vivos → muere (soledad).
3. Célula viva con 4 o más vecinos vivos → muere (sobrepoblación).
4. Célula muerta con exactamente 3 vecinos vivos → nace.

**Supuesto de diseño:** el tablero tiene bordes fijos (no es
toroidal/circular); una célula en la orilla simplemente tiene menos vecinos
posibles.

## Arquitectura

| Archivo | Clase | Rol |
|---|---|---|
| `Array2DADT.java` | `Array2DADT<T>` | ADT genérico 2D (construido para esta tarea) |
| `TableroAjedrez.java` | `TableroAjedrez` | Arma y muestra la posición inicial |
| `MainAjedrez.java` | `MainAjedrez` | Punto de entrada del Problema A |
| `JuegoDeLaVida.java` | `JuegoDeLaVida` | Motor de reglas y cálculo de generaciones |
| `LectorPoblacion.java` | `LectorPoblacion` | Lee el CSV de población inicial |
| `MainJuegoVida.java` | `MainJuegoVida` | Punto de entrada del Problema B |

Todas las clases están en el paquete `tarea3` (para no chocar con los `Main`
de las demás tareas del repositorio).

## Archivo de ejemplo: `../poblacion_inicial.csv`

Contiene el patrón clásico **"glider"** en una cuadrícula de 10x10 — un
patrón de 5 células que se desplaza en diagonal, ideal para comprobar
visualmente que las reglas de nacimiento/muerte están bien implementadas.
Puedes reemplazarlo por cualquier otra matriz de 0s y 1s del mismo formato.

## Evidencias

Ver [`evidencias/Evidencias_Java.pdf`](./evidencias/Evidencias_Java.pdf).
