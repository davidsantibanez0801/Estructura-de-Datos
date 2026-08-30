# Version en Java (Swing)

## Requisitos

- JDK 8 o superior (probado con OpenJDK 21).
- No se necesita ninguna libreria externa: todo el programa usa solo la
  biblioteca estandar de Java (`java.io`, `java.util`, `javax.swing`).

## Como compilarlo y ejecutarlo

```bash
cd java/src
javac -d ../bin -encoding UTF-8 *.java
cd ../bin
java MainRedes
```

Al abrirse la ventana, presiona **"Seleccionar archivo CSV"** y elige el
archivo `datos_redes_sociales.csv` que esta un nivel arriba (`java/`), o
cualquier otro con el mismo formato.

> Nota: si compilas y ejecutas en un mismo paso desde `src/`, recuerda
> copiar tambien `datos_redes_sociales.csv` a la carpeta desde la que
> ejecutes `java Main`, o selecciona el archivo desde su ruta completa en
> el selector de archivos.

## Archivos

| Archivo | Clase(s) | Responsabilidad |
|---|---|---|
| `RedSocial.java` | `RedSocial` (abstracta) | Herencia y encapsulamiento (`LinkedHashMap` privado) |
| `Facebook.java` / `Twitter.java` / `Youtube.java` | Heredan de `RedSocial` | Representan cada red social |
| `LectorDatos.java` | `LectorDatos` | Leer el CSV y crear los objetos |
| `AnalizadorRedes.java` | `AnalizadorRedes` | Los 4 calculos |
| `InterfazApp.java` | `InterfazApp` | Ventana grafica (Swing) |
| `VentanaSeleccionMeses.java` | `VentanaSeleccionMeses` (`JDialog`) | Ventana emergente para elegir meses |
| `MainRedes.java` | `MainRedes` | Punto de entrada |

Ver el formato esperado del CSV y mas detalle de la arquitectura en el
`README.md` de la carpeta de la tarea (un nivel arriba).
