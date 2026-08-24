import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de leer el archivo CSV y convertir cada fila en
 * datos dentro de un objeto Facebook, Twitter o Youtube.
 *
 * Se separa esta responsabilidad en su propia clase (principio de
 * responsabilidad unica) para no mezclar "leer archivo" con
 * "hacer calculos" ni con "dibujar la interfaz".
 */
public class LectorDatos {

    private final String rutaArchivo;
    private final List<String[]> filasOriginales = new ArrayList<>();

    public LectorDatos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Lee el archivo CSV y regresa un mapa:
     * { "FACEBOOK" -> objeto Facebook, "TWITTER" -> objeto Twitter, "YOUTUBE" -> objeto Youtube }
     */
    public Map<String, RedSocial> leer() throws IOException {
        Map<String, RedSocial> redes = new HashMap<>();
        redes.put("FACEBOOK", new Facebook());
        redes.put("TWITTER", new Twitter());
        redes.put("YOUTUBE", new Youtube());

        filasOriginales.clear();

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo, java.nio.charset.StandardCharsets.UTF_8))) {
            String linea = lector.readLine(); // encabezado
            if (linea == null) {
                return redes;
            }
            // Quitamos un posible BOM al inicio del archivo
            linea = linea.replace("\uFEFF", "");
            filasOriginales.add(dividirLinea(linea));

            while ((linea = lector.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] fila = dividirLinea(linea);
                filasOriginales.add(fila);

                if (fila.length < 4) {
                    continue;
                }

                String redSocial = fila[0].trim().toUpperCase();
                String concepto = fila[1].trim().toUpperCase();

                // Las columnas 3 a 14 (indices 3..14) son los 12 meses,
                // despues de RED SOCIAL, CONCEPTO, AÑO.
                double[] valores = new double[12];
                for (int i = 0; i < 12; i++) {
                    int indiceColumna = 3 + i;
                    String texto = indiceColumna < fila.length ? fila[indiceColumna] : "0";
                    valores[i] = aNumero(texto);
                }

                RedSocial red = redes.get(redSocial);
                if (red != null) {
                    red.agregarConcepto(concepto, valores);
                }
            }
        }

        return redes;
    }

    /** Separa una linea de CSV simple (sin comillas ni comas dentro de campos). */
    private static String[] dividirLinea(String linea) {
        return linea.split(",", -1);
    }

    /**
     * Convierte un texto a numero (double).
     * Soporta valores como "0.43%" -> 0.43 y celdas vacias -> 0.0
     */
    private static double aNumero(String texto) {
        if (texto == null) {
            return 0.0;
        }
        String limpio = texto.trim().replace("%", "").replace(",", "");
        if (limpio.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(limpio);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /** Regresa hasta numFilas filas (incluyendo encabezado) para mostrarlas en la vista previa. */
    public List<String[]> vistaPrevia(int numFilas) {
        int limite = Math.min(numFilas, filasOriginales.size());
        return new ArrayList<>(filasOriginales.subList(0, limite));
    }
}
