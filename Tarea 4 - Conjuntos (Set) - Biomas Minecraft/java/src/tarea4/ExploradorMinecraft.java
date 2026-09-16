package tarea4;

/**
 * ExploradorMinecraft.java
 * ------------------------------------------------------------
 * Cada jugador tiene un ConjuntoADT<String> con los nombres de
 * los biomas que ya descubrió. Como es un CONJUNTO (no una
 * lista), no importa cuántas veces el jugador vuelva a pisar el
 * mismo bioma — solo cuenta una vez.
 * ------------------------------------------------------------
 */
public class ExploradorMinecraft {

    private final String nombreJugador;
    private final ConjuntoADT<String> biomasDescubiertos;

    public ExploradorMinecraft(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.biomasDescubiertos = new ConjuntoADT<>();
    }

    /**
     * El jugador entra a un bioma. Si nunca lo había visitado, se
     * agrega al conjunto y cuenta como nuevo descubrimiento. Si ya
     * lo conocía, el conjunto no cambia (así es como se comporta
     * un conjunto matemático).
     */
    public void visitarBioma(String nombreBioma) {
        boolean yaLoConocia = biomasDescubiertos.contieneElemento(nombreBioma);
        biomasDescubiertos.agregarElemento(nombreBioma);

        if (yaLoConocia) {
            System.out.println(nombreJugador + " entra a " + nombreBioma
                + " -> ya lo habia descubierto antes, no pasa nada nuevo.");
        } else {
            System.out.println(nombreJugador + " entra a " + nombreBioma
                + " -> ¡NUEVO bioma descubierto! (" + biomasDescubiertos.longitud()
                + " en total)");
        }
    }

    public int totalBiomasDescubiertos() {
        return biomasDescubiertos.longitud();
    }

    public boolean yaDescubrio(String nombreBioma) {
        return biomasDescubiertos.contieneElemento(nombreBioma);
    }

    public ConjuntoADT<String> getBiomasDescubiertos() {
        return biomasDescubiertos;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void mostrarResumen() {
        System.out.println(nombreJugador + " ha descubierto " + totalBiomasDescubiertos()
            + " biomas distintos: " + biomasDescubiertos.getElementos());
    }
}
