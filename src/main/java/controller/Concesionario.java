package controller;
import dao.CocheDao;
import lombok.AllArgsConstructor;
import model.Coche;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Concesionario implements CocheDao {
//Arraylist donde se guardan los objetos de tipo Coche leidos en coches.dat o introducidos por consola
    private static ArrayList<Coche> coches = new ArrayList<>();

    //Verifica la existencia de coches.dat y procede a leerlo para rellenar el ArrayList
    public void lecturaCoches(){

        File fileCoches = new File("src/main/java/resources/coches.dat");
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        if (fileCoches.exists()&&fileCoches.isFile()){

            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(fileCoches));
                coches = (ArrayList<Coche>) objectInputStream.readObject();
                System.out.println("Coches cargados correctamente desde el fichero.");
            } catch (EOFException e) {
                    // Archivo vacío
                    System.out.println("El archivo está vacío. Inicializando lista de coches como vacía.");
                    coches = new ArrayList<>();
            } catch (IOException e) {
                System.out.println("Error en la lectura del fichero");
            } catch (ClassNotFoundException e) {
                System.out.println("No se puede encontar la clase con la que mapear");
                coches = new ArrayList<>();
            }
            finally{
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException |NullPointerException e) {
                        System.out.println("Error en el cerrado");
                    }
                }
            }
        }
        else {
            System.out.println("El fichero coches.dat no existe");
            coches = new ArrayList<>();
        }}

    //Método añadirCoche implementado
    @Override
    public void añadirCoche(int id, String matricula, String marca, String modelo, String color) {
        // Comprobar que no exista un coche con el mismo id o matrícula
        for (Coche coche : coches) {
            if (coche.getId() == id) {
                System.out.println("El id introducido ya está asociado a un coche.");
                return;
            }
            if (coche.getMatricula().equals(matricula)) {
                System.out.println("La matrícula introducida ya está asociada a un coche.");
                return;
            }
        }
        // Añadir coche a la lista
        coches.add(new Coche(id, matricula, marca, modelo, color));
        System.out.println("Coche añadido correctamente.");
    }

    //Método borrarCochePorId implementado
    @Override
    public void borrarCochePorId(int id) {
        // Buscar el coche por id
        for (int i = 0; i < coches.size(); i++) {
            Coche coche = coches.get(i);
            if (coche.getId() == id) {
                coches.remove(i);  // Eliminar el coche de la lista
                System.out.println("Coche eliminado correctamente.");
                return;  // Salir del método después de eliminar el coche
            }
        }
        // Si no se encuentra el coche
        System.out.println("Coche con el id " + id + " no encontrado.");
    }

    //Método consultarCochePorId implementado
    @Override
    public Coche consultarCochePorId(int id) {
        // Buscar coche por id
        for (Coche coche : coches) {
            if (coche.getId() == id) {
                return coche;
            }
        }
        return null;  // Retorna null si no se encuentra el coche
    }

    //Método listarCoches implementado
    @Override
    public void listarCoches() {
        // Listar todos los coches
        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            for (Coche coche : coches) {
                System.out.println(coche);
            }
        }
    }

    // Método propio para exportar los coches a archivo CSV
    public void exportarCochesACSV() {
        String rutaArchivo = "src/main/java/resources/coches.csv";  // Ruta del archivo CSV

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("ID;Matrícula;Marca;Modelo;Color");
            writer.newLine();

            for (Coche coche : coches) {
                writer.write(coche.getId() + ";" +
                        coche.getMatricula() + ";" +
                        coche.getMarca() + ";" +
                        coche.getModelo() + ";" +
                        coche.getColor());
                writer.newLine();
            }

            System.out.println("Coches exportados a CSV correctamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar los coches a CSV: " + e.getMessage());
        }
    }
}

