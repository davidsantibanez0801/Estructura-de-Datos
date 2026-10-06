package tarea7;

public class ListaLigadaADT<T> {

    private Nodo<T> head;

    public ListaLigadaADT() {
        this.head = null;
    }

    /** Agrega un dato al final de la lista. */
    public void agregar(T dato) {
        if (head == null) {
            this.head = new Nodo<>(dato);
        } else {
            Nodo<T> actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(new Nodo<>(dato));
        }
    }

    /** Recorre la lista imprimiendo cada dato. Se agrega salto de línea final. */
    public void transversal() {
        if (head == null) {
            System.out.println("Vacia");
        } else {
            Nodo<T> actual = head;
            do {
                System.out.print("|" + actual.getDato());
                actual = actual.getSiguiente();
            } while (actual != null);
            System.out.println("|");
        }
    }

    /**
     * Reemplaza el primer dato igual a aBuscar por nuevoValor.
     */
    public void actualizar(T aBuscar, T nuevoValor) {
        if (head == null) {
            System.out.println("Vacia");
        } else {
            Nodo<T> actual = buscarNodo(aBuscar);
            if (actual == null) {
                System.out.println("No se encontro: " + aBuscar);
            } else {
                actual.setDato(nuevoValor);
            }
        }
    }

    /** Regresa la cantidad de nodos de la lista. */
    public int getTamanio() {
        int contador = 0;
        if (head == null) {
            return contador;
        } else {
            Nodo<T> actual = head;
            do {
                contador++;
                actual = actual.getSiguiente();
            } while (actual != null);
            return contador;
        }
    }

    /**
     * Inserta valor justo después del primer nodo igual a referencia.
     * (Corrección: se evita NullPointerException si la referencia no existe.)
     */
    public void agregarDespuesDe(T referencia, T valor) {
        if (head == null) {
            System.out.println("Vacia");
        } else {
            Nodo<T> actual = buscarNodo(referencia);
            if (actual == null) {
                System.out.println("No se encontro la referencia: " + referencia);
            } else {
                Nodo<T> nuevoNodo = new Nodo<>(valor, actual.getSiguiente());
                actual.setSiguiente(nuevoNodo);
            }
        }
    }

    /** @return true si la lista no tiene nodos. */
    public boolean estaVacia() {
        return head == null;
    }

    /** Agrega un dato al inicio de la lista (nuevo head). */
    public void agregarAlInicio(T dato) {
        head = new Nodo<>(dato, head);
    }

    /**
     * Inserta valor justo antes del primer nodo igual a referencia.
     * @return true si se insertó, false si la referencia no existe.
     */
    public boolean agregarAntesDe(T referencia, T valor) {
        if (head == null) {
            return false;
        }
        if (head.getDato().equals(referencia)) {
            agregarAlInicio(valor);
            return true;
        }
        Nodo<T> anterior = head;
        while (anterior.getSiguiente() != null
                && !anterior.getSiguiente().getDato().equals(referencia)) {
            anterior = anterior.getSiguiente();
        }
        if (anterior.getSiguiente() == null) {
            return false;
        }
        anterior.setSiguiente(new Nodo<>(valor, anterior.getSiguiente()));
        return true;
    }

    /**
     * Inserta un dato en la posición indicada (0 = inicio, tamaño = final).
     * @return true si el índice era válido.
     */
    public boolean insertarEn(int indice, T dato) {
        if (indice < 0 || indice > getTamanio()) {
            return false;
        }
        if (indice == 0) {
            agregarAlInicio(dato);
            return true;
        }
        Nodo<T> anterior = head;
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }
        anterior.setSiguiente(new Nodo<>(dato, anterior.getSiguiente()));
        return true;
    }

    /** @return true si existe un dato igual a buscado. */
    public boolean contiene(T buscado) {
        return buscarNodo(buscado) != null;
    }

    /** @return posición (desde 0) del primer dato igual a buscado, o -1. */
    public int indiceDe(T buscado) {
        int indice = 0;
        Nodo<T> actual = head;
        while (actual != null) {
            if (actual.getDato().equals(buscado)) {
                return indice;
            }
            actual = actual.getSiguiente();
            indice++;
        }
        return -1;
    }

    /** @return dato en la posición indicada, o null si el índice es inválido. */
    public T obtener(int indice) {
        if (indice < 0) {
            return null;
        }
        Nodo<T> actual = head;
        int i = 0;
        while (actual != null && i < indice) {
            actual = actual.getSiguiente();
            i++;
        }
        return (actual == null) ? null : actual.getDato();
    }

    /**
     * Elimina el primer nodo cuyo dato sea igual a aEliminar.
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminar(T aEliminar) {
        if (head == null) {
            return false;
        }
        if (head.getDato().equals(aEliminar)) {
            head = head.getSiguiente();
            return true;
        }
        Nodo<T> anterior = head;
        while (anterior.getSiguiente() != null
                && !anterior.getSiguiente().getDato().equals(aEliminar)) {
            anterior = anterior.getSiguiente();
        }
        if (anterior.getSiguiente() == null) {
            return false;
        }
        anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
        return true;
    }

    /** Elimina el primer nodo. @return el dato eliminado o null si estaba vacía. */
    public T eliminarPrimero() {
        if (head == null) {
            return null;
        }
        T dato = head.getDato();
        head = head.getSiguiente();
        return dato;
    }

    /** Elimina el último nodo. @return el dato eliminado o null si estaba vacía. */
    public T eliminarUltimo() {
        if (head == null) {
            return null;
        }
        if (head.getSiguiente() == null) {
            T dato = head.getDato();
            head = null;
            return dato;
        }
        Nodo<T> anterior = head;
        while (anterior.getSiguiente().getSiguiente() != null) {
            anterior = anterior.getSiguiente();
        }
        T dato = anterior.getSiguiente().getDato();
        anterior.setSiguiente(null);
        return dato;
    }

    /** Elimina todos los nodos. */
    public void vaciar() {
        head = null;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "Vacia";
        }
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = head;
        while (actual != null) {
            sb.append("|").append(actual.getDato());
            actual = actual.getSiguiente();
        }
        return sb.append("|").toString();
    }

    // ------------------------------------------------------------------
    // AUXILIAR PRIVADO
    // ------------------------------------------------------------------

    /** Regresa el primer nodo cuyo dato sea igual a buscado, o null. */
    private Nodo<T> buscarNodo(T buscado) {
        Nodo<T> actual = head;
        while (actual != null && !actual.getDato().equals(buscado)) {
            actual = actual.getSiguiente();
        }
        return actual;
    }
}
