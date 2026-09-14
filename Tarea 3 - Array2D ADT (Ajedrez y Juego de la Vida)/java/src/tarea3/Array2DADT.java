package tarea3;

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
