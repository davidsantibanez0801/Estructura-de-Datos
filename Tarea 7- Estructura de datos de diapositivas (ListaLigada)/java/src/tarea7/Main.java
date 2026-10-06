package tarea7;

/**
 * Demostración de la Lista Ligada genérica usando la clase PolloAsado
 * (en lugar de String). Recorre todos los métodos del TDA.
 */
public class Main {

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }

    /** Muestra la lista un elemento por renglon (mas legible que transversal). */
    private static void mostrar(ListaLigadaADT<PolloAsado> lista) {
        if (lista.estaVacia()) {
            System.out.println("  (lista vacia)");
            return;
        }
        for (int i = 0; i < lista.getTamanio(); i++) {
            System.out.println("  [" + i + "] " + lista.obtener(i));
        }
    }

    public static void main(String[] args) {
        ListaLigadaADT<PolloAsado> menu = new ListaLigadaADT<>();

        PolloAsado pierna   = new PolloAsado("Pierna",   "BBQ",         85.00);
        PolloAsado muslo    = new PolloAsado("Muslo",    "Adobada",     70.00);
        PolloAsado pechuga  = new PolloAsado("Pechuga",  "Chimichurri", 95.50);
        PolloAsado alas     = new PolloAsado("Alas",     "Picante",     60.00);
        PolloAsado entero   = new PolloAsado("Entero",   "Limon",      210.00);

        titulo("1. Lista recien creada");
        System.out.println("Esta vacia? " + menu.estaVacia());
        System.out.print("Transversal: ");
        menu.transversal();
        System.out.println("Tamanio: " + menu.getTamanio());

        titulo("2. agregar() al final");
        menu.agregar(pierna);
        menu.agregar(muslo);
        menu.agregar(pechuga);
        System.out.print("Transversal: ");
        menu.transversal();
        System.out.println("Tamanio: " + menu.getTamanio());

        titulo("3. agregarAlInicio()");
        menu.agregarAlInicio(alas);
        mostrar(menu);

        titulo("4. agregarDespuesDe(Muslo, Entero)");
        menu.agregarDespuesDe(muslo, entero);
        mostrar(menu);

        titulo("5. agregarAntesDe(Pechuga, Pierna BBQ grande)");
        PolloAsado piernaGrande = new PolloAsado("Pierna", "BBQ grande", 110.00);
        System.out.println("Insertado? " + menu.agregarAntesDe(pechuga, piernaGrande));
        mostrar(menu);

        titulo("6. insertarEn(2, Combo familiar)");
        PolloAsado combo = new PolloAsado("Combo familiar", "Mixta", 320.00);
        System.out.println("Insertado? " + menu.insertarEn(2, combo));
        System.out.println("insertarEn(99, ...) -> " + menu.insertarEn(99, combo) + " (indice invalido)");
        mostrar(menu);

        titulo("7. contiene() / indiceDe() / obtener()");
        System.out.println("contiene(Pechuga)? " + menu.contiene(pechuga));
        System.out.println("indiceDe(Pechuga) = " + menu.indiceDe(pechuga));
        System.out.println("obtener(0) = " + menu.obtener(0));
        System.out.println("obtener(50) = " + menu.obtener(50) + " (fuera de rango)");
        PolloAsado inexistente = new PolloAsado("Costilla", "Miel", 120.00);
        System.out.println("contiene(Costilla)? " + menu.contiene(inexistente));

        titulo("8. actualizar(Muslo $70.00 -> Muslo $55.00)");
        menu.actualizar(muslo, new PolloAsado("Muslo", "Adobada", 55.00));
        mostrar(menu);
        System.out.print("actualizar() de un dato inexistente: ");
        menu.actualizar(inexistente, pierna);

        titulo("9. eliminar(Entero)");
        System.out.println("Eliminado? " + menu.eliminar(entero));
        System.out.println("Eliminar de nuevo? " + menu.eliminar(entero));
        mostrar(menu);

        titulo("10. eliminarPrimero() y eliminarUltimo()");
        System.out.println("Primero eliminado: " + menu.eliminarPrimero());
        System.out.println("Ultimo eliminado:  " + menu.eliminarUltimo());
        mostrar(menu);
        System.out.println("Tamanio: " + menu.getTamanio());

        titulo("11. vaciar()");
        menu.vaciar();
        System.out.print("Transversal: ");
        menu.transversal();
        System.out.println("Esta vacia? " + menu.estaVacia());
        System.out.println("eliminarPrimero() en lista vacia = " + menu.eliminarPrimero());
        System.out.println("eliminarUltimo() en lista vacia  = " + menu.eliminarUltimo());
    }
}
