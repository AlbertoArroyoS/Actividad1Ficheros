package presentacion;

import java.util.Scanner;

import entidad.Articulo;
import negocio.GestionAlmacen;

public class OpcionesVistaUsuario {
	
	public static void opcion1(GestionAlmacen gestionAlmacen,Scanner leer) {   	

        System.out.println();
        int id = gestionAlmacen.obtenerEntero(leer,"Introduzca Id del articulo: ");
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
    	double precio = gestionAlmacen.obtenerDouble(leer, "Introduzca precio del articulo: ");
    	
    	if(gestionAlmacen.addArticulo(id,nombre,descripcion,stock,precio)) {
    		System.out.println("El articulo se ha añadido correctamente ");
    	}else {
    		System.out.println("El articulo NO se ha posido añadir");
    	}
    	
    	
    }
    
	public static void opcion2(GestionAlmacen gestionAlmacen,Scanner leer) {
    	System.out.println("Introduzca el ID del artículo a borrar: ");
        int idABorrar = leer.nextInt();
        if(gestionAlmacen.borrarArticulo(idABorrar)) {
        	System.out.println("Artículo con ID " + idABorrar + " borrado exitosamente.");
        }              
    }
    
    public static void opcion3(GestionAlmacen gestionAlmacen,Scanner leer) {
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
    public static void opcion4(GestionAlmacen gestionAlmacen,Scanner leer) {
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
    
    public static void opcion5(GestionAlmacen gestionAlmacen,Scanner leer) {
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
    
    public static void opcion6(GestionAlmacen gestionAlmacen,Scanner leer) {
    	if(gestionAlmacen.terminar()== true) {
    		System.out.println("Información guardada correctamente en el archivo.");
    	}else {
    		System.out.println("Programa terminado.");
    	}
    	
    }


}
