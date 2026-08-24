"""
modelos.py
------------------------------------------------------------
Aqui viven las clases que representan a cada red social.

Conceptos de POO aplicados:
- Clase base (RedSocial) que agrupa lo que TODAS las redes
  sociales tienen en comun (nombre, datos por concepto y mes).
- Herencia: Facebook, Twitter y Youtube heredan de RedSocial.
- Encapsulamiento: los datos se guardan en un atributo "protegido"
  (self._datos) y solo se accede/edita a traves de metodos
  (agregar_concepto, obtener_valor, etc.), no directamente.
------------------------------------------------------------
"""

# Lista de meses en el orden en que aparecen en el archivo.
# La usamos varias veces, por eso la dejamos como constante del modulo.
MESES = [
    "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO",
    "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"
]


class RedSocial:
    """
    Clase base para cualquier red social.

    Guarda, para cada "concepto" (por ejemplo SEGUIDORES, ME GUSTA,
    CRECIMIENTO, etc.) una lista con 12 valores, uno por mes.
    """

    def __init__(self, nombre):
        self.nombre = nombre
        # Diccionario "protegido": { "SEGUIDORES": [ene, feb, ..., dic], ... }
        self._datos = {}

    # ---------- Metodos para cargar datos ----------
    def agregar_concepto(self, concepto, valores_por_mes):
        """
        Guarda una fila de datos.
        concepto: texto, ej. "SEGUIDORES (FOLLOWERS)"
        valores_por_mes: lista de 12 numeros (uno por mes)
        """
        self._datos[concepto.upper().strip()] = valores_por_mes

    # ---------- Metodos de consulta ----------
    def tiene_concepto(self, concepto):
        return concepto.upper().strip() in self._datos

    def buscar_concepto(self, palabra_clave):
        """
        Busca el primer concepto que contenga la palabra clave dada.
        Sirve para no depender de que el texto sea EXACTAMENTE igual
        (ej. "ME GUSTA" tambien encuentra "ME GUSTA EN PUBLICACIONES").
        Regresa el nombre del concepto encontrado o None.
        """
        palabra_clave = palabra_clave.upper().strip()
        for concepto in self._datos:
            if palabra_clave in concepto:
                return concepto
        return None

    def obtener_valor(self, concepto, mes):
        """Regresa el valor de un concepto en un mes especifico."""
        concepto = concepto.upper().strip()
        mes = mes.upper().strip()
        if concepto not in self._datos:
            raise ValueError(f"El concepto '{concepto}' no existe en {self.nombre}.")
        if mes not in MESES:
            raise ValueError(f"'{mes}' no es un mes valido.")
        indice = MESES.index(mes)
        return self._datos[concepto][indice]

    def diferencia(self, concepto, mes_inicio, mes_fin):
        """Diferencia (mes_fin - mes_inicio) para un concepto dado."""
        valor_inicio = self.obtener_valor(concepto, mes_inicio)
        valor_fin = self.obtener_valor(concepto, mes_fin)
        return valor_fin - valor_inicio

    def promedio(self, concepto, mes_inicio="ENERO", mes_fin="JUNIO"):
        """Promedio de un concepto entre dos meses (incluyendo ambos)."""
        concepto = concepto.upper().strip()
        if concepto not in self._datos:
            raise ValueError(f"El concepto '{concepto}' no existe en {self.nombre}.")
        i_inicio = MESES.index(mes_inicio.upper().strip())
        i_fin = MESES.index(mes_fin.upper().strip())
        valores = self._datos[concepto][i_inicio:i_fin + 1]
        return sum(valores) / len(valores)

    def __str__(self):
        return f"{self.nombre} ({len(self._datos)} conceptos cargados)"


class Facebook(RedSocial):
    def __init__(self):
        super().__init__("FACEBOOK")


class Twitter(RedSocial):
    def __init__(self):
        super().__init__("TWITTER")


class Youtube(RedSocial):
    def __init__(self):
        super().__init__("YOUTUBE")
