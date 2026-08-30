# Tarea 1 - Analisis de Redes Sociales

Programa que lee un archivo CSV con datos historicos de Facebook, Twitter y
YouTube, y permite realizar 4 calculos sobre esos datos desde una interfaz
grafica. Esta tarea se entrega en **dos lenguajes** (mismo diseño de clases,
mismos resultados), para comparar como se resuelve el mismo problema con
Programacion Orientada a Objetos en cada uno:

- [`python/`](./python) — Python + tkinter
- [`java/`](./java) — Java + Swing

Ambas versiones fueron verificadas con el mismo archivo de datos y producen
resultados numericos identicos.

## Que hace el programa

1. Muestra una pantalla de inicio para seleccionar un archivo `.csv`.
2. Lee el archivo y muestra una vista previa de los datos.
3. Ofrece 4 botones de accion:
   1. Diferencia de seguidores de Twitter entre ENERO y JUNIO.
   2. Diferencia de visualizaciones de YouTube entre dos meses que el
      usuario escribe por teclado.
   3. Promedio de crecimiento de Twitter y Facebook (ENERO a JUNIO).
   4. Promedio de "Me gusta" de YouTube, Twitter y Facebook (ENERO a JUNIO).
4. Un boton "Menu de inicio" permite volver a elegir otro archivo y repetir
   el proceso.

## Arquitectura de clases (igual en ambos lenguajes)

| Responsabilidad | Python | Java |
|---|---|---|
| Modelo de datos (herencia + encapsulamiento) | `modelos.py` → `RedSocial`, `Facebook`, `Twitter`, `Youtube` | `RedSocial.java` (abstracta), `Facebook.java`, `Twitter.java`, `Youtube.java` |
| Lectura del archivo CSV | `lector_datos.py` → `LectorDatos` | `LectorDatos.java` |
| Calculos (logica de negocio) | `analizador.py` → `AnalizadorRedes` | `AnalizadorRedes.java` |
| Interfaz grafica | `interfaz.py` → `InterfazApp` (tkinter) | `InterfazApp.java` (Swing) + `VentanaSeleccionMeses.java` |
| Punto de entrada | `main.py` | `MainRedes.java` |

Separar el programa en estas clases (una responsabilidad por clase) permite
que, si cambia el formato del archivo, solo se modifique `LectorDatos`; si
cambia una formula, solo `AnalizadorRedes`; y si cambia el diseño de la
ventana, solo la clase de interfaz — sin tocar las demas.

## Como ejecutar cada version

Ver el `README.md` dentro de cada carpeta (`python/` y `java/`) para las
instrucciones detalladas. En resumen:

**Python:**
```bash
cd python
python main.py
```

**Java:**
```bash
cd java/src
javac -d ../bin -encoding UTF-8 *.java
cd ../bin
java MainRedes
```

Al abrirse la ventana, selecciona el archivo `datos_redes_sociales.csv`
incluido en cada carpeta (o cualquier otro con el mismo formato).

## Evidencias

En [`evidencias/`](./evidencias) estan los PDF con capturas de pantalla del
funcionamiento de cada version (`Evidencias_Python.pdf` y
`Evidencias_Java.pdf`).

## Bugs encontrados y resueltos durante el desarrollo

- **Java — orden de iteracion de `HashMap`:** al buscar un concepto por
  palabra clave (por ejemplo "CRECIMIENTO"), un `HashMap` normal no
  garantiza el orden en que se agregaron los datos, asi que a veces
  encontraba "PORCENTAJE DE CRECIMIENTO" en vez de "CRECIMIENTO
  (SEGUIDORES)". Se resolvio usando `LinkedHashMap`, que si conserva el
  orden de insercion.
