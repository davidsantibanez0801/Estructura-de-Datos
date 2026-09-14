package tarea3;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * MainJuegoVida.java
 * ------------------------------------------------------------
 * Punto de entrada del Problema B.
 *   1) Lee la población inicial desde un archivo CSV.
 *   2) Muestra la generación 0 (la inicial, tal como viene del archivo).
 *   3) Calcula y muestra 10 generaciones más, una por una.
 * ------------------------------------------------------------
 */
public class MainJuegoVida {

    private static final int TOTAL_GENERACIONES_A_CALCULAR = 10;

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        String ruta = args.length > 0 ? args[0] : "poblacion_inicial.csv";

        try {
            LectorPoblacion lector = new LectorPoblacion(ruta);
            boolean[][] poblacionInicial = lector.leer();

            JuegoDeLaVida juego = new JuegoDeLaVida(poblacionInicial);

            System.out.println("Cuadricula: " + juego.getRenglones() + " x " + juego.getColumnas());
            System.out.println();
            System.out.println("=== Generacion 0 (inicial) ===");
            juego.imprimir();

            for (int generacion = 1; generacion <= TOTAL_GENERACIONES_A_CALCULAR; generacion++) {
                juego.calcularSiguienteGeneracion();
                System.out.println();
                System.out.println("=== Generacion " + generacion + " ===");
                juego.imprimir();
            }

        } catch (IOException error) {
            java.io.File archivo = new java.io.File(ruta);
            System.out.println("No se pudo leer el archivo '" + ruta + "'.");
            System.out.println("Ruta absoluta donde lo busque: " + archivo.getAbsolutePath());
            System.out.println("Directorio de trabajo actual:  " + System.getProperty("user.dir"));
            System.out.println("Detalle: " + error.getMessage());
        } catch (RuntimeException error) {
            System.out.println("Ocurrio un error al procesar el archivo.");
            System.out.println("Detalle: " + error.getMessage());
        }
    }
}
