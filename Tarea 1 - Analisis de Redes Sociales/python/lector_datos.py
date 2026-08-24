"""
lector_datos.py
------------------------------------------------------------
Clase encargada de leer el archivo CSV y convertir cada fila
en datos dentro de un objeto Facebook, Twitter o Youtube.

Se separa esta responsabilidad en su propia clase (principio
de responsabilidad unica) para no mezclar "leer archivo" con
"hacer calculos" ni con "dibujar la interfaz".
------------------------------------------------------------
"""

import csv
from modelos import Facebook, Twitter, Youtube, MESES


class LectorDatos:
    """Lee un archivo CSV con el formato de datos de redes sociales."""

    def __init__(self, ruta_archivo):
        self.ruta_archivo = ruta_archivo
        self.filas_originales = []   # guardamos las filas tal cual, para la vista previa

    def leer(self):
        """
        Lee el archivo CSV y regresa un diccionario:
        { "FACEBOOK": objeto Facebook, "TWITTER": objeto Twitter, "YOUTUBE": objeto Youtube }
        """
        redes = {
            "FACEBOOK": Facebook(),
            "TWITTER": Twitter(),
            "YOUTUBE": Youtube(),
        }

        self.filas_originales = []

        with open(self.ruta_archivo, newline="", encoding="utf-8-sig") as archivo:
            lector = csv.reader(archivo)
            encabezado = next(lector)  # primera fila: RED SOCIAL, CONCEPTO, AÑO, ENERO, ...
            self.filas_originales.append(encabezado)

            for fila in lector:
                if not fila or fila[0].strip() == "":
                    continue  # ignoramos lineas vacias

                self.filas_originales.append(fila)

                red_social = fila[0].strip().upper()
                concepto = fila[1].strip().upper()
                # Las columnas 3 a 14 son los 12 meses (despues de RED SOCIAL, CONCEPTO, AÑO)
                valores_texto = fila[3:15]
                valores = [self._a_numero(v) for v in valores_texto]

                if red_social in redes:
                    redes[red_social].agregar_concepto(concepto, valores)

        return redes

    @staticmethod
    def _a_numero(texto):
        """
        Convierte un texto a numero (float).
        Soporta valores como '0.43%' -> 0.43 y celdas vacias -> 0.0
        """
        if texto is None:
            return 0.0
        texto = str(texto).strip()
        if texto == "":
            return 0.0
        texto = texto.replace("%", "").replace(",", "")
        try:
            return float(texto)
        except ValueError:
            return 0.0

    def vista_previa(self, num_filas=10):
        """Regresa hasta num_filas filas (incluyendo encabezado) para mostrarlas."""
        return self.filas_originales[:num_filas]
