import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.Year;

/**
 * MainRedes.java
 * ------------------------------------------------------------
 * Punto de entrada. Orquesta el flujo completo:
 *   1) Leer el archivo junio.dat -> ListaTrabajadoresADT
 *   2) Calcular el sueldo de cada trabajador
 *   3) Mostrar el de mayor y menor antigüedad
 *   4) Mostrar la nomina completa con el sueldo a pagar
 *
 * Para ejecutarlo:
 *   javac -d bin -encoding UTF-8 src/*.java
 *   cd bin
 *   java MainRedes ../junio.dat
 *
 * (Si no se indica ruta por argumento, usa "junio.dat" por defecto,
 * buscandolo en la carpeta desde la que se ejecuta el programa.)
 * ------------------------------------------------------------
 */
public class MainNomina {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        String ruta = args.length > 0 ? args[0] : "Tarea 2 - Nomina de Trabajadores/java/junio.dat";

        try {
            LectorTrabajadores lector = new LectorTrabajadores(ruta);
            ListaTrabajadoresADT nomina = lector.leer();

            int anioActual = Year.now().getValue();
            nomina.calcularSueldos(anioActual);

            System.out.println("=========================================================");
            System.out.println(" NOMINA DE TRABAJADORES - " + ruta);
            System.out.println(" (Antigüedad calculada contra el año actual: " + anioActual + ")");
            System.out.println("=========================================================");
            System.out.println();

            Trabajador mayorAntiguedad = nomina.obtenerMayorAntiguedad();
            Trabajador menorAntiguedad = nomina.obtenerMenorAntiguedad();

            System.out.println("--- Trabajador con MAYOR antigüedad ---");
            System.out.println(mayorAntiguedad);
            System.out.println();

            System.out.println("--- Trabajador con MENOR antigüedad ---");
            System.out.println(menorAntiguedad);
            System.out.println();

            System.out.println("--- Nomina completa (" + nomina.longitud() + " trabajadores) ---");
            nomina.imprimirNomina();

        } catch (IOException error) {
            System.out.println("No se pudo leer el archivo '" + ruta + "'.");
            System.out.println("Detalle: " + error.getMessage());
        } catch (RuntimeException error) {
            System.out.println("Ocurrio un error al procesar el archivo.");
            System.out.println("Detalle: " + error.getMessage());
        }
    }
}
