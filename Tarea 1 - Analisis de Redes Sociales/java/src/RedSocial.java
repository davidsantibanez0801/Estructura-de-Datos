import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase base para cualquier red social.
 *
 * Conceptos de POO aplicados:
 * - Clase abstracta: no tiene sentido crear un objeto "RedSocial" a secas,
 *   siempre es Facebook, Twitter o Youtube (por eso es abstract).
 * - Herencia: Facebook, Twitter y Youtube extienden de esta clase.
 * - Encapsulamiento: el mapa de datos es privado y solo se accede a traves
 *   de metodos publicos (agregarConcepto, obtenerValor, promedio, etc.)
 */
public abstract class RedSocial {

    // Meses en el orden en que aparecen en el archivo (constante compartida).
    public static final String[] MESES = {
        "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO",
        "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"
    };

    private final String nombre;
    // Diccionario "protegido": { "SEGUIDORES" -> [ene, feb, ..., dic], ... }
    // Usamos LinkedHashMap (no HashMap) para conservar el ORDEN en que se
    // fueron agregando los conceptos: buscarConcepto() recorre este mapa y
    // debe encontrar primero "CRECIMIENTO (SEGUIDORES)" antes que
    // "PORCENTAJE DE CRECIMIENTO", tal como aparecen en el archivo.
    private final Map<String, double[]> datos;

    public RedSocial(String nombre) {
        this.nombre = nombre;
        this.datos = new LinkedHashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    // ---------- Metodos para cargar datos ----------
    public void agregarConcepto(String concepto, double[] valoresPorMes) {
        datos.put(concepto.trim().toUpperCase(), valoresPorMes);
    }

    // ---------- Metodos de consulta ----------
    public boolean tieneConcepto(String concepto) {
        return datos.containsKey(concepto.trim().toUpperCase());
    }

    /**
     * Busca el primer concepto que CONTENGA la palabra clave dada.
     * Sirve para no depender de que el texto sea exactamente igual
     * (ej. "ME GUSTA" tambien encuentra "ME GUSTA EN PUBLICACIONES").
     */
    public String buscarConcepto(String palabraClave) {
        String clave = palabraClave.trim().toUpperCase();
        for (String concepto : datos.keySet()) {
            if (concepto.contains(clave)) {
                return concepto;
            }
        }
        return null;
    }

    private static int indiceDeMes(String mes) {
        String m = mes.trim().toUpperCase();
        for (int i = 0; i < MESES.length; i++) {
            if (MESES[i].equals(m)) {
                return i;
            }
        }
        throw new IllegalArgumentException("'" + mes + "' no es un mes valido.");
    }

    public double obtenerValor(String concepto, String mes) {
        String clave = concepto.trim().toUpperCase();
        double[] valores = datos.get(clave);
        if (valores == null) {
            throw new IllegalArgumentException(
                "El concepto '" + clave + "' no existe en " + nombre + ".");
        }
        return valores[indiceDeMes(mes)];
    }

    public double diferencia(String concepto, String mesInicio, String mesFin) {
        double valorInicio = obtenerValor(concepto, mesInicio);
        double valorFin = obtenerValor(concepto, mesFin);
        return valorFin - valorInicio;
    }

    public double promedio(String concepto, String mesInicio, String mesFin) {
        String clave = concepto.trim().toUpperCase();
        double[] valores = datos.get(clave);
        if (valores == null) {
            throw new IllegalArgumentException(
                "El concepto '" + clave + "' no existe en " + nombre + ".");
        }
        int iInicio = indiceDeMes(mesInicio);
        int iFin = indiceDeMes(mesFin);
        double suma = 0;
        int cantidad = 0;
        for (int i = iInicio; i <= iFin; i++) {
            suma += valores[i];
            cantidad++;
        }
        return suma / cantidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + datos.size() + " conceptos cargados)";
    }
}
