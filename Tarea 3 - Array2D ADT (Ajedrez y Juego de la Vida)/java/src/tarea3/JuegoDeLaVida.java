package tarea3;

/**
 * JuegoDeLaVida.java
 * ------------------------------------------------------------
 * Problema B de la tarea: el clásico "Juego de la Vida" de
 * Conway, implementado sobre un Array2DADT<Boolean> (true =
 * célula viva, false = célula muerta).
 *
 * Reglas aplicadas en calcularSiguienteGeneracion():
 *   1) Célula viva con 2 o 3 vecinos vivos -> sobrevive.
 *   2) Célula viva con 0 o 1 vecinos vivos -> muere (soledad).
 *   3) Célula viva con 4 o más vecinos vivos -> muere (sobrepoblación).
 *   4) Célula muerta con exactamente 3 vecinos vivos -> nace.
 *      El resto de células muertas permanecen muertas.
 *
 * IMPORTANTE (supuesto de diseño): el tablero tiene bordes FIJOS,
 * es decir, NO es un tablero toroidal/circular. Una célula en la
 * orilla simplemente tiene menos vecinos posibles (los que caen
 * fuera de la cuadrícula no cuentan). Esta es la variante más
 * común de la implementación del juego cuando no se especifica
 * lo contrario.
 * ------------------------------------------------------------
 */
public class JuegoDeLaVida {

    private Array2DADT<Boolean> tablero;
    private final int renglones;
    private final int columnas;

    public JuegoDeLaVida(boolean[][] poblacionInicial) {
        this.renglones = poblacionInicial.length;
        this.columnas = poblacionInicial[0].length;
        this.tablero = new Array2DADT<>(renglones, columnas);

        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero.insertarElemento(i, j, poblacionInicial[i][j]);
            }
        }
    }

    /**
     * Calcula la siguiente generación y la deja como el estado actual.
     * Se construye un tablero NUEVO con los resultados: todas las
     * células se evalúan contra el estado ACTUAL (no el que se va
     * modificando a medias), tal como exige el juego de la vida.
     */
    public void calcularSiguienteGeneracion() {
        Array2DADT<Boolean> siguiente = new Array2DADT<>(renglones, columnas);

        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                boolean estaViva = tablero.obtenerElemento(i, j);
                int vecinosVivos = contarVecinosVivos(i, j);

                boolean nuevoEstado;
                if (estaViva && (vecinosVivos == 2 || vecinosVivos == 3)) {
                    nuevoEstado = true;                 // Regla 1: sobrevive
                } else if (estaViva && vecinosVivos <= 1) {
                    nuevoEstado = false;                // Regla 2: muere por soledad
                } else if (estaViva && vecinosVivos >= 4) {
                    nuevoEstado = false;                // Regla 3: muere por sobrepoblación
                } else if (!estaViva && vecinosVivos == 3) {
                    nuevoEstado = true;                 // Regla 4: nace
                } else {
                    nuevoEstado = estaViva;              // Célula muerta que sigue muerta
                }

                siguiente.insertarElemento(i, j, nuevoEstado);
            }
        }

        this.tablero = siguiente;
    }

    /** Cuenta cuántas de las (hasta) 8 celdas vecinas están vivas. */
    private int contarVecinosVivos(int fila, int columna) {
        int contador = 0;
        for (int deltaFila = -1; deltaFila <= 1; deltaFila++) {
            for (int deltaColumna = -1; deltaColumna <= 1; deltaColumna++) {
                if (deltaFila == 0 && deltaColumna == 0) {
                    continue; // no contarse a sí misma
                }
                int filaVecina = fila + deltaFila;
                int columnaVecina = columna + deltaColumna;
                if (tablero.indiceValido(filaVecina, columnaVecina)
                        && tablero.obtenerElemento(filaVecina, columnaVecina)) {
                    contador++;
                }
            }
        }
        return contador;
    }

    /** Imprime el tablero actual: 'O' célula viva, '.' célula muerta. */
    public void imprimir() {
        for (int i = 0; i < renglones; i++) {
            StringBuilder linea = new StringBuilder();
            for (int j = 0; j < columnas; j++) {
                boolean viva = tablero.obtenerElemento(i, j);
                linea.append(viva ? 'O' : '.').append(' ');
            }
            System.out.println(linea);
        }
    }

    public int getRenglones() {
        return renglones;
    }

    public int getColumnas() {
        return columnas;
    }
}
