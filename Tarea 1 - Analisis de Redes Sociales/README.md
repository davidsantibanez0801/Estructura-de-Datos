# Tarea 1 - Analisis de Redes Sociales

Programa que lee un archivo CSV con datos historicos de Facebook, Twitter y
YouTube, y permite realizar 4 calculos sobre esos datos desde una interfaz
grafica. Esta tarea se entrega en **dos lenguajes** (mismo diseño de clases,
mismos resultados), para comparar como se resuelve el mismo problema con
Programacion Orientada a Objetos en cada uno:

- [`java/`](./java) — Java + Swing

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

| Responsabilidad | | Java |
|---|-|---|
| Modelo de datos (herencia + encapsulamiento) | | `RedSocial.java` (abstracta), `Facebook.java`, `Twitter.java`, `Youtube.java` |
| Lectura del archivo CSV | | `LectorDatos.java` |
| Calculos (logica de negocio) | | `AnalizadorRedes.java` |
| Interfaz grafica | | `InterfazApp.java` (Swing) + `VentanaSeleccionMeses.java` |
| Punto de entrada |  | `Main.java` |

Separar el programa en estas clases (una responsabilidad por clase) permite
que, si cambia el formato del archivo, solo se modifique `LectorDatos`; si
cambia una formula, solo `AnalizadorRedes`; y si cambia el diseño de la
ventana, solo la clase de interfaz — sin tocar las demas.


Al abrirse la ventana, selecciona el archivo `datos_redes_sociales.csv`
incluido en cada carpeta (o cualquier otro con el mismo formato).


## Bugs encontrados y resueltos durante el desarrollo

- **Java — orden de iteracion de `HashMap`:** al buscar un concepto por
  palabra clave (por ejemplo "CRECIMIENTO"), un `HashMap` normal no
  garantiza el orden en que se agregaron los datos, asi que a veces
  encontraba "PORCENTAJE DE CRECIMIENTO" en vez de "CRECIMIENTO
  (SEGUIDORES)". Se resolvio usando `LinkedHashMap`, que si conserva el
  orden de insercion.
