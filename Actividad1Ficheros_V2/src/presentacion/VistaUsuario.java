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
	                	gestionAlmacen.addArticulo(daoArticulo,leer);
	                	break;
	                case 2://Borrar artículo por id
	                	gestionAlmacen.borrarArticulo(daoArticulo,leer);
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
    

}
