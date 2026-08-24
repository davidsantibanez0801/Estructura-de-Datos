# Version en Python (tkinter)

## Requisitos

- Python 3.8 o superior.
- tkinter (incluido en la instalacion normal de Python en Windows y macOS).
  En Linux, si falta, se instala con:

  ```bash
  sudo apt-get install python3-tk
  ```

No se necesita instalar ninguna libreria externa: todo el programa usa solo
la biblioteca estandar de Python (`csv`, `tkinter`).

## Como ejecutarlo

```bash
cd python
python main.py
```

Al abrirse la ventana, presiona **"Seleccionar archivo CSV"** y elige el
archivo `datos_redes_sociales.csv` de esta misma carpeta (o cualquier otro
con el mismo formato).

## Archivos

| Archivo | Clase(s) | Responsabilidad |
|---|---|---|
| `modelos.py` | `RedSocial`, `Facebook`, `Twitter`, `Youtube` | Herencia y encapsulamiento |
| `lector_datos.py` | `LectorDatos` | Leer el CSV y crear los objetos |
| `analizador.py` | `AnalizadorRedes` | Los 4 calculos |
| `interfaz.py` | `InterfazApp`, `VentanaSeleccionMeses` | Ventana grafica (tkinter) |
| `main.py` | — | Punto de entrada |

Ver el formato esperado del CSV y mas detalle de la arquitectura en el
`README.md` de la carpeta de la tarea (un nivel arriba).
