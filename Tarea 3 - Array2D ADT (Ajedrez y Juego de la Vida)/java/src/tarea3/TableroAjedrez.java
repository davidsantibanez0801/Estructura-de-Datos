package tarea3;

/**
 * TableroAjedrez.java
 * ------------------------------------------------------------
 * El tablero es un Array2DADT<Character> de 8x8, donde cada
 * casilla guarda el carácter Unicode de la pieza que le
 * corresponde en la posición inicial, o ' ' (espacio) si la
 * casilla está vacía.
 *
 * Códigos Unicode usados (bloque "Chess Symbols"):
 *   Blancas: ♔ ♕ ♖ ♗ ♘ ♙   (U+2654 - U+2659)
 *   Negras:  ♚ ♛ ♜ ♝ ♞ ♟   (U+265A - U+265F)
 * ------------------------------------------------------------
 */
public class TableroAjedrez {

    public static final int TAMANIO = 8;

    // ---------- Piezas blancas ----------
    public static final char REY_BLANCO      = '\u2654';
    public static final char REINA_BLANCA    = '\u2655';
    public static final char TORRE_BLANCA    = '\u2656';
    public static final char ALFIL_BLANCO    = '\u2657';
    public static final char CABALLO_BLANCO  = '\u2658';
    public static final char PEON_BLANCO     = '\u2659';

    // ---------- Piezas negras ----------
    public static final char REY_NEGRO      = '\u265A';
    public static final char REINA_NEGRA    = '\u265B';
    public static final char TORRE_NEGRA    = '\u265C';
    public static final char ALFIL_NEGRO    = '\u265D';
    public static final char CABALLO_NEGRO  = '\u265E';
    public static final char PEON_NEGRO     = '\u265F';

    public static final char CASILLA_VACIA = ' ';

    private final Array2DADT<Character> tablero;

    public TableroAjedrez() {
        this.tablero = new Array2DADT<>(TAMANIO, TAMANIO);
        colocarPosicionInicial();
    }

    /**
     * Coloca las 32 piezas en su posición de inicio.
     * Renglón 0 = fila 8 (piezas negras), renglón 7 = fila 1 (piezas blancas),
     * siguiendo la orientación estándar en la que se dibuja un tablero
     * (negras arriba, blancas abajo).
     */
    private void colocarPosicionInicial() {
        tablero.rellenar(CASILLA_VACIA);

        // Piezas negras (fila 8 = renglón 0)
        char[] filaTraserasNegras = {
                TORRE_NEGRA, CABALLO_NEGRO, ALFIL_NEGRO, REINA_NEGRA,
                REY_NEGRO, ALFIL_NEGRO, CABALLO_NEGRO, TORRE_NEGRA
        };
        for (int columna = 0; columna < TAMANIO; columna++) {
            tablero.insertarElemento(0, columna, filaTraserasNegras[columna]);
            tablero.insertarElemento(1, columna, PEON_NEGRO); // fila 7 = peones negros
        }

        // Piezas blancas (fila 1 = renglón 7)
        char[] filaTraserasBlancas = {
                TORRE_BLANCA, CABALLO_BLANCO, ALFIL_BLANCO, REINA_BLANCA,
                REY_BLANCO, ALFIL_BLANCO, CABALLO_BLANCO, TORRE_BLANCA
        };
        for (int columna = 0; columna < TAMANIO; columna++) {
            tablero.insertarElemento(6, columna, PEON_BLANCO);       // fila 2 = peones blancos
            tablero.insertarElemento(7, columna, filaTraserasBlancas[columna]);
        }
    }

    /**
     * Imprime el tablero en texto plano, sin líneas de cuadrícula:
     * solo el número de fila (8-1) a la izquierda, las piezas separadas
     * por espacios, y las letras de columna (a-h) al final, tal como
     * se ve un diagrama de ajedrez simple en consola.
     */
    public void imprimir() {
        for (int renglon = 0; renglon < TAMANIO; renglon++) {
            int numeroDeFila = TAMANIO - renglon; // renglon 0 -> fila 8, renglon 7 -> fila 1
            StringBuilder linea = new StringBuilder();
            linea.append(numeroDeFila).append("  ");
            for (int columna = 0; columna < TAMANIO; columna++) {
                char pieza = tablero.obtenerElemento(renglon, columna);
                linea.append(pieza).append("   ");
            }
            System.out.println(linea);
        }
        System.out.println();
        System.out.println("   a   b   c   d   e   f   g   h");
    }
}