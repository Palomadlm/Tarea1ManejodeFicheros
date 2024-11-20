package dao;
import model.Coche;

public interface CocheDao {

        // Método sin implementar para añadir un coche a través de los atributos que definen la clase Coche
        void añadirCoche(int id, String matricula, String marca, String modelo, String color);

        // Método sin implementar para borrar un coche a través del id
        void borrarCochePorId(int id);

        // Método sin implementar para consultar un coche a través del id
        Coche consultarCochePorId(int id);

        // Método sin implementar para listar todos los coches presentes en el Arraylist coches
        void listarCoches();
    }


