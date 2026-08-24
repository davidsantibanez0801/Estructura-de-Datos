import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clase que contiene la "logica de negocio": los 4 calculos que pide
 * la practica. Recibe los objetos Facebook/Twitter/Youtube ya cargados
 * y solo se dedica a calcular, no a leer archivos ni a dibujar ventanas.
 */
public class AnalizadorRedes {

    private final Facebook facebook;
    private final Twitter twitter;
    private final Youtube youtube;

    public AnalizadorRedes(Map<String, RedSocial> redes) {
        this.facebook = (Facebook) redes.get("FACEBOOK");
        this.twitter = (Twitter) redes.get("TWITTER");
        this.youtube = (Youtube) redes.get("YOUTUBE");
    }

    /** 1) Diferencia de seguidores de Twitter entre enero y junio */
    public double diferenciaSeguidoresTwitter() {
        String concepto = twitter.buscarConcepto("SEGUIDORES");
        if (concepto == null) {
            concepto = twitter.buscarConcepto("FOLLOWERS");
        }
        return twitter.diferencia(concepto, "ENERO", "JUNIO");
    }

    /** 2) Diferencia de visualizaciones de YouTube entre dos meses elegidos por el usuario */
    public double diferenciaVisualizacionesYoutube(String mesInicio, String mesFin) {
        String concepto = youtube.buscarConcepto("VISUALIZACIONES");
        return youtube.diferencia(concepto, mesInicio, mesFin);
    }

    /** 3) Promedio de crecimiento de Twitter y Facebook (enero a junio) */
    public Map<String, Double> promedioCrecimientoTwitterFacebook() {
        String conceptoTw = twitter.buscarConcepto("CRECIMIENTO");
        String conceptoFb = facebook.buscarConcepto("CRECIMIENTO");
        Map<String, Double> resultado = new LinkedHashMap<>();
        resultado.put("TWITTER", twitter.promedio(conceptoTw, "ENERO", "JUNIO"));
        resultado.put("FACEBOOK", facebook.promedio(conceptoFb, "ENERO", "JUNIO"));
        return resultado;
    }

    /** 4) Promedio de "Me gusta" de YouTube, Twitter y Facebook (enero a junio) */
    public Map<String, Double> promedioMeGusta() {
        String conceptoYt = youtube.buscarConcepto("ME GUSTA");
        String conceptoTw = twitter.buscarConcepto("ME GUSTA");
        String conceptoFb = facebook.buscarConcepto("ME GUSTA");

        Map<String, Double> resultado = new LinkedHashMap<>();
        resultado.put("YOUTUBE", youtube.promedio(conceptoYt, "ENERO", "JUNIO"));
        resultado.put("TWITTER", twitter.promedio(conceptoTw, "ENERO", "JUNIO"));
        resultado.put("FACEBOOK", facebook.promedio(conceptoFb, "ENERO", "JUNIO"));
        return resultado;
    }
}
