package tarea4;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Main.java
 * ------------------------------------------------------------
 * Parte 1: prueba directa del ADT (sin la capa de negocio),
 *          para demostrar que agregarElemento() ya no permite
 *          duplicados.
 * Parte 2: el caso de uso tal cual, con un jugador explorando
 *          biomas (algunos repetidos).
 * Parte 3: dos jugadores, para demostrar union(), interseccion()
 *          y diferencia() entre dos conjuntos de biomas.
 * ------------------------------------------------------------
 */
public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        pruebaDirectaDelADT();
        pruebaCasoDeUso();
        pruebaOperacionesEntreConjuntos();
    }

    // ------------------------------------------------------------------
    // PARTE 1: prueba directa del ConjuntoADT<String>
    // ------------------------------------------------------------------
    private static void pruebaDirectaDelADT() {
        System.out.println("=== Parte 1: prueba directa de ConjuntoADT<String> ===");

        ConjuntoADT<String> biomas = new ConjuntoADT<>();
        System.out.println("Conjunto vacio? " + biomas.estaVacio());

        biomas.agregarElemento("Desierto");
        biomas.agregarElemento("Taiga");
        biomas.agregarElemento("Pantano");
        System.out.println("Despues de agregar Desierto, Taiga, Pantano -> longitud = " + biomas.longitud());

        System.out.println("Intentamos agregar 'Desierto' de nuevo (ya existe)...");
        biomas.agregarElemento("Desierto");
        System.out.println("Longitud sigue siendo = " + biomas.longitud() + " (el conjunto no acepto el duplicado)");

        System.out.println("¿Contiene 'Taiga'? " + biomas.contieneElemento("Taiga"));
        System.out.println("¿Contiene 'Oceano'? " + biomas.contieneElemento("Oceano"));

        biomas.eliminarElemento("Pantano");
        System.out.println("Despues de eliminar 'Pantano' -> " + biomas);
        System.out.println();
    }

    // ------------------------------------------------------------------
    // PARTE 2: el caso de uso completo, con mensajes de negocio
    // ------------------------------------------------------------------
    private static void pruebaCasoDeUso() {
        System.out.println("=== Parte 2: caso de uso - un jugador explorando biomas ===");

        ExploradorMinecraft steve = new ExploradorMinecraft("Steve");

        steve.visitarBioma("Desierto");
        steve.visitarBioma("Taiga");
        steve.visitarBioma("Pantano");
        steve.visitarBioma("Desierto");   // repetido a proposito, como en la diapositiva
        steve.visitarBioma("Selva");
        steve.visitarBioma("Taiga");      // repetido otra vez

        System.out.println();
        steve.mostrarResumen();
        System.out.println();
    }

    // ------------------------------------------------------------------
    // PARTE 3: dos jugadores -> union, interseccion y diferencia
    // ------------------------------------------------------------------
    private static void pruebaOperacionesEntreConjuntos() {
        System.out.println("=== Parte 3: dos jugadores - operaciones entre conjuntos ===");

        ExploradorMinecraft steve = new ExploradorMinecraft("Steve");
        steve.visitarBioma("Desierto");
        steve.visitarBioma("Taiga");
        steve.visitarBioma("Pantano");
        steve.visitarBioma("Selva");

        System.out.println();
        ExploradorMinecraft alex = new ExploradorMinecraft("Alex");
        alex.visitarBioma("Taiga");
        alex.visitarBioma("Oceano");
        alex.visitarBioma("Selva");
        alex.visitarBioma("Montaña");

        System.out.println();
        steve.mostrarResumen();
        alex.mostrarResumen();
        System.out.println();

        ConjuntoADT<String> biomasSteve = steve.getBiomasDescubiertos();
        ConjuntoADT<String> biomasAlex = alex.getBiomasDescubiertos();

        ConjuntoADT<String> todosLosBiomas = biomasSteve.union(biomasAlex);
        System.out.println("Union (todos los biomas que el servidor ha visto entre los dos): "
            + todosLosBiomas.longitud() + " -> " + todosLosBiomas.getElementos());

        ConjuntoADT<String> biomasEnComun = biomasSteve.interseccion(biomasAlex);
        System.out.println("Interseccion (biomas que AMBOS han descubierto): "
            + biomasEnComun.longitud() + " -> " + biomasEnComun.getElementos());

        ConjuntoADT<String> soloSteve = biomasSteve.diferencia(biomasAlex);
        System.out.println("Diferencia Steve - Alex (lo que Steve conoce y Alex NO): "
            + soloSteve.longitud() + " -> " + soloSteve.getElementos());

        ConjuntoADT<String> soloAlex = biomasAlex.diferencia(biomasSteve);
        System.out.println("Diferencia Alex - Steve (lo que Alex conoce y Steve NO): "
            + soloAlex.longitud() + " -> " + soloAlex.getElementos());
    }
}
