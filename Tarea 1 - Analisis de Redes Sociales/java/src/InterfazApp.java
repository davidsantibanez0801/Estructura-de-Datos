import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Clase InterfazApp: maneja TODA la parte visual (ventana, botones,
 * tablas, mensajes) usando Swing.
 *
 * No hace calculos ni lee archivos directamente: para eso usa a
 * LectorDatos y AnalizadorRedes. Asi cada clase tiene una sola
 * responsabilidad (mas facil de explicar y de mantener).
 */
public class InterfazApp {

    private final JFrame ventana;
    private final JPanel contenedor;

    // Se llenan cuando el usuario carga un archivo
    private Map<String, RedSocial> redes;
    private AnalizadorRedes analizador;
    private String rutaArchivo;

    private JTextArea areaResultado;

    private static final DecimalFormat FORMATO = new DecimalFormat("#,##0.00");
    private static final DecimalFormat FORMATO_ENTERO = new DecimalFormat("#,##0");

    public InterfazApp() {
        ventana = new JFrame("Analisis de Redes Sociales");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1000, 850);
        ventana.setMinimumSize(new Dimension(900, 700));
        ventana.setLocationRelativeTo(null);

        contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        ventana.setContentPane(contenedor);

        mostrarMenuInicio();
        ventana.setVisible(true);
    }

    // ------------------------------------------------------------------
    // PANTALLA 1: Menu de inicio (seleccionar archivo)
    // ------------------------------------------------------------------
    private void mostrarMenuInicio() {
        limpiarContenedor();
        redes = null;
        analizador = null;

        contenedor.add(Box.createVerticalStrut(30));

        JLabel titulo = new JLabel("Analisis de Redes Sociales (Facebook / Twitter / YouTube)");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(titulo);

        contenedor.add(Box.createVerticalStrut(10));

        JLabel subtitulo = new JLabel("Selecciona un archivo CSV con el formato de la practica para comenzar.");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(subtitulo);

        contenedor.add(Box.createVerticalStrut(30));

        JButton botonCargar = new JButton("Seleccionar archivo CSV");
        botonCargar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        botonCargar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCargar.addActionListener(e -> cargarArchivo());
        contenedor.add(botonCargar);

        contenedor.revalidate();
        contenedor.repaint();
    }

    private void cargarArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Selecciona el archivo de datos");
        selector.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos CSV", "csv"));

        int opcion = selector.showOpenDialog(ventana);
        if (opcion != JFileChooser.APPROVE_OPTION) {
            return; // el usuario cancelo
        }

        File archivo = selector.getSelectedFile();
        try {
            LectorDatos lector = new LectorDatos(archivo.getAbsolutePath());
            redes = lector.leer();
            analizador = new AnalizadorRedes(redes);
            rutaArchivo = archivo.getAbsolutePath();
            mostrarPanelPrincipal(lector.vistaPrevia(10));
        } catch (IOException | RuntimeException error) {
            JOptionPane.showMessageDialog(
                ventana,
                "No se pudo leer el archivo seleccionado.\n\nDetalle: " + error.getMessage(),
                "Error al leer el archivo",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ------------------------------------------------------------------
    // PANTALLA 2: Vista previa + 4 botones de accion
    // ------------------------------------------------------------------
    private void mostrarPanelPrincipal(List<String[]> filasVistaPrevia) {
        limpiarContenedor();

        // --- Encabezado ---
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        encabezado.setAlignmentX(Component.LEFT_ALIGNMENT);
        encabezado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel etiquetaArchivo = new JLabel("Archivo cargado: " + rutaArchivo);
        etiquetaArchivo.setFont(new Font("SansSerif", Font.ITALIC, 11));
        encabezado.add(etiquetaArchivo, BorderLayout.WEST);

        JButton botonMenu = new JButton("<< Menu de inicio");
        botonMenu.addActionListener(e -> mostrarMenuInicio());
        encabezado.add(botonMenu, BorderLayout.EAST);

        contenedor.add(encabezado);

        // --- Vista previa del archivo (JTable) ---
        JLabel etiquetaTabla = new JLabel("Vista previa de los datos");
        etiquetaTabla.setFont(new Font("SansSerif", Font.BOLD, 13));
        etiquetaTabla.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(Box.createVerticalStrut(15));
        contenedor.add(etiquetaTabla);
        contenedor.add(Box.createVerticalStrut(5));

        String[] columnas = filasVistaPrevia.isEmpty() ? new String[0] : filasVistaPrevia.get(0);
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (int i = 1; i < filasVistaPrevia.size(); i++) {
            modelo.addRow(filasVistaPrevia.get(i));
        }
        JTable tabla = new JTable(modelo);
        tabla.setEnabled(false);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollTabla.setMaximumSize(new Dimension(Integer.MAX_VALUE, 190));
        scrollTabla.setPreferredSize(new Dimension(960, 190));
        contenedor.add(scrollTabla);

        // --- Botones de accion ---
        JLabel etiquetaAcciones = new JLabel("Acciones disponibles");
        etiquetaAcciones.setFont(new Font("SansSerif", Font.BOLD, 13));
        etiquetaAcciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(Box.createVerticalStrut(20));
        contenedor.add(etiquetaAcciones);
        contenedor.add(Box.createVerticalStrut(5));

        JPanel marcoBotones = new JPanel(new GridLayout(2, 2, 12, 12));
        marcoBotones.setAlignmentX(Component.CENTER_ALIGNMENT);
        marcoBotones.setMaximumSize(new Dimension(700, 140));

        marcoBotones.add(crearBotonAccion(
            "<html><center>1. Diferencia de seguidores<br>Twitter (Enero - Junio)</center></html>",
            e -> accionDiferenciaSeguidoresTwitter()));

        marcoBotones.add(crearBotonAccion(
            "<html><center>2. Diferencia de visualizaciones<br>YouTube (meses a elegir)</center></html>",
            e -> accionDiferenciaVisualizacionesYoutube()));

        marcoBotones.add(crearBotonAccion(
            "<html><center>3. Promedio de crecimiento<br>Twitter y Facebook (Ene - Jun)</center></html>",
            e -> accionPromedioCrecimiento()));

        marcoBotones.add(crearBotonAccion(
            "<html><center>4. Promedio de \"Me gusta\"<br>YouTube, Twitter y Facebook</center></html>",
            e -> accionPromedioMeGusta()));

        contenedor.add(marcoBotones);

        // --- Zona de resultados ---
        JLabel etiquetaResultado = new JLabel("Resultado");
        etiquetaResultado.setFont(new Font("SansSerif", Font.BOLD, 13));
        etiquetaResultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedor.add(Box.createVerticalStrut(20));
        contenedor.add(etiquetaResultado);
        contenedor.add(Box.createVerticalStrut(5));

        areaResultado = new JTextArea("Selecciona una accion para ver el resultado aqui.");
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        JScrollPane scrollResultado = new JScrollPane(areaResultado);
        scrollResultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollResultado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
        scrollResultado.setPreferredSize(new Dimension(960, 130));
        contenedor.add(scrollResultado);
        contenedor.add(Box.createVerticalStrut(10));

        contenedor.revalidate();
        contenedor.repaint();
    }

    private JButton crearBotonAccion(String texto, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(accion);
        return boton;
    }

    private void mostrarResultado(String texto) {
        areaResultado.setText(texto);
    }

    private void limpiarContenedor() {
        contenedor.removeAll();
    }

    // ------------------------------------------------------------------
    // ACCION 1: Diferencia de seguidores de Twitter (Enero - Junio)
    // ------------------------------------------------------------------
    private void accionDiferenciaSeguidoresTwitter() {
        try {
            double diferencia = analizador.diferenciaSeguidoresTwitter();
            String texto = "Diferencia de seguidores en Twitter entre ENERO y JUNIO:\n"
                + FORMATO_ENTERO.format(diferencia) + " seguidores nuevos.";
            mostrarResultado(texto);
        } catch (RuntimeException error) {
            JOptionPane.showMessageDialog(ventana, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ------------------------------------------------------------------
    // ACCION 2: Diferencia de visualizaciones de YouTube (meses por teclado)
    // ------------------------------------------------------------------
    private void accionDiferenciaVisualizacionesYoutube() {
        BiConsumer<String, String> alConfirmar = (mesInicio, mesFin) -> {
            try {
                double diferencia = analizador.diferenciaVisualizacionesYoutube(mesInicio, mesFin);
                String texto = "Diferencia de visualizaciones en YouTube entre "
                    + mesInicio + " y " + mesFin + ":\n"
                    + FORMATO_ENTERO.format(diferencia) + " visualizaciones.";
                mostrarResultado(texto);
            } catch (RuntimeException error) {
                JOptionPane.showMessageDialog(ventana, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
        new VentanaSeleccionMeses(ventana, alConfirmar);
    }

    // ------------------------------------------------------------------
    // ACCION 3: Promedio de crecimiento Twitter y Facebook (Ene-Jun)
    // ------------------------------------------------------------------
    private void accionPromedioCrecimiento() {
        try {
            Map<String, Double> promedios = analizador.promedioCrecimientoTwitterFacebook();
            String texto = "Promedio de crecimiento (ENERO a JUNIO):\n"
                + "  - Twitter:  " + FORMATO.format(promedios.get("TWITTER")) + " seguidores nuevos / mes\n"
                + "  - Facebook: " + FORMATO.format(promedios.get("FACEBOOK")) + " seguidores nuevos / mes";
            mostrarResultado(texto);
        } catch (RuntimeException error) {
            JOptionPane.showMessageDialog(ventana, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ------------------------------------------------------------------
    // ACCION 4: Promedio de "Me gusta" YouTube, Twitter, Facebook
    // ------------------------------------------------------------------
    private void accionPromedioMeGusta() {
        try {
            Map<String, Double> promedios = analizador.promedioMeGusta();
            String texto = "Promedio de \"Me gusta\" (ENERO a JUNIO):\n"
                + "  - YouTube:  " + FORMATO.format(promedios.get("YOUTUBE")) + "\n"
                + "  - Twitter:  " + FORMATO.format(promedios.get("TWITTER")) + "\n"
                + "  - Facebook: " + FORMATO.format(promedios.get("FACEBOOK"));
            mostrarResultado(texto);
        } catch (RuntimeException error) {
            JOptionPane.showMessageDialog(ventana, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
