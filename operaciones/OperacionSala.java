package operaciones;

import java.io.*;

public class OperacionSala {

    /**
     * Restablece todos los asientos de la sala especificada a desocupados.
     * Genera una matriz de 5x5x5 booleana, donde todos los valores son `false`, lo cual representa asientos libres.
     * Luego, guarda esta matriz en un archivo, sobrescribiendo el estado actual de la sala.
     *
     * @param numeroSala Número identificador de la sala a restablecer.
     */
    public static void restablecerSala(int numeroSala) {
        boolean[][][] falseMatrix = new boolean[5][5][5];
        for (int i = 0; i < falseMatrix.length; i++) {
            for (int j = 0; j < falseMatrix[0].length; j++) {
                for (int k = 0; k < falseMatrix[0][0].length; k++) {
                    falseMatrix[i][j][k] = false; // Inicializa cada asiento como desocupado (false)
                }
            }
        }
        escribirSala(numeroSala, falseMatrix); // Guarda el estado inicial de la sala en el archivo
    }

    /**
     * Carga el estado de los asientos de la sala especificada desde un archivo.
     * Deserializa un archivo que contiene una matriz de 5x5x5 booleana que representa el estado de ocupación de los asientos.
     * Si el archivo no existe o se produce un error, imprime una traza de error y devuelve `null`.
     *
     * @param numeroSala Número identificador de la sala a cargar.
     * @return Una matriz de 5x5x5 booleana que representa el estado de ocupación de los asientos, o `null` si ocurre un error.
     */
    public static boolean[][][] cargarSala(int numeroSala) {
        boolean[][][] matrix = null;
        String fileNameConstructor = "sala" + numeroSala + ".ser";
        try (FileInputStream fileIn = new FileInputStream("src/main/resources/" + fileNameConstructor);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            matrix = (boolean[][][]) in.readObject(); // Deserializa la matriz desde el archivo
            System.out.println("\nMatriz cargada desde " + fileNameConstructor + "\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Imprime la traza de error si ocurre un problema de E/S o clase no encontrada
        }
        return matrix;
    }

    /**
     * Guarda el estado actual de los asientos de la sala en un archivo.
     * Serializa la matriz de 5x5x5 booleana que representa el estado de los asientos y la escribe en un archivo.
     *
     * @param numeroSala Número identificador de la sala a guardar.
     * @param sala Matriz de 5x5x5 booleana que representa el estado de ocupación de los asientos.
     */
    public static void escribirSala(int numeroSala, boolean[][][] sala) {
        String fileNameConstructor = "sala" + numeroSala + ".ser";
        try (FileOutputStream fileOut = new FileOutputStream("src/main/resources/" + fileNameConstructor);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(sala); // Serializa la matriz y la guarda en el archivo
            System.out.println("\nMatriz guardada en " + fileNameConstructor + "\n");
        } catch (IOException e) {
            e.printStackTrace(); // Imprime la traza de error si ocurre un problema de E/S
        }
    }
}

