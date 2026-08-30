import javax.swing.SwingUtilities;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Punto de entrada del programa.
 *
 * Para ejecutarlo (desde la carpeta que contiene el .class o el .jar):
 *     java MainRedes
 */
public class MainRedes {
    public static void main(String[] args) {
        // Forzamos UTF-8 en la salida de consola para que acentos y "ñ"
        // se vean bien en cualquier sistema operativo.
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        // Los programas Swing deben iniciarse en el "Event Dispatch Thread" (EDT)
        SwingUtilities.invokeLater(InterfazApp::new);
    }
}
