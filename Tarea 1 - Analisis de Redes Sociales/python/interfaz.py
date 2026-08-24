"""
interfaz.py
------------------------------------------------------------
Clase InterfazApp: maneja TODA la parte visual (ventana,
botones, tablas, mensajes) usando tkinter.

No hace calculos ni lee archivos directamente: para eso usa
a LectorDatos y AnalizadorRedes. Asi cada clase tiene una sola
responsabilidad (mas facil de explicar y de mantener).
------------------------------------------------------------
"""

import tkinter as tk
from tkinter import ttk, filedialog, messagebox

from lector_datos import LectorDatos
from analizador import AnalizadorRedes
from modelos import MESES


class InterfazApp:
    def __init__(self, ventana):
        self.ventana = ventana
        self.ventana.title("Analisis de Redes Sociales")
        self.ventana.geometry("1000x850")
        self.ventana.minsize(900, 700)

        # Atributos que se llenan cuando el usuario carga un archivo
        self.redes = None
        self.analizador = None
        self.ruta_archivo = None

        # Contenedor principal: aqui vamos "cambiando de pantalla"
        self.contenedor = tk.Frame(self.ventana)
        self.contenedor.pack(fill="both", expand=True)

        self.mostrar_menu_inicio()

    # ------------------------------------------------------------------
    # PANTALLA 1: Menu de inicio (seleccionar archivo)
    # ------------------------------------------------------------------
    def mostrar_menu_inicio(self):
        self._limpiar_contenedor()
        self.redes = None
        self.analizador = None

        titulo = tk.Label(
            self.contenedor,
            text="Analisis de Redes Sociales (Facebook / Twitter / YouTube)",
            font=("Arial", 16, "bold"),
        )
        titulo.pack(pady=30)

        subtitulo = tk.Label(
            self.contenedor,
            text="Selecciona un archivo CSV con el formato de la practica para comenzar.",
            font=("Arial", 11),
        )
        subtitulo.pack(pady=5)

        boton_cargar = tk.Button(
            self.contenedor,
            text="Seleccionar archivo CSV",
            font=("Arial", 12),
            width=25,
            height=2,
            command=self.cargar_archivo,
        )
        boton_cargar.pack(pady=30)

    def cargar_archivo(self):
        ruta = filedialog.askopenfilename(
            title="Selecciona el archivo de datos",
            filetypes=[("Archivos CSV", "*.csv"), ("Todos los archivos", "*.*")],
        )
        if not ruta:
            return  # el usuario cancelo

        try:
            lector = LectorDatos(ruta)
            self.redes = lector.leer()
            self.analizador = AnalizadorRedes(self.redes)
            self.ruta_archivo = ruta
            self.mostrar_panel_principal(lector.vista_previa())
        except Exception as error:
            messagebox.showerror(
                "Error al leer el archivo",
                f"No se pudo leer el archivo seleccionado.\n\nDetalle: {error}",
            )

    # ------------------------------------------------------------------
    # PANTALLA 2: Vista previa + 4 botones de accion
    # ------------------------------------------------------------------
    def mostrar_panel_principal(self, filas_vista_previa):
        self._limpiar_contenedor()

        # --- Encabezado ---
        encabezado = tk.Frame(self.contenedor)
        encabezado.pack(fill="x", pady=(10, 0))

        tk.Label(
            encabezado, text=f"Archivo cargado: {self.ruta_archivo}", font=("Arial", 10, "italic")
        ).pack(side="left", padx=10)

        tk.Button(
            encabezado, text="<< Menu de inicio", command=self.mostrar_menu_inicio
        ).pack(side="right", padx=10)

        # --- Vista previa del archivo (Treeview a modo de tabla) ---
        tk.Label(self.contenedor, text="Vista previa de los datos", font=("Arial", 12, "bold")).pack(
            pady=(15, 5)
        )

        marco_tabla = tk.Frame(self.contenedor)
        marco_tabla.pack(fill="both", expand=False, padx=10)

        columnas = filas_vista_previa[0] if filas_vista_previa else []
        tabla = ttk.Treeview(marco_tabla, columns=list(range(len(columnas))), show="headings", height=8)
        for i, nombre_col in enumerate(columnas):
            tabla.heading(i, text=nombre_col)
            tabla.column(i, width=90, anchor="center")
        for fila in filas_vista_previa[1:]:
            tabla.insert("", "end", values=fila)

        scroll_x = ttk.Scrollbar(marco_tabla, orient="horizontal", command=tabla.xview)
        tabla.configure(xscrollcommand=scroll_x.set)
        tabla.pack(fill="x")
        scroll_x.pack(fill="x")

        # --- Botones de accion ---
        tk.Label(self.contenedor, text="Acciones disponibles", font=("Arial", 12, "bold")).pack(
            pady=(20, 5)
        )

        marco_botones = tk.Frame(self.contenedor)
        marco_botones.pack(pady=5)

        tk.Button(
            marco_botones, text="1. Diferencia de seguidores\nTwitter (Enero - Junio)",
            width=28, height=3, command=self.accion_diferencia_seguidores_twitter,
        ).grid(row=0, column=0, padx=8, pady=8)

        tk.Button(
            marco_botones, text="2. Diferencia de visualizaciones\nYouTube (meses a elegir)",
            width=28, height=3, command=self.accion_diferencia_visualizaciones_youtube,
        ).grid(row=0, column=1, padx=8, pady=8)

        tk.Button(
            marco_botones, text="3. Promedio de crecimiento\nTwitter y Facebook (Ene - Jun)",
            width=28, height=3, command=self.accion_promedio_crecimiento,
        ).grid(row=1, column=0, padx=8, pady=8)

        tk.Button(
            marco_botones, text='4. Promedio de "Me gusta"\nYouTube, Twitter y Facebook',
            width=28, height=3, command=self.accion_promedio_me_gusta,
        ).grid(row=1, column=1, padx=8, pady=8)

        # --- Zona de resultados ---
        tk.Label(self.contenedor, text="Resultado", font=("Arial", 12, "bold")).pack(pady=(20, 5))
        self.texto_resultado = tk.Text(self.contenedor, height=6, width=100, wrap="word")
        self.texto_resultado.pack(padx=10, pady=(0, 10))
        self.texto_resultado.insert("1.0", "Selecciona una accion para ver el resultado aqui.")
        self.texto_resultado.config(state="disabled")

    # ------------------------------------------------------------------
    # Utilidad para escribir en la zona de resultados
    # ------------------------------------------------------------------
    def _mostrar_resultado(self, texto):
        self.texto_resultado.config(state="normal")
        self.texto_resultado.delete("1.0", "end")
        self.texto_resultado.insert("1.0", texto)
        self.texto_resultado.config(state="disabled")

    def _limpiar_contenedor(self):
        for widget in self.contenedor.winfo_children():
            widget.destroy()

    # ------------------------------------------------------------------
    # ACCION 1: Diferencia de seguidores de Twitter (Enero - Junio)
    # ------------------------------------------------------------------
    def accion_diferencia_seguidores_twitter(self):
        try:
            diferencia = self.analizador.diferencia_seguidores_twitter()
            texto = (
                f"Diferencia de seguidores en Twitter entre ENERO y JUNIO:\n"
                f"{diferencia:,.0f} seguidores nuevos."
            )
            self._mostrar_resultado(texto)
        except Exception as error:
            messagebox.showerror("Error", str(error))

    # ------------------------------------------------------------------
    # ACCION 2: Diferencia de visualizaciones de YouTube (meses por teclado)
    # ------------------------------------------------------------------
    def accion_diferencia_visualizaciones_youtube(self):
        VentanaSeleccionMeses(self.ventana, self._calcular_diferencia_youtube)

    def _calcular_diferencia_youtube(self, mes_inicio, mes_fin):
        try:
            diferencia = self.analizador.diferencia_visualizaciones_youtube(mes_inicio, mes_fin)
            texto = (
                f"Diferencia de visualizaciones en YouTube entre {mes_inicio} y {mes_fin}:\n"
                f"{diferencia:,.0f} visualizaciones."
            )
            self._mostrar_resultado(texto)
        except Exception as error:
            messagebox.showerror("Error", str(error))

    # ------------------------------------------------------------------
    # ACCION 3: Promedio de crecimiento Twitter y Facebook (Ene-Jun)
    # ------------------------------------------------------------------
    def accion_promedio_crecimiento(self):
        try:
            promedios = self.analizador.promedio_crecimiento_twitter_facebook()
            texto = (
                "Promedio de crecimiento (ENERO a JUNIO):\n"
                f"  - Twitter:  {promedios['TWITTER']:,.2f} seguidores nuevos / mes\n"
                f"  - Facebook: {promedios['FACEBOOK']:,.2f} seguidores nuevos / mes"
            )
            self._mostrar_resultado(texto)
        except Exception as error:
            messagebox.showerror("Error", str(error))

    # ------------------------------------------------------------------
    # ACCION 4: Promedio de "Me gusta" YouTube, Twitter, Facebook
    # ------------------------------------------------------------------
    def accion_promedio_me_gusta(self):
        try:
            promedios = self.analizador.promedio_me_gusta()
            texto = (
                'Promedio de "Me gusta" (ENERO a JUNIO):\n'
                f"  - YouTube:  {promedios['YOUTUBE']:,.2f}\n"
                f"  - Twitter:  {promedios['TWITTER']:,.2f}\n"
                f"  - Facebook: {promedios['FACEBOOK']:,.2f}"
            )
            self._mostrar_resultado(texto)
        except Exception as error:
            messagebox.showerror("Error", str(error))


