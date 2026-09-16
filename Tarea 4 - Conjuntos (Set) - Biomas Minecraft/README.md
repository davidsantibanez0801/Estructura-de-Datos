# Tarea 4 - Conjuntos (Set): Biomas descubiertos en Minecraft

Caso de uso elegido de las diapositivas de conjuntos: **"Minecraft: biomas
descubiertos"**. Un jugador explora el mundo y va pisando biomas distintos;
sin importar cuántas veces regrese al mismo bioma, solo cuenta una vez como
"descubierto" — es el comportamiento esencial de un conjunto matemático
(sin elementos repetidos).

## El punto central de la tarea

El `ConjuntoADT<T>` que se nos compartió (parcial) tenía un hueco importante:
`agregarElemento()` no revisaba si el elemento ya existía antes de
insertarlo, así que en realidad se comportaba como una **lista**, no como un
**conjunto**. Se corrigió para que verifique primero con
`contieneElemento()`, y se agregaron las operaciones clásicas entre dos
conjuntos: **unión**, **intersección** y **diferencia**.

## Arquitectura

| Archivo | Clase | Rol |
|---|---|---|
| `ConjuntoADT.java` | `ConjuntoADT<T>` | ADT de conjunto (completado a partir del código parcial dado en clase) |
| `ExploradorMinecraft.java` | `ExploradorMinecraft` | Caso de uso: un jugador que va descubriendo biomas |
| `Main.java` | `Main` | Pruebas: ADT directo, caso de uso, y operaciones entre 2 conjuntos |

Todas las clases están en el paquete `tarea4`.

## Qué prueba el `Main`

1. **Prueba directa del ADT:** agrega "Desierto", "Taiga", "Pantano", y luego
   intenta agregar "Desierto" otra vez — la longitud del conjunto NO
   incrementa, confirmando que ya no acepta duplicados.
2. **El caso de uso:** un jugador (Steve) explora 6 biomas, 2 de ellos
   repetidos a propósito (igual que en la diapositiva original), y el
   conjunto termina con solo 4 biomas únicos.
3. **Dos jugadores:** Steve y Alex exploran biomas distintos (con algunos en
   común), para demostrar `union()`, `interseccion()` y `diferencia()` entre
   sus dos conjuntos.

