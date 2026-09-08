# Tarea 2 - Nómina de Trabajadores (ADT Array)

Programa en Java que usa el **ADT Array** visto en clase para almacenar y
procesar la información de los trabajadores de una empresa, leída desde el
archivo `junio.dat`.

## Qué hace el programa

1. Lee `junio.dat` (número de trabajador, nombres, paterno, materno, horas
   extra, sueldo base, año de ingreso).
2. Calcula el sueldo a pagar de cada trabajador, aplicando:
   - Pago de horas extra: **$276.50 por hora**.
   - Prestación por antigüedad: **3% por cada año de antigüedad**, calculado
     sobre (sueldo base + pago de horas extra).
3. Muestra en pantalla al trabajador con **mayor** y **menor** antigüedad.
4. Muestra la **nómina completa** de todos los trabajadores, incluyendo el
   sueldo a pagar de este mes.

## Arquitectura (ADT construido a partir de ArrayADT)

| Archivo | Clase | Rol |
|---|---|---|
| `ArrayADT.java` | `ArrayADT<T>` | ADT genérico visto en clase (se deja sin modificar) |
| `ListaTrabajadoresADT.java` | `ListaTrabajadoresADT` | **Hereda de `ArrayADT<Trabajador>`** (`extends`) y le agrega operaciones propias del negocio: calcular sueldos, buscar mayor/menor antigüedad, imprimir la nómina |
| `Trabajador.java` | `Trabajador` | Representa un renglón del archivo; sabe calcular su propio sueldo |
| `LectorTrabajadores.java` | `LectorTrabajadores` | Lee el archivo y construye el ADT |
| `MainRedes.java` | `MainRedes` | Punto de entrada: orquesta lectura → cálculo → despliegue |

`ListaTrabajadoresADT` es el ADT que pide la tarea: en vez de reescribir la
lógica de un arreglo, se construye **heredando** de `ArrayADT<Trabajador>`,
así que `obtenerElemento()`, `insertarElemento()` y `longitud()` ya vienen
incluidos, y solo se agregan los métodos específicos del negocio
(`calcularSueldos`, `obtenerMayorAntiguedad`, `obtenerMenorAntiguedad`,
`imprimirNomina`).


(Si no se pasa ruta como argumento, el programa busca `junio.dat` en la
carpeta desde la que se ejecuta.)
