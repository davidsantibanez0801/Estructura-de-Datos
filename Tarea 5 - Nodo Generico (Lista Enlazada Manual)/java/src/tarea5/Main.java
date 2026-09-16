package tarea5;

/**
 * Main.java
 * ------------------------------------------------------------
 * Se construye manualmente, usando SOLO referencias entre
 * objetos Nodo (nada de ArrayList, arreglos ni colecciones de
 * Java para representar la lista)
 * ------------------------------------------------------------
 */
public class Main {

    public static void main(String[] args) {

        Nodo<String> nodoAl = new Nodo<>("Al");
        Nodo<String> nodoB  = new Nodo<>("B");
        Nodo<String> nodoC  = new Nodo<>("C");
        Nodo<String> nodoDe = new Nodo<>("De");
        Nodo<String> nodoMc = new Nodo<>("Mc");
        Nodo<String> nodoZi = new Nodo<>("Zi");

        // Se enlazan uno con otro, tal como en DemoNodo:
        // head.setSiguiente(...) / .getSiguiente().setSiguiente(...)
        nodoAl.setSiguiente(nodoB);
        nodoB.setSiguiente(nodoC);
        nodoC.setSiguiente(nodoDe);
        nodoDe.setSiguiente(nodoMc);
        nodoMc.setSiguiente(nodoZi);
        // nodoZi.getSiguiente() queda en null: es el ultimo nodo.

        Nodo<String> head = nodoAl;

        // ==========================================================
        // 2) Estado inicial completo de la lista
        // ==========================================================
        System.out.println("=== 1) Estado inicial completo de la lista ===");
        System.out.println(head); // usa el toString() de Nodo (recursivo)
        System.out.print("Version simplificada: ");
        imprimirListaSimplificada(head);
        System.out.println();

        // ==========================================================
        // 3) Solo el dato del primer nodo
        // ==========================================================
        System.out.println("=== 2) Dato del primer nodo ===");
        System.out.println(head.getDato());
        System.out.println();

        // ==========================================================
        // 4) Estado completo del nodo en la ultima posicion
        // ==========================================================
        System.out.println("=== 3) Estado completo del ultimo nodo ===");
        Nodo<String> ultimoNodo = obtenerUltimoNodo(head);
        System.out.println(ultimoNodo);
        System.out.println();

        // ==========================================================
        // 5) Insertar "Fe" entre "De" y "Mc"
        // ==========================================================
        System.out.println("=== 4) Insertar \"Fe\" entre \"De\" y \"Mc\" ===");
        Nodo<String> nodoDeEncontrado = buscarNodo(head, "De");
        Nodo<String> nodoFe = new Nodo<>("Fe");

        nodoFe.setSiguiente(nodoDeEncontrado.getSiguiente()); // Fe -> Mc
        nodoDeEncontrado.setSiguiente(nodoFe);                // De -> Fe

        System.out.println("Nuevo estado de la lista:");
        System.out.println(head);
        System.out.print("Version simplificada: ");
        imprimirListaSimplificada(head);
        System.out.println();

        // ==========================================================
        // 6) Insertar "Zz" al final de la lista
        // ==========================================================
        System.out.println("=== 5) Insertar \"Zz\" al final de la lista ===");
        Nodo<String> nodoZz = new Nodo<>("Zz");
        Nodo<String> ultimoActual = obtenerUltimoNodo(head);
        ultimoActual.setSiguiente(nodoZz);

        System.out.println("Nuevo estado de la lista:");
        System.out.println(head);
        System.out.print("Version simplificada: ");
        imprimirListaSimplificada(head);
        System.out.println();

        // ==========================================================
        // 7) Insertar "Aa" al inicio de la lista
        // ==========================================================
        System.out.println("=== 6) Insertar \"Aa\" al inicio de la lista ===");
        Nodo<String> nodoAa = new Nodo<>("Aa");
        nodoAa.setSiguiente(head); // Aa -> (antiguo head, Al)
        head = nodoAa;             // Aa se vuelve el nuevo primer nodo

        System.out.println("Estado final de la lista:");
        System.out.println(head);
        System.out.print("Version simplificada: ");
        imprimirListaSimplificada(head);
    }

    // ------------------------------------------------------------------
    // Métodos auxiliares: recorren la lista SOLO con referencias Nodo,
    // sin usar arreglos, ArrayList ni ninguna colección de Java.
    // ------------------------------------------------------------------

    /** Recorre la lista desde 'inicio' hasta encontrar el nodo cuyo dato sea 'valorBuscado'. */
    private static <T> Nodo<T> buscarNodo(Nodo<T> inicio, T valorBuscado) {
        Nodo<T> actual = inicio;
        while (actual != null) {
            if (actual.getDato().equals(valorBuscado)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null; // no se encontro
    }

    /** Recorre la lista desde 'inicio' hasta el nodo cuyo siguiente es null (el ultimo). */
    private static <T> Nodo<T> obtenerUltimoNodo(Nodo<T> inicio) {
        Nodo<T> actual = inicio;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    /** Imprime la lista como "Al -> B -> C -> ..." en vez del toString anidado de Nodo. */
    private static <T> void imprimirListaSimplificada(Nodo<T> inicio) {
        StringBuilder texto = new StringBuilder();
        Nodo<T> actual = inicio;
        while (actual != null) {
            texto.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                texto.append(" -> ");
            }
            actual = actual.getSiguiente();
        }
        System.out.println(texto);
    }
}
