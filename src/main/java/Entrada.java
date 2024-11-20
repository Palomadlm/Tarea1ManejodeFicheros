import controller.Concesionario;
    import model.Coche;

    import java.util.Scanner;

    public class Entrada {

        public static void main(String[] args) {
            // Se crea un objeto de la clase Concesionario para acceder al método lecturaCoches y comprobar la existencia de coches.dat
            Concesionario concesionario = new Concesionario();
            concesionario.lecturaCoches();

            //Menú para introducir opciones por consola que ejecuten los distintos métodos de la clase Concesionario
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("---Menú del concesionario---");
                System.out.println("Opción 1 --> Añadir nuevo coche");
                System.out.println("Opción 2 --> Borrar coche por id");
                System.out.println("Opción 3 --> Consultar coche por id");
                System.out.println("Opción 4 --> Listar coches");
                System.out.println("Opción 5 --> Exportar coches a CSV");
                System.out.println("Opción 6 --> Terminar el programa");
                System.out.print("Introduce tu opción: ");
                opcion = scanner.nextInt();

                if (opcion < 1 || opcion > 6) {

                    System.out.println("Introduce una opción válida, del 1 al 6.");
                } else {
                    switch (opcion) {
                        case 1:
                            // Añadir coche
                            System.out.println("Introduce los datos del coche:");

                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();  // Consumir el salto de línea
                            System.out.print("Matrícula: ");
                            String matricula = scanner.nextLine();
                            System.out.print("Marca: ");
                            String marca = scanner.nextLine();
                            System.out.print("Modelo: ");
                            String modelo = scanner.nextLine();
                            System.out.print("Color: ");
                            String color = scanner.nextLine();

                            concesionario.añadirCoche(id, matricula, marca, modelo, color);
                            break;

                        case 2:
                            System.out.print("Introduce el id del coche a eliminar: ");
                            int idEliminar = scanner.nextInt();
                            concesionario.borrarCochePorId(idEliminar);
                            break;
                        case 3:
                            System.out.print("Introduce el id del coche a consultar: ");
                            int idConsultar = scanner.nextInt();
                            Coche coche = concesionario.consultarCochePorId(idConsultar);
                            if (coche != null) {
                                System.out.println(coche);
                            } else {
                                System.out.println("Coche no encontrado.");
                            }
                            break;
                        case 4:
                            System.out.println("Lista de coches del concesionario");
                            concesionario.listarCoches();
                            break;

                        case 5:
                            concesionario.exportarCochesACSV();
                            break;

                        case 6:
                            System.out.println("Fin de programa.");
                            break;

                        default:
                            System.out.println("Opción no válida.");
                    }

                    }
            } while (opcion != 6); // Continuar hasta que la opción sea 6 (terminar)
        }
    }


