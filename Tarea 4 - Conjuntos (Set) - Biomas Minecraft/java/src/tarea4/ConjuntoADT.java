package tarea4;

import java.util.ArrayList;

/**
 * ConjuntoADT.java
 * ------------------------------------------------------------
 * Este es el ADT de Conjunto que nos dieron en clase (parcial),
 * completado para que se comporte como un conjunto matemático
 * de verdad.
 * ------------------------------------------------------------
 */
public class ConjuntoADT<T> {
    private ArrayList<T> elementos;

    public ConjuntoADT(){
        this.elementos = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "ConjuntoADT{" +
                "elementos=" + elementos +
                '}';
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<T> elementos) {
        this.elementos = elementos;
    }

    public int longitud(){
        return elementos.size();
    }

    /**
     * Agrega un elemento al conjunto, PERO SOLO SI todavía no
     * existe. Esta es la diferencia esencial entre un Conjunto
     * y una Lista: un conjunto nunca tiene elementos duplicados.
     */
    public void agregarElemento(T elemento){
        if (!contieneElemento(elemento)) {
            elementos.add(elemento);
        }
    }

    /** Quita un elemento del conjunto, si existe. */
    public void eliminarElemento(T elemento){
        elementos.remove(elemento);
    }

    public boolean contieneElemento(T elemento){
        return elementos.contains(elemento);
    }

    public boolean estaVacio() {
        return elementos.isEmpty();
    }

    /**
     * Unión: un conjunto nuevo con todos los elementos que están
     * en ESTE conjunto o en 'otro' (sin repetir ninguno).
     */
    public ConjuntoADT<T> union(ConjuntoADT<T> otro) {
        ConjuntoADT<T> resultado = new ConjuntoADT<>();
        for (T elemento : this.elementos) {
            resultado.agregarElemento(elemento);
        }
        for (T elemento : otro.elementos) {
            resultado.agregarElemento(elemento);
        }
        return resultado;
    }

    /**
     * Intersección: un conjunto nuevo solo con los elementos que
     * están en AMBOS conjuntos a la vez.
     */
    public ConjuntoADT<T> interseccion(ConjuntoADT<T> otro) {
        ConjuntoADT<T> resultado = new ConjuntoADT<>();
        for (T elemento : this.elementos) {
            if (otro.contieneElemento(elemento)) {
                resultado.agregarElemento(elemento);
            }
        }
        return resultado;
    }

    /**
     * Diferencia: un conjunto nuevo con los elementos que están
     * en ESTE conjunto pero NO en 'otro'.
     */
    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro) {
        ConjuntoADT<T> resultado = new ConjuntoADT<>();
        for (T elemento : this.elementos) {
            if (!otro.contieneElemento(elemento)) {
                resultado.agregarElemento(elemento);
            }
        }
        return resultado;
    }
}
