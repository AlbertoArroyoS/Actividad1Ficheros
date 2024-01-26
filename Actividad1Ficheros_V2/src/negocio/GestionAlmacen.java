package negocio;

import java.util.InputMismatchException;
import java.util.Scanner;
import entidad.Articulo;
import persistencia.DaoArticulo;

/**
 * La clase GestionAlmacen proporciona métodos para gestionar operaciones de salida de datos,
 * como la exportación de artículos a un archivo CSV, la finalización del programa y la validación
 * de la entrada del usuario.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v2.0
 */
public class GestionAlmacen {
	
	/**
     * Agrega un nuevo artículo solicitando la información al usuario.
     *
     * @param gestionAlmacen representa la instancia de GestionAlmacen utilizada para obtener información del usuario.
     * @param daoArticulo representa la instancia de DaoArticulo utilizada para agregar el artículo.
     * @param leer representa la Instancia de Scanner utilizada para leer la entrada del usuario.
     */
    public void addArticulo(DaoArticulo daoArticulo, Scanner leer) {
    	System.out.println("Introduzca Id del articulo: ");
    	int id = leer.nextInt();
    	while (daoArticulo.existeArticuloConID(id)) {
        	
            System.out.println("Ya existe un artículo con el ID " + id + ". No se puede agregar.");
            id = obtenerEntero(leer, "Introduzca Id del articulo: ");
        }
    	leer.nextLine();
    	System.out.println("Introduzca nombre del articulo: ");
        String nombre = leer.nextLine();
        System.out.println("Introduzca descripcion del articulo: ");
        String descripcion = leer.nextLine();
    	int stock = obtenerEntero(leer,"Introduzca stock del articulo: ");
    	System.out.println("Introduzca precio del articulo: ");
    	double precio = leer.nextDouble();
    	
        daoArticulo.agregarArticulo(id, nombre, descripcion, stock, precio);
    }
    /**
     * Borra un artículo solicitando el ID al usuario.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para borrar el artículo.
     * @param leer representa la Instancia de Scanner utilizada para leer la entrada del usuario.
     */
    public void borrarArticulo(DaoArticulo daoArticulo, Scanner leer){
    	System.out.println("Introduzca el ID del artículo a borrar: ");
        int idABorrar = leer.nextInt();                  
        ;
        if (daoArticulo.borrarArticulo(idABorrar)) {
        	System.out.println("Artículo con ID " + idABorrar + " borrado exitosamente.");
        }else {
        	System.out.println("No se encontró ningún artículo con el ID " + idABorrar);
        }
    }
    /**
     * Consulta un artículo por su ID solicitando la información al usuario.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para consultar el artículo.
     * @param leer representa la Instancia de Scanner utilizada para leer la entrada del usuario.
     */
    public void consultarPorId(DaoArticulo daoArticulo, Scanner leer) {
    	System.out.println("Introduzca el ID del artículo a consultar: ");
        int idAConsultar = leer.nextInt();
        Articulo articuloAConsultar = daoArticulo.consultarArticulo(idAConsultar);
    	if(articuloAConsultar!=null) {
    		// Se encontró el artículo, mostrar su información
            System.out.println("=== Información del Artículo ===");
            System.out.println("ID: " + articuloAConsultar.getId());
            System.out.println("Nombre: " + articuloAConsultar.getNombre());
            System.out.println("Descripción: " + articuloAConsultar.getDescripcion());
            System.out.println("Stock: " + articuloAConsultar.getStock());
            System.out.println("Precio: " + articuloAConsultar.getPrecio());
    	}else {
    		System.out.println("No se encontró ningún artículo con el ID " + idAConsultar);
    	}
    }
    /**
     * Lista todos los artículos.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para listar los artículos.
     */
    public void listarTodos(DaoArticulo daoArticulo) {
    	System.out.println("\n=== Listado de Artículos ===");
		for(Articulo ele: daoArticulo.listarArticulos()) {
			System.out.println(ele);
		}
    }
    /**
     * Exporta los artículos a un archivo CSV.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para obtener la lista de artículos.
     */
    public void exportarCsv(DaoArticulo daoArticulo) {
    	System.out.println("Exportando artículos a archivo CSV...");
    	try {
    		if(daoArticulo.exportarArticulosCSV(daoArticulo.devolverArrayList())) {
    			System.out.println("Artículos exportados correctamente");
    		}else {
    			System.out.println("Error al exportar artículos a archivo CSV.");
    		}
    		
;	                	}catch (Exception e){
    		System.out.println("El archivo csv ya existe");
    	}
    	
    }
    /**
     * Finaliza el programa y guarda la información en un archivo .dat.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para obtener la lista de artículos.
     */
    public void terminar(DaoArticulo daoArticulo) {
    	if(daoArticulo.guardarDat(daoArticulo.devolverArrayList())){
    		System.out.println("Información guardada correctamente en el archivo.");
    	}else {
    		System.out.println("Programa terminado.");
    	}
        
    }
 

    //metodos para validar la entrada de datos
        
    /**
     * Método para obtener un entero desde la entrada del usuario con manejo de excepciones.
     *
     * @param leer representa la Instancia de Scanner utilizada para leer la entrada del usuario.
     * @param mensaje representa el mensaje a mostrar al usuario para solicitar la entrada.
     * @return Valor entero ingresado por el usuario.
     */
    public int obtenerEntero(Scanner leer,String mensaje) {
        int valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                valor = leer.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
               // leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
            }
        }
        return valor;
    }
    
    /**
     * Método para obtener un double desde la entrada del usuario con manejo de excepciones.
     *
     * @param leer representa la Instancia de Scanner utilizada para leer la entrada del usuario.
     * @param mensaje representa el mensaje a mostrar al usuario para solicitar la entrada.
     * @return Valor decimal ingresado por el usuario.
     */
    public double obtenerDouble(Scanner leer, String mensaje) {
        double valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                valor = leer.nextDouble();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Ingrese un número decimal.");
               // leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
            }
        }
        return valor;
    }
    
}
