package presentacion;

import java.util.Scanner;

import entidad.Articulo;
import negocio.GestionAlmacen;
/**
 * Clase que define las opciones disponibles para la interacción del usuario en la vista.
 * Proporciona métodos para ejecutar distintas operaciones del sistema de gestión de almacén.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v2.1
 */
public class OpcionesVistaUsuario {
	
	/**
     * Método que permite al usuario agregar un nuevo artículo al sistema.
     *
     * @param gestionAlmacen representa la instancia de la clase GestionAlmacen utilizada para realizar operaciones en el almacén.
     * @param leer representa Scanner utilizado para leer la entrada del usuario.
     */
	public void opcion1(GestionAlmacen gestionAlmacen,Scanner leer) {   	

        System.out.println();
        int id = gestionAlmacen.obtenerEntero(leer,"Introduzca Id del articulo: ");
    	leer.nextLine();
    	System.out.println("Introduzca nombre del articulo: ");
        String nombre = leer.nextLine();
        System.out.println("Introduzca descripcion del articulo: ");
        String descripcion = leer.nextLine();
        int stock = gestionAlmacen.obtenerEntero(leer,"Introduzca stock del articulo: ");
    	double precio = gestionAlmacen.obtenerDouble(leer, "Introduzca precio del articulo: ");
    	
    	if(gestionAlmacen.addArticulo(id,nombre,descripcion,stock,precio)) {
    		System.out.println("El articulo se ha añadido correctamente ");
    	}else {
    		System.out.println("El articulo NO se ha posido añadir");
    	}
    	
    	
    }
	/**
     * Método que permite al usuario borrar un artículo del sistema por su ID.
     *
     * @param gestionAlmacen representa la instancia de la clase GestionAlmacen utilizada para realizar operaciones en el almacén.
     * @param leer representa Scanner utilizado para leer la entrada del usuario.
     */
	public void opcion2(GestionAlmacen gestionAlmacen,Scanner leer) {
    	System.out.println("Introduzca el ID del artículo a borrar: ");
        int idABorrar = leer.nextInt();
        if(gestionAlmacen.borrarArticulo(idABorrar)) {
        	System.out.println("Artículo con ID " + idABorrar + " borrado exitosamente.");
        }              
    }
	
	/**
     * Método que permite al usuario consultar la información de un artículo por su ID.
     *
     * @param gestionAlmacen representa la instancia de la clase GestionAlmacen utilizada para realizar operaciones en el almacén.
     * @param leer representa Scanner utilizado para leer la entrada del usuario.
     */
    public void opcion3(GestionAlmacen gestionAlmacen,Scanner leer) {
    	System.out.println("Introduzca el ID del artículo a consultar: ");
        int idAConsultar = leer.nextInt();
        Articulo articuloAConsultar = gestionAlmacen.consultarPorId(idAConsultar);
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
     * Método que lista todos los artículos almacenados en el sistema.
     *
     * @param gestionAlmacen representa la instancia de la clase GestionAlmacen utilizada para realizar operaciones en el almacén.
     * @param leer representa Scanner utilizado para leer la entrada del usuario.
     */
    public void opcion4(GestionAlmacen gestionAlmacen,Scanner leer) {
    	if(gestionAlmacen.listarTodos() == null) {
    		System.out.println("\n=== NO existe ningún Artículo en el almacen ===");
    	}
    	else {
    		System.out.println("\n=== Listado de Artículos ===");
    		for(Articulo ele: gestionAlmacen.listarTodos()) {
    			System.out.println(ele);
    		}
    	}
    }
    /**
     * Método que exporta los artículos a un archivo CSV.
     *
     * @param gestionAlmacen representa la instancia de la clase GestionAlmacen utilizada para realizar operaciones en el almacén.
     * @param leer representa Scanner utilizado para leer la entrada del usuario.
     */
    public void opcion5(GestionAlmacen gestionAlmacen,Scanner leer) {
    	System.out.println("Exportando artículos a archivo CSV...");    	
    	switch (gestionAlmacen.exportarCsv()) {
	    	case 1:
	    		System.out.println("Artículos exportados correctamente");
	        	break;
	        case 2:
	        	System.out.println("Error al exportar artículos a archivo CSV");
	            break;
	        case 3:	                	
	        	System.out.println("El archivo csv ya existe");	                	
	            break;
	        default:
	        	System.out.println("Error al exportar el archivo CSV");        	
	    	}
    }
    /**
     * Método que termina el programa y guarda la información en el archivo .dat.
     *
     * @param gestionAlmacen representa la Instancia de la clase GestionAlmacen utilizada para realizar operaciones en el almacén.
     * @param leer representa Scanner utilizado para leer la entrada del usuario.
     */
    public void opcion6(GestionAlmacen gestionAlmacen,Scanner leer) {
    	if(gestionAlmacen.terminar()== true) {
    		System.out.println("Información guardada correctamente en el archivo.");
    	}else {
    		System.out.println("Programa terminado.");
    	}
    	
    }


}
