package tarea3;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * MainAjedrez.java
 * ------------------------------------------------------------
 * Punto de entrada del Problema A. Solo crea el tablero y lo
 * imprime en su posición inicial. No hay movimientos, no hay
 * turnos, no es funcional — tal como pide el enunciado.
 * ------------------------------------------------------------
 */
public class MainAjedrez {
    public static void main(String[] args) {
        // Forzamos UTF-8 en la salida para que los símbolos de ajedrez
        // (♔ ♕ ♖ ♗ ♘ ♙ ♚ ♛ ♜ ♝ ♞ ♟) se impriman bien en cualquier consola.
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        TableroAjedrez tablero = new TableroAjedrez();
        tablero.imprimir();
    }
}