class VentanaSeleccionMeses(tk.Toplevel):
    """
    Ventana emergente donde el usuario escribe (por teclado) los dos
    meses que quiere comparar para la diferencia de visualizaciones
    de YouTube.
    """

    def __init__(self, ventana_padre, funcion_al_confirmar):
        super().__init__(ventana_padre)
        self.title("Elegir meses")
        self.geometry("320x220")
        self.resizable(False, False)
        self.funcion_al_confirmar = funcion_al_confirmar

        tk.Label(self, text="Escribe el mes inicial:", font=("Arial", 10)).pack(pady=(15, 0))
        self.entrada_mes_inicio = ttk.Combobox(self, values=MESES, state="normal")
        self.entrada_mes_inicio.set("ENERO")
        self.entrada_mes_inicio.pack(pady=5)

        tk.Label(self, text="Escribe el mes final:", font=("Arial", 10)).pack(pady=(10, 0))
        self.entrada_mes_fin = ttk.Combobox(self, values=MESES, state="normal")
        self.entrada_mes_fin.set("JUNIO")
        self.entrada_mes_fin.pack(pady=5)

        tk.Button(self, text="Calcular", command=self._confirmar).pack(pady=20)

    def _confirmar(self):
        mes_inicio = self.entrada_mes_inicio.get().strip().upper()
        mes_fin = self.entrada_mes_fin.get().strip().upper()

        if mes_inicio not in MESES or mes_fin not in MESES:
            messagebox.showerror(
                "Mes invalido",
                "Escribe un mes valido, por ejemplo: ENERO, FEBRERO, MARZO, etc.",
            )
            return

        self.funcion_al_confirmar(mes_inicio, mes_fin)
        self.destroy()
