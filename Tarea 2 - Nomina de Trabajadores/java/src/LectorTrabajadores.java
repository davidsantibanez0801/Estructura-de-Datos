import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * LectorTrabajadores.java
 * ------------------------------------------------------------
 * Responsable UNICAMENTE de leer el archivo .dat y convertir
 * cada renglon en un objeto Trabajador dentro de un
 * ListaTrabajadoresADT. No hace calculos de sueldo ni imprime
 * nada: esa es tarea de otras clases.
 *
 * Formato esperado de cada renglon (separado por comas):
 *   numero, nombres, paterno, materno, horas extra, sueldo base, año de ingreso
 * ------------------------------------------------------------
 */
public class LectorTrabajadores {

    private final String rutaArchivo;

    public LectorTrabajadores(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public ListaTrabajadoresADT leer() throws IOException {
        List<String> lineasDeDatos = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(
                new FileReader(rutaArchivo, StandardCharsets.UTF_8))) {

            String linea = lector.readLine(); // encabezado, se descarta
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    lineasDeDatos.add(linea);
                }
            }
        }

        ListaTrabajadoresADT nomina = new ListaTrabajadoresADT(lineasDeDatos.size());

        for (int i = 0; i < lineasDeDatos.size(); i++) {
            Trabajador trabajador = convertirALinea(lineasDeDatos.get(i));
            nomina.insertarElemento(i, trabajador);
        }

        return nomina;
    }

    private Trabajador convertirALinea(String linea) {
        String[] campos = linea.split(",", -1);

        int numero = Integer.parseInt(campos[0].trim());
        String nombres = campos[1].trim();
        String paterno = campos[2].trim();
        String materno = campos[3].trim();
        int horasExtra = Integer.parseInt(campos[4].trim());
        double sueldoBase = Double.parseDouble(campos[5].trim());
        int anioIngreso = Integer.parseInt(campos[6].trim());

        return new Trabajador(numero, nombres, paterno, materno, horasExtra, sueldoBase, anioIngreso);
    }
}
