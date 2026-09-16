# Tarea 5 - Ejercicios con la clase Nodo<T>

Se construye manualmente, usando **solo referencias entre objetos `Nodo<T>`**
(sin `ArrayList`, arreglos ni ninguna colección de Java), la lista enlazada
mostrada en la imagen de la tarea:

```
head -> Al -> B -> C -> De -> Mc -> Zi -> null
```

## Qué hace el programa (`Main.java`), paso a paso

1. Construye la lista manualmente (6 objetos `Nodo<String>` enlazados con
   `setSiguiente(...)`).
2. Imprime el estado inicial completo de la lista.
3. Imprime únicamente el dato del primer nodo (`Al`).
4. Imprime el estado completo del último nodo (`Zi`, con `siguiente=null`).
5. Inserta un nodo `"Fe"` entre `"De"` y `"Mc"`, e imprime el nuevo estado.
6. Inserta un nodo `"Zz"` al final de la lista, e imprime el nuevo estado.
7. Inserta un nodo `"Aa"` al inicio de la lista (nuevo `head`), e imprime el
   estado final.

Resultado final: `Aa -> Al -> B -> C -> De -> Fe -> Mc -> Zi -> Zz`

## Métodos auxiliares (también solo con referencias `Nodo`)

- `buscarNodo(inicio, valor)` — recorre la lista hasta encontrar el nodo con
  ese dato (se usa para ubicar `"De"` antes de insertar `"Fe"`).
- `obtenerUltimoNodo(inicio)` — recorre la lista hasta el nodo cuyo
  `siguiente` es `null`.
- `imprimirListaSimplificada(inicio)` — imprime la lista como
  `"Al -> B -> C -> ..."` en vez del `toString()` anidado de `Nodo`, solo
  para que sea más fácil de leer en consola (usa un `StringBuilder`
  únicamente para armar el *texto* que se imprime; la lista en sí sigue
  siendo, en todo momento, una cadena de objetos `Nodo` enlazados).

## Estructura del proyecto

| Archivo | Clase | Rol |
|---|---|---|
| `Nodo.java` | `Nodo<T>` | Clase genérica de nodo (tal cual se dio en clase, sin modificar) |
| `Main.java` | `Main` | Construcción manual + las 7 operaciones + métodos auxiliares |

Ambos archivos están en el paquete `mx.unam.aragon.ico.edd.listas` (el mismo
que traía la clase `Nodo` proporcionada), por lo que viven en
`src/mx/unam/aragon/ico/edd/listas/`.
