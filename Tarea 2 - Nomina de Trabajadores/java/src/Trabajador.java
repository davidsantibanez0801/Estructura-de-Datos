/**
 * Trabajador.java
 * ------------------------------------------------------------
 * Representa un renglon del archivo junio.dat, es decir, un
 * trabajador de la empresa.
 *
 * Ademas de guardar los datos que vienen del archivo, esta clase
 * sabe calcular SU PROPIO sueldo del mes (encapsulamiento de la
 * regla de negocio: nadie fuera de esta clase necesita saber la
 * formula, solo llaman a calcularSueldo()).
 * ------------------------------------------------------------
 */
public class Trabajador {

    // ---------- Reglas de negocio (constantes) ----------
    /** Valor que se paga por cada hora extra trabajada. */
    public static final double VALOR_HORA_EXTRA = 276.5;
    /** Prestacion: 3% (0.03) del sueldo por cada año de antigüedad. */
    public static final double PORCENTAJE_POR_ANIO_ANTIGUEDAD = 0.03;

    // ---------- Datos que vienen del archivo ----------
    private final int numero;
    private final String nombres;
    private final String paterno;
    private final String materno;
    private final int horasExtra;
    private final double sueldoBase;
    private final int anioIngreso;

    // ---------- Datos calculados (se llenan con calcularSueldo) ----------
    private int antiguedad;
    private double pagoHorasExtra;
    private double prestacionAntiguedad;
    private double sueldoAPagar;
    private boolean sueldoCalculado = false;

    public Trabajador(int numero, String nombres, String paterno, String materno,
                       int horasExtra, double sueldoBase, int anioIngreso) {
        this.numero = numero;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.horasExtra = horasExtra;
        this.sueldoBase = sueldoBase;
        this.anioIngreso = anioIngreso;
    }

    /**
     * Calcula el sueldo a pagar este mes, con las reglas de negocio:
     *   - Cada hora extra se paga a VALOR_HORA_EXTRA.
     *   - La antigüedad = anioActual - anioIngreso.
     *   - La prestacion es 3% (por cada año de antigüedad) sobre
     *     (sueldo base + pago de horas extra).
     *   - Sueldo a pagar = sueldo base + pago horas extra + prestacion.
     *
     * @param anioActual el año contra el que se calcula la antigüedad
     */
    public void calcularSueldo(int anioActual) {
        this.antiguedad = anioActual - anioIngreso;
        this.pagoHorasExtra = horasExtra * VALOR_HORA_EXTRA;

        double baseParaPrestacion = sueldoBase + pagoHorasExtra;
        double porcentajeAcumulado = antiguedad * PORCENTAJE_POR_ANIO_ANTIGUEDAD;
        this.prestacionAntiguedad = baseParaPrestacion * porcentajeAcumulado;

        this.sueldoAPagar = sueldoBase + pagoHorasExtra + prestacionAntiguedad;
        this.sueldoCalculado = true;
    }

    // ---------- Getters (acceso de solo lectura a los datos privados) ----------
    public int getNumero() { return numero; }
    public String getNombres() { return nombres; }
    public String getPaterno() { return paterno; }
    public String getMaterno() { return materno; }
    public String getNombreCompleto() { return nombres + " " + paterno + " " + materno; }
    public int getHorasExtra() { return horasExtra; }
    public double getSueldoBase() { return sueldoBase; }
    public int getAnioIngreso() { return anioIngreso; }

    public int getAntiguedad() {
        verificarCalculado();
        return antiguedad;
    }

    public double getPagoHorasExtra() {
        verificarCalculado();
        return pagoHorasExtra;
    }

    public double getPrestacionAntiguedad() {
        verificarCalculado();
        return prestacionAntiguedad;
    }

    public double getSueldoAPagar() {
        verificarCalculado();
        return sueldoAPagar;
    }

    private void verificarCalculado() {
        if (!sueldoCalculado) {
            throw new IllegalStateException(
                "Aun no se ha llamado a calcularSueldo() para el trabajador " + numero);
        }
    }

    @Override
    public String toString() {
        return String.format(
            "#%-6d %-30s Antigüedad: %2d año(s)  Sueldo base: $%,10.2f  " +
            "Horas extra: %2d ($%,9.2f)  Prestación: $%,10.2f  ->  Sueldo a pagar: $%,10.2f",
            numero, getNombreCompleto(), antiguedad, sueldoBase,
            horasExtra, pagoHorasExtra, prestacionAntiguedad, sueldoAPagar
        );
    }
}
