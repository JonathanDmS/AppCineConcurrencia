package entidades;

import java.io.Serializable;
import operaciones.OperacionSala;

public class Sala implements Serializable {

    private int numeroSala;

    // `funcionSala` es una matriz tridimensional que representa el estado de los asientos en una sala de cine.
    // Primer índice [c1] -> Fila de asientos (letras) -> A - E (representado como 0 - 4)
    // Segundo índice [c2] -> Número de asiento en la fila -> 1 - 5 (representado como 0 - 4)
    // Tercer índice [c3] -> Función o sesión de cine -> Función 1 - 5 (representado como 0 - 4)
    // `true` indica que el asiento está ocupado, `false` indica que está libre.
    private boolean[][][] funcionSala;

    /**
     * Constructor que inicializa la sala con un número específico y carga el estado de los asientos.
     *
     * @param numeroSala Número identificador de la sala de cine.
     *                   Se utiliza para cargar el estado de los asientos a través del método `cargarSala`.
     */
    public Sala(int numeroSala) {
        this.numeroSala = numeroSala;
        this.funcionSala = OperacionSala.cargarSala(numeroSala); // Carga la disponibilidad inicial de asientos.
    }

    /**
     * Intenta ocupar un asiento específico en una función.
     *
     * @param x Fila del asiento (valor entre 0 y 4).
     * @param y Número del asiento en la fila (valor entre 0 y 4).
     * @param z Número de la función o sesión de cine (valor entre 0 y 4).
     * @return `true` si el asiento estaba libre y ahora está ocupado; `false` si ya estaba ocupado.
     */
    public boolean ocuparAsiento(int x, int y, int z) {
        if (funcionSala[x][y][z]) {
            // El asiento ya está ocupado, se devuelve false sin cambios.
            return false;
        } else {
            // Marca el asiento como ocupado y devuelve true.
            funcionSala[x][y][z] = true;
            return true;
        }
    }

    /**
     * Imprime el estado actual de los asientos para cada función de la sala.
     * Muestra la disponibilidad de los asientos en formato de cuadrícula, con 'x' indicando asientos ocupados y 'o' los libres.
     * Agrupa la impresión por función (una cuadrícula por función), y dentro de cada cuadrícula, organiza los asientos en filas.
     */
    public void imprimirSala() {
        for (int i = 0; i < funcionSala.length; i++) {
            System.out.println("Función " + (i + 1) + " de sala " + this.numeroSala);
            for (int j = 0; j < funcionSala[i].length; j++) {
                for (int k = 0; k < funcionSala[i][j].length; k++) {
                    // Imprime 'x' si el asiento está ocupado y 'o' si está libre, con un espacio entre cada columna de asientos.
                    System.out.print(funcionSala[i][j][k] ? "x" : "o");
                    if (k < funcionSala[i][j].length - 1) {
                        System.out.print("   "); // Espacio adicional para claridad entre columnas
                    }
                }
                System.out.println(); // Nueva línea al final de cada fila de asientos
            }
            System.out.println(); // Espacio entre funciones
        }
    }
}