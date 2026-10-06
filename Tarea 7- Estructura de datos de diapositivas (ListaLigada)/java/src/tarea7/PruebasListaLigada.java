package tarea7;


public class PruebasListaLigada {
    public static void main(String[] args) {
        ListaLigadaADT<PolloAsado> lista = new ListaLigadaADT<>();

        PolloAsado pierna  = new PolloAsado("Pierna", "BBQ", 85.00);
        PolloAsado muslo   = new PolloAsado("Muslo", "Adobada", 70.00);
        PolloAsado pechuga = new PolloAsado("Pechuga", "Chimichurri", 95.50);
        PolloAsado alas    = new PolloAsado("Alas", "Picante", 60.00);
        PolloAsado entero  = new PolloAsado("Entero", "Limon", 210.00);

        // Lista vacia
        lista.transversal();

        // agregar
        lista.agregar(pierna);
        lista.agregar(muslo);
        lista.agregar(pechuga);
        lista.transversal();
        System.out.println("Tamaño: " + lista.getTamanio());
        System.out.println("------------");

        // actualizar
        lista.actualizar(muslo, new PolloAsado("Muslo", "Adobada", 55.00));
        lista.transversal();
        System.out.println("------------");

        // agregarAlInicio
        lista.agregarAlInicio(alas);
        lista.transversal();
        System.out.println("Tamaño: " + lista.getTamanio());
        System.out.println("------------");

        // agregarDespuesDe
        lista.agregarDespuesDe(pierna, entero);
        lista.transversal();
        System.out.println("------------");

        // agregarAntesDe
        lista.agregarAntesDe(pechuga, new PolloAsado("Pierna", "BBQ grande", 110.00));
        lista.transversal();
        System.out.println("------------");

        // insertarEn
        lista.insertarEn(1, new PolloAsado("Combo familiar", "Mixta", 320.00));
        lista.transversal();
        System.out.println("------------");

        // contiene, indiceDe y obtener
        System.out.println("Contiene pechuga: " + lista.contiene(pechuga));
        System.out.println("Indice de pechuga: " + lista.indiceDe(pechuga));
        System.out.println("Elemento en 0: " + lista.obtener(0));
        System.out.println("------------");

        // eliminar
        lista.eliminar(entero);
        lista.transversal();
        System.out.println("Tamaño: " + lista.getTamanio());
        System.out.println("------------");

        // eliminarPrimero y eliminarUltimo
        System.out.println("Primero eliminado: " + lista.eliminarPrimero());
        System.out.println("Ultimo eliminado: " + lista.eliminarUltimo());
        lista.transversal();
        System.out.println("------------");

        // vaciar
        lista.vaciar();
        lista.transversal();
        System.out.println("Esta vacia: " + lista.estaVacia());
    }
}