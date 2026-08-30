/**
 * ListaTrabajadoresADT.java
 * ------------------------------------------------------------
 * Este es el ADT que pide la tarea: "un ADT a partir del ADT
 * Array que almacene la informacion del archivo".
 *
 * Se construye HEREDANDO de ArrayADT<Trabajador> (extends), es
 * decir, ListaTrabajadoresADT ES-UN ArrayADT especializado para
 * guardar Trabajadores, al que le agregamos operaciones propias
 * del negocio: calcular sueldos, encontrar mayor/menor
 * antigüedad e imprimir la nomina completa.
 *
 * Gracias a la herencia, no tuvimos que volver a escribir
 * obtenerElemento(), insertarElemento() ni longitud(): ya vienen
 * incluidos desde ArrayADT.
 * ------------------------------------------------------------
 */
public class ListaTrabajadoresADT extends ArrayADT<Trabajador> {

    public ListaTrabajadoresADT(int cantidadTrabajadores) {
        super(cantidadTrabajadores);
    }

    /**
     * Calcula el sueldo de TODOS los trabajadores almacenados,
     * usando el año actual para calcular la antigüedad de cada uno.
     */
    public void calcularSueldos(int anioActual) {
        for (int i = 0; i < longitud(); i++) {
            Trabajador trabajador = obtenerElemento(i);
            trabajador.calcularSueldo(anioActual);
        }
    }

    /** Regresa el trabajador con MAYOR antigüedad (el que ingreso hace mas años). */
    public Trabajador obtenerMayorAntiguedad() {
        Trabajador mayor = obtenerElemento(0);
        for (int i = 1; i < longitud(); i++) {
            Trabajador actual = obtenerElemento(i);
            if (actual.getAntiguedad() > mayor.getAntiguedad()) {
                mayor = actual;
            }
        }
        return mayor;
    }

    /** Regresa el trabajador con MENOR antigüedad (el que ingreso mas reciente). */
    public Trabajador obtenerMenorAntiguedad() {
        Trabajador menor = obtenerElemento(0);
        for (int i = 1; i < longitud(); i++) {
            Trabajador actual = obtenerElemento(i);
            if (actual.getAntiguedad() < menor.getAntiguedad()) {
                menor = actual;
            }
        }
        return menor;
    }

    /** Imprime en pantalla los datos completos de todos los trabajadores. */
    public void imprimirNomina() {
        for (int i = 0; i < longitud(); i++) {
            System.out.println(obtenerElemento(i));
        }
    }
}
