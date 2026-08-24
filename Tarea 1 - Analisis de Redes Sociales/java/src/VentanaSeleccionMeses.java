import javax.swing.*;
import java.awt.*;
import java.util.function.BiConsumer;

/**
 * Ventana emergente donde el usuario escribe (por teclado) los dos
 * meses que quiere comparar para la diferencia de visualizaciones
 * de YouTube. Es un JComboBox editable: el usuario puede escribir
 * el mes directamente o elegirlo de la lista.
 */
public class VentanaSeleccionMeses extends JDialog {

    private final JComboBox<String> comboMesInicio;
    private final JComboBox<String> comboMesFin;

    public VentanaSeleccionMeses(JFrame padre, BiConsumer<String, String> alConfirmar) {
        super(padre, "Elegir meses", true);
        setSize(320, 230);
        setResizable(false);
        setLocationRelativeTo(padre);
        setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel etiquetaInicio = new JLabel("Escribe el mes inicial:");
        etiquetaInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(etiquetaInicio);

        comboMesInicio = new JComboBox<>(RedSocial.MESES);
        comboMesInicio.setEditable(true);
        comboMesInicio.setSelectedItem("ENERO");
        comboMesInicio.setMaximumSize(new Dimension(200, 28));
        comboMesInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(comboMesInicio);

        JLabel etiquetaFin = new JLabel("Escribe el mes final:");
        etiquetaFin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(10));
        panel.add(etiquetaFin);

        comboMesFin = new JComboBox<>(RedSocial.MESES);
        comboMesFin.setEditable(true);
        comboMesFin.setSelectedItem("JUNIO");
        comboMesFin.setMaximumSize(new Dimension(200, 28));
        comboMesFin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(comboMesFin);

        JButton botonCalcular = new JButton("Calcular");
        botonCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(botonCalcular);

        botonCalcular.addActionListener(e -> {
            String mesInicio = comboMesInicio.getEditor().getItem().toString().trim().toUpperCase();
            String mesFin = comboMesFin.getEditor().getItem().toString().trim().toUpperCase();

            if (!esMesValido(mesInicio) || !esMesValido(mesFin)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Escribe un mes valido, por ejemplo: ENERO, FEBRERO, MARZO, etc.",
                    "Mes invalido",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            alConfirmar.accept(mesInicio, mesFin);
            dispose();
        });

        add(panel);
        setVisible(true);
    }

    private static boolean esMesValido(String mes) {
        for (String m : RedSocial.MESES) {
            if (m.equals(mes)) {
                return true;
            }
        }
        return false;
    }
}
