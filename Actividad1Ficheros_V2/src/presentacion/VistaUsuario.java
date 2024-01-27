package presentacion;



import java.io.IOException;

import java.util.Scanner;
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
 * @version v2.1
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
		//llamo al metodo para verificar si existe el fichero artículos.dat y si no, lo crea
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
	                	addArticulo(gestionAlmacen);
	                	break;
	                case 2://Borrar artículo por id
	                	borrarArticulo(gestionAlmacen);
	                    break;
	                case 3://Consulta artículo por id	                	
	                	gestionAlmacen.consultarPorId(daoArticulo,leer);	                	
	                    break;
	                case 4://Listado de todos
	                	gestionAlmacen.listarTodos(daoArticulo);		                			
	                    break;
	                case 5://Exportar a CSV
	                	gestionAlmacen.exportarCsv(daoArticulo);
	                    break;
	                case 6://Terminar el programa y guardar en el .dat
	                	gestionAlmacen.terminar(daoArticulo);
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
    private static void addArticulo(GestionAlmacen gestionAlmacen) {
    	System.out.println("Introduzca Id del articulo: ");
    	int id = leer.nextInt();
    	//Comprobamos si el id existe y en caso de que exista lo vuelve a pedir
    	while (gestionAlmacen.existe(id)) {       	
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
    	
    	if(gestionAlmacen.addArticulo(id,nombre,descripcion,stock,precio)) {
    		System.out.println("El articulo se ha añadido correctamente ");
    	}else {
    		System.out.println("El articulo NO se ha posido añadir");
    	}
    	
    	
    }
    
    private static void borrarArticulo(GestionAlmacen gestionAlmacen) {
    	System.out.println("Introduzca el ID del artículo a borrar: ");
        int idABorrar = leer.nextInt();
        if(gestionAlmacen.borrarArticulo(idABorrar)) {
        	System.out.println("Artículo con ID " + idABorrar + " borrado exitosamente.");
        }              
    }
    

}
