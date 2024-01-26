package presentacion;



import java.io.IOException;

import java.util.Scanner;

import entidad.Articulo;
import negocio.GestionAlmacen;
import persistencia.DaoArticulo;
/**
 * La clase VistaUsuario proporciona una interfaz de usuario simple para gestionar
 * artículos utilizando un menú interactivo. Permite realizar operaciones como agregar,
 * borrar, consultar, listar, exportar a CSV y terminar el programa.
 *
 * La clase utiliza instancias de las clases DaoArticulo y GestionAlmacen para realizar
 * operaciones en el sistema de gestión de artículos.
 *
 * @author Alberto Arroyo Santofimia
 * 
 * @version v2.0
 */
public class VistaUsuario {
	
	
	private static Scanner leer;
	
	/**
     * Inicializa el scanner estático utilizado para leer la entrada del usuario.
     */
	static {
		leer = new Scanner(System.in);
}

	public static void main(String[] args) throws IOException {
		DaoArticulo daoArticulo = new DaoArticulo();
		GestionAlmacen gestionAlmacen = new GestionAlmacen();
		
    	daoArticulo.crearFichero();
    	
    	
    	boolean continuar = true;

        do {
        	//Cargamos el menu inicial y recuperamos la opción elegida
			int opcion = menu();
			//Si la opcion está fuera del rango de opciones se repetira el menu
			while (opcion<1 || opcion>6){
				opcion = menu();
			}

	            switch (opcion) {
	                case 1://Añadir nuevo artículo
	                	addArticulo(gestionAlmacen,daoArticulo);
	                	break;
	                case 2://Borrar artículo por id
	                	borrarArticulo(daoArticulo);
	                    break;
	                case 3://Consulta artículo por id	                	
	                	consultarPorId(daoArticulo);	                	
	                    break;
	                case 4://Listado de todos
	                	listarTodos(daoArticulo);		                			
	                    break;
	                case 5://Exportar a CSV
	                	exportarCsv(gestionAlmacen, daoArticulo);
	                    break;
	                case 6://Terminar el programa y guardar en el .dat
	                	terminar(gestionAlmacen, daoArticulo);
	                	continuar=false;
	                    break;
	                default:
	                    System.out.println("Opción no válida. Inténtalo de nuevo.");
	            }
			
        } while (continuar);
		

	}
	
	/**
     * Muestra un menú interactivo y devuelve la opción seleccionada por el usuario.
     *
     * @return Opción elegida por el usuario.
     */
    public static int menu() {
		
		int opcion = 0;
		System.out.println("----------------------------------------------------");
		System.out.println("|                      MENU                        |");
		System.out.println("----------------------------------------------------");
		System.out.println("1. Añadir nuevo artículo");
		System.out.println("2. Borrar artículo por id ");
		System.out.println("3. Consulta artículo por id ");
		System.out.println("4. Listado de todos los artículos");
		System.out.println("5. Exportar a CSV");
		System.out.println("6. Terminar el programa");
		System.out.println("----------------------------------------------------");
		System.out.println("Introduzca una opción del 1 al 6, si quiere salir 6");
		System.out.println("----------------------------------------------------");
		
		try {
			opcion = leer.nextInt();
			
		} catch (java.util.InputMismatchException e) {
	        // Atrapar la excepción si se ingresa algo que no es un entero
	        System.out.println("Entrada no válida. Ingrese un número entero.");
	        leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
	    }
		
		if (opcion<1 || opcion > 6) {
			System.out.println("OPCION INCORRECTA");
		}
		
		return opcion;	
	}
    /**
     * Agrega un nuevo artículo solicitando la información al usuario.
     *
     * @param gestionAlmacen La instancia de GestionAlmacen utilizada para obtener información del usuario.
     * @param daoArticulo La instancia de DaoArticulo utilizada para agregar el artículo.
     */
    private static void addArticulo(GestionAlmacen gestionAlmacen, DaoArticulo daoArticulo) {
    	System.out.println("Introduzca Id del articulo: ");
    	int id = leer.nextInt();
    	while (daoArticulo.existeArticuloConID(id)) {
        	
            System.out.println("Ya existe un artículo con el ID " + id + ". No se puede agregar.");
            id = gestionAlmacen.obtenerEntero(leer, "Introduzca Id del articulo: ");
        }
    	leer.nextLine();
    	System.out.println("Introduzca nombre del articulo: ");
        String nombre = leer.nextLine();
        System.out.println("Introduzca descripcion del articulo: ");
        String descripcion = leer.nextLine();
    	int stock = gestionAlmacen.obtenerEntero(leer,"Introduzca stock del articulo: ");
    	System.out.println("Introduzca precio del articulo: ");
    	double precio = leer.nextDouble();
    	
        daoArticulo.agregarArticulo(id, nombre, descripcion, stock, precio);
    }
    /**
     * Borra un artículo solicitando el ID al usuario.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para borrar el artículo.
     */
    private static void borrarArticulo(DaoArticulo daoArticulo){
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
     */
    private static void consultarPorId(DaoArticulo daoArticulo) {
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
    private static void listarTodos(DaoArticulo daoArticulo) {
    	System.out.println("\n=== Listado de Artículos ===");
		for(Articulo ele: daoArticulo.listarArticulos()) {
			System.out.println(ele);
		}
    }
    /**
     * Exporta los artículos a un archivo CSV.
     *
     * @param gestionAlmacen La instancia de GestionAlmacen utilizada para realizar la exportación.
     * @param daoArticulo La instancia de DaoArticulo utilizada para obtener la lista de artículos.
     */
    private static void exportarCsv(GestionAlmacen gestionAlmacen, DaoArticulo daoArticulo) {
    	System.out.println("Exportando artículos a archivo CSV...");
    	try {
    		if(gestionAlmacen.exportarArticulosCSV(daoArticulo.devolverArrayList())) {
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
     * @param gestionAlmacen La instancia de GestionAlmacen utilizada para finalizar el programa.
     * @param daoArticulo La instancia de DaoArticulo utilizada para obtener la lista de artículos.
     */
    private static void terminar(GestionAlmacen gestionAlmacen, DaoArticulo daoArticulo) {
    	if(gestionAlmacen.terminarPrograma(daoArticulo.devolverArrayList())){
    		System.out.println("Información guardada correctamente en el archivo.");
    	}else {
    		System.out.println("Programa terminado.");
    	}
        
    }


}
