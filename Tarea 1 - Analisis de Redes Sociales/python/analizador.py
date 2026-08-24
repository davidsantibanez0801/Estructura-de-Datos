"""
analizador.py
------------------------------------------------------------
Clase que contiene la "logica de negocio": los 4 calculos que
pide la practica. Recibe los objetos Facebook/Twitter/Youtube
ya cargados y solo se dedica a calcular, no a leer archivos ni
a dibujar ventanas.
------------------------------------------------------------
"""

from modelos import MESES


class AnalizadorRedes:
    def __init__(self, redes):
        """
        redes: diccionario { "FACEBOOK": obj, "TWITTER": obj, "YOUTUBE": obj }
        """
        self.facebook = redes.get("FACEBOOK")
        self.twitter = redes.get("TWITTER")
        self.youtube = redes.get("YOUTUBE")

    # 1) Diferencia de seguidores de Twitter entre enero y junio
    def diferencia_seguidores_twitter(self):
        concepto = self.twitter.buscar_concepto("SEGUIDORES")
        if concepto is None:
            concepto = self.twitter.buscar_concepto("FOLLOWERS")
        return self.twitter.diferencia(concepto, "ENERO", "JUNIO")

    # 2) Diferencia de visualizaciones de YouTube entre dos meses elegidos por el usuario
    def diferencia_visualizaciones_youtube(self, mes_inicio, mes_fin):
        concepto = self.youtube.buscar_concepto("VISUALIZACIONES")
        return self.youtube.diferencia(concepto, mes_inicio, mes_fin)

    # 3) Promedio de crecimiento de Twitter y Facebook (enero a junio)
    def promedio_crecimiento_twitter_facebook(self):
        concepto_tw = self.twitter.buscar_concepto("CRECIMIENTO")
        concepto_fb = self.facebook.buscar_concepto("CRECIMIENTO")
        promedio_tw = self.twitter.promedio(concepto_tw, "ENERO", "JUNIO")
        promedio_fb = self.facebook.promedio(concepto_fb, "ENERO", "JUNIO")
        return {"TWITTER": promedio_tw, "FACEBOOK": promedio_fb}

    # 4) Promedio de "Me gusta" de YouTube, Twitter y Facebook (enero a junio)
    def promedio_me_gusta(self):
        concepto_yt = self.youtube.buscar_concepto("ME GUSTA")
        concepto_tw = self.twitter.buscar_concepto("ME GUSTA")
        concepto_fb = self.facebook.buscar_concepto("ME GUSTA")
        return {
            "YOUTUBE": self.youtube.promedio(concepto_yt, "ENERO", "JUNIO"),
            "TWITTER": self.twitter.promedio(concepto_tw, "ENERO", "JUNIO"),
            "FACEBOOK": self.facebook.promedio(concepto_fb, "ENERO", "JUNIO"),
        }
