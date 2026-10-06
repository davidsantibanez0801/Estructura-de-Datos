# Tarea 7 - Estructura de datos de diapositivas (Lista Ligada)

**Materia:** Estructura de Datos · 3er semestre · Ingeniería en Computación (FES Aragón)

## Objetivo
Terminar de implementar el TDA **Lista Ligada** visto en las diapositivas (métodos faltantes) y
probarlo con una clase distinta de `String`. Se usa la clase **`PolloAsado`**
(corte, salsa y precio). La lista es genérica (`ListaLigadaADT<T>`), por lo que funciona con
cualquier clase (`Perro`, `PolloAsado`, etc.).

## Estructura
```
Tarea 7- Estructura de datos de diapositivas (ListaLigada)/
├── README.md
├── .gitignore
├── java/src/tarea7/
│   ├── Nodo.java                  # nodo genérico (dato + siguiente)
│   ├── ListaLigadaADT.java        # TDA lista ligada (métodos de clase + faltantes)
│   ├── PolloAsado.java            # clase de prueba (equals/hashCode/toString)
│   ├── Main.java                  # demostración de todos los métodos
│   └── PruebasListaLigada.java    # 34 pruebas automáticas (PASS/FAIL)
└── evidencias/
    ├── Tarea7_ListaLigada_Evidencias.pdf
    ├── capturas/                  # capturas de terminal
    └── salidas/                   # salida real en texto
```

## Métodos
| Método | Origen | Complejidad |
|---|---|---|
| `agregar(T)` | Diapositivas | O(n) |
| `transversal()` | Diapositivas | O(n) |
| `actualizar(T, T)` | Diapositivas (corregido) | O(n) |
| `getTamanio()` | Diapositivas | O(n) |
| `agregarDespuesDe(T, T)` | Diapositivas (corregido) | O(n) |
| `estaVacia()` | **Nuevo** | O(1) |
| `agregarAlInicio(T)` | **Nuevo** | O(1) |
| `agregarAntesDe(T, T)` | **Nuevo** | O(n) |
| `insertarEn(int, T)` | **Nuevo** | O(n) |
| `contiene(T)` | **Nuevo** | O(n) |
| `indiceDe(T)` | **Nuevo** | O(n) |
| `obtener(int)` | **Nuevo** | O(n) |
| `eliminar(T)` | **Nuevo** | O(n) |
| `eliminarPrimero()` | **Nuevo** | O(1) |
| `eliminarUltimo()` | **Nuevo** | O(n) |
| `vaciar()` | **Nuevo** | O(1) |
| `toString()` | **Nuevo** | O(n) |

## Cómo ejecutar
Desde la carpeta `java/`:
```bash
javac -d out src/tarea7/*.java
java -cp out tarea7.Main                 # demostración
java -cp out tarea7.PruebasListaLigada   # pruebas automáticas
```
En IntelliJ IDEA: abrir `java/` como módulo, marcar `src` como *Sources Root* y ejecutar `Main`.

## Notas
- `PolloAsado` sobreescribe `equals()`/`hashCode()`: la lista compara con `equals()`; sin esto,
  `actualizar`, `agregarDespuesDe`, `eliminar` y `contiene` compararían referencias y fallarían.
- Se corrigió el `NullPointerException` de `actualizar()` y `agregarDespuesDe()` cuando el dato
  buscado no existe, y `transversal()` ahora termina con salto de línea.
