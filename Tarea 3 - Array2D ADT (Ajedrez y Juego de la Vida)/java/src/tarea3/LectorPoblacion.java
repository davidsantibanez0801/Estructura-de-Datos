package tarea3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * LectorPoblacion.java
 * ------------------------------------------------------------
 * Lee un archivo CSV con la configuración inicial del Juego de
 * la Vida: una matriz de 0s y 1s (1 = célula viva, 0 = célula
 * muerta), separados por comas, un renglón del archivo por cada
 * fila de la cuadrícula.
 * ------------------------------------------------------------
 */
public class LectorPoblacion {

    private final String rutaArchivo;

    public LectorPoblacion(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public boolean[][] leer() throws IOException {
        List<boolean[]> renglones = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(
                new FileReader(rutaArchivo, StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) {
                    continue;
                }
                String[] valores = linea.split(",", -1);
                boolean[] renglon = new boolean[valores.length];
                for (int j = 0; j < valores.length; j++) {
                    renglon[j] = valores[j].trim().equals("1");
                }
                renglones.add(renglon);
            }
        }

        if (renglones.isEmpty()) {
            throw new IOException("El archivo '" + rutaArchivo + "' esta vacio.");
        }

        int columnas = renglones.get(0).length;
        boolean[][] poblacion = new boolean[renglones.size()][columnas];
        for (int i = 0; i < renglones.size(); i++) {
            poblacion[i] = renglones.get(i);
        }

        return poblacion;
    }
}
