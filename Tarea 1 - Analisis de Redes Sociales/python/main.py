"""
main.py
------------------------------------------------------------
Punto de entrada del programa.

Para ejecutarlo:
    python main.py

Requisitos: Python 3 con tkinter (viene incluido en la
instalacion estandar de Python en Windows/Mac; en Linux a
veces hay que instalar el paquete "python3-tk").
------------------------------------------------------------
"""

import tkinter as tk
from interfaz import InterfazApp


def main():
    ventana = tk.Tk()
    InterfazApp(ventana)
    ventana.mainloop()


if __name__ == "__main__":
    main()
