package tarea3;

/**
 * Array2DADT.java
 * ------------------------------------------------------------
 * ADT genérico para una matriz de 2 dimensiones (renglones x
 * columnas), del mismo estilo que el ArrayADT<T> visto en clase
 * para 1 dimensión (mismo patrón: envolver un arreglo interno de
 * Object[] y exponer operaciones seguras con validación de
 * rango).
 *
 * Esta clase NO nos la dieron en clase — la construimos nosotros
 * siguiendo el mismo diseño del ArrayADT de la tarea anterior,
 * pero para 2 dimensiones, porque ambos problemas de esta tarea
 * (tablero de ajedrez y juego de la vida) necesitan una
 * cuadrícula, no una sola fila.
 * ------------------------------------------------------------
 */
public class Array2DADT<T> {

    private final int renglones;
    private final int columnas;
    private final Object[][] datos;

    public Array2DADT(int renglones, int columnas) {
        this.renglones = renglones;
        this.columnas = columnas;
        this.datos = new Object[renglones][columnas];
    }

    @SuppressWarnings("unchecked")
    public T obtenerElemento(int renglon, int columna) {
        if (indiceValido(renglon, columna)) {
            return (T) datos[renglon][columna];
        } else {
            System.out.println("Indice fuera de rango");
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void insertarElemento(int renglon, int columna, T elemento) {
        if (indiceValido(renglon, columna)) {
            datos[renglon][columna] = elemento;
        } else {
            System.out.println("Indice fuera de rango");
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int renglones() {
        return renglones;
    }

    public int columnas() {
        return columnas;
    }

    public void rellenar(T elemento) {
        for (int i = 0; i < renglones; i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = elemento;
            }
        }
    }

    /** Verifica que un par (renglon, columna) exista dentro de la matriz. */
    public boolean indiceValido(int renglon, int columna) {
        return renglon >= 0 && renglon < renglones && columna >= 0 && columna < columnas;
    }

    /** Impresión genérica (fila por fila), igual de simple que la del ArrayADT 1D. */
    public void imprimir() {
        for (int i = 0; i < renglones; i++) {
            System.out.print("[");
            for (int j = 0; j < columnas; j++) {
                System.out.print(datos[i][j] + ",");
            }
            System.out.println("]");
        }
    }
}
