# Versión en Java

## Requisitos

- JDK 8 o superior.
- No requiere librerías externas.

## Compilar y ejecutar

Todas las clases viven en el paquete `nomina` (para evitar choques con la
clase `MainRedes` de otras tareas si el IDE las llega a compilar juntas).

```bash
cd java
javac -d bin -encoding UTF-8 src/nomina/*.java
cd bin
java nomina.MainRedes ../junio.dat
```

> **Nota sobre IDEs:** si abres esta tarea en Eclipse, IntelliJ, NetBeans o
> VS Code, apunta el "Source folder" a `java/src` (no a la raiz del
> repositorio). Al estar las clases dentro del paquete `nomina`, no chocan
> con el `MainRedes` de la Tarea 1 (paquete `redessociales`) aunque ambos
> proyectos queden abiertos a la vez.

## Formato esperado de `junio.dat`

Archivo de texto separado por comas, con encabezado en la primera línea:

```
numero de trabajador, nombres, paterno, materno, horas extra, sueldo base, año de ingreso
2345,CARLOS,PEREZ,CASTILLO,0,9850,2017
5754,ANGEL,GOMEZ,GARRIDO,1,9850,2022
...
```

## Archivos

| Archivo | Clase | Rol |
|---|---|---|
| `ArrayADT.java` | `ArrayADT<T>` | ADT genérico visto en clase |
| `ListaTrabajadoresADT.java` | `ListaTrabajadoresADT` | ADT construido a partir de `ArrayADT` (herencia) |
| `Trabajador.java` | `Trabajador` | Modelo de datos + cálculo de su propio sueldo |
| `LectorTrabajadores.java` | `LectorTrabajadores` | Lectura del archivo |
| `MainRedes.java` | `MainRedes` | Punto de entrada |

Ver el `README.md` de la carpeta de la tarea (un nivel arriba) para el
detalle de las reglas de negocio y la arquitectura completa.
