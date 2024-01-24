import java.io.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class GestionAlmacen {
    
	private static ArrayList<Articulo> articulos = new ArrayList<>();
	private static Scanner leer;
	
	//Para poder leer las opciones del menu	que estan en un metodo estatico
		static {
				leer = new Scanner(System.in);
		}


    public static void main(String[] args) throws IOException {
    	
    	File file = new File("articulos.dat");
    	//ArrayList<Articulo> articulos = new ArrayList<>();
    	//si el archivo existe
    	
    	if (!file.exists()) {// Averiguamos si existe
			//Creamos el fichero
			file.createNewFile();
			//También podemos crear un directorio, normalmente le quitamos la 
			//extension al fichero (fn)
			// fn.mkdir();
			
			System.out.println("Creado el archivo " + file.getName());
		}else {
			try {
				cargarArticulosDesdeArchivo(file);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	boolean continuar = true;

        do {
        	//Cargamos el menu inicial y recuperamos la opción elegida
			int opcion = menu();
			//Si la opcion está fuera del rango de opciones se repetira el menu
			while (opcion<1 || opcion>5){
				opcion = menu();
			}if (opcion == 5) {
				System.out.println("Salir del programa");
				continuar=false;
			}else {

	            switch (opcion) {
	                case 1:
	                    agregarArticulo();
	                    break;
	                case 2:
	                    borrarArticulo();
	                    break;
	                case 3:
	                    consultarArticulo();
	                    break;
	                case 4:
	                    listarArticulos();
	                    break;
	                case 5:
	                	exportarArticulosCSV();
	                    break;
	                case 6:
	                    terminarPrograma();
	                    break;
	                default:
	                    System.out.println("Opción no válida. Inténtalo de nuevo.");
	            }
			}
        } while (continuar);
    }

    private static void cargarArticulosDesdeArchivo(File file) throws ClassNotFoundException {
    	
		try (FileInputStream fis = new FileInputStream(file);
			 ObjectInputStream escritor = new ObjectInputStream(fis);) {
            articulos = (ArrayList<Articulo>) escritor.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		/*
		//-------------
		// Abrir el fichero
		// Lee el objeto guardado en el archivo alumno.dat
		try (FileInputStream file = new FileInputStream("articulos.dat");
			ObjectInputStream escritor = new ObjectInputStream(file);){
			
			Articulo art1 = (Articulo) escritor.readObject();//importante castearlo, ya que read objet
														//devuelve una referencia de tipo Object
			System.out.println("Nombre del articulo: " + art1.getNombre());
			System.out.println("Id del articulo: " + art1.getId());
			System.out.println("Descripción del articulo: " + art1.getDescripcion());
			System.out.println("Stock del articulo: " + art1.getStock());
			System.out.println("Precio del articulo: " + art1.getDescripcion());
			
			
			
		} catch (IOException e) {
			System.out.println("Error al leer en el fichero");
			e.printStackTrace();
		}*/
    }
/*
    private static void mostrarMenu() {
        System.out.println("\n=== Menú ===");
        System.out.println("1. Añadir nuevo artículo");
        System.out.println("2. Borrar artículo por id");
        System.out.println("3. Consultar artículo por id");
        System.out.println("4. Listado de todos los artículos");
        System.out.println("5. Terminar el programa");
    }
*/
    public static int menu() {
		
		int opcion = 0;
		System.out.println("----------------------------------------------------");
		System.out.println("|                      MENU                        |");
		System.out.println("----------------------------------------------------");
		System.out.println("1. Consultar pelicula por ID");
		System.out.println("2. Consultar película por título ");
		System.out.println("3. Consultar películas por director ");
		System.out.println("4. Añadir pelicula ");
		System.out.println("5. Exportar a CSV");
		System.out.println("6. Salir de la aplicación");
		System.out.println("----------------------------------------------------");
		System.out.println("Introduzca una opción del 1 al 5, si quiere salir 5");
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


    private static void agregarArticulo() {
    	// Limpiar el búfer de nueva línea
    	//leer.nextLine();
    	
        Articulo art = new Articulo();
        System.out.println("Introduzca Id del articulo: ");
        int id = leer.nextInt();
        art.setId(id);
        System.out.println("Introduzca nombre del articulo: ");
        String nombre = leer.next();
        art.setNombre(nombre);
        System.out.println("Introduzca descripcion del articulo: ");
        String descripcion = leer.nextLine();
        art.setDescripcion(descripcion);
        System.out.println("Introduzca stock del articulo: ");
        int stock = leer.nextInt();
        art.setStock(stock);
        System.out.println("Introduzca precio del articulo: ");
        double precio = leer.nextDouble();
        art.setPrecio(precio);
    }

    private static void borrarArticulo() {
        System.out.println("Introduzca el ID del artículo a borrar: ");
        int idABorrar = leer.nextInt();

        // Buscar el artículo con el ID proporcionado
        Articulo articuloABorrar = null;
        for (Articulo articulo : articulos) {
            if (articulo.getId() == idABorrar) {
                articuloABorrar = articulo;
                break;
            }
        }

        if (articuloABorrar != null) {
            // Se encontró el artículo, proceder a borrarlo
            articulos.remove(articuloABorrar);
            System.out.println("Artículo con ID " + idABorrar + " borrado exitosamente.");
        } else {
            System.out.println("No se encontró ningún artículo con el ID " + idABorrar);
        }
    }

    private static void consultarArticulo() {
        System.out.println("Introduzca el ID del artículo a consultar: ");
        int idAConsultar = leer.nextInt();

        // Buscar el artículo con el ID proporcionado
        Articulo articuloAConsultar = null;
        for (Articulo articulo : articulos) {
            if (articulo.getId() == idAConsultar) {
                articuloAConsultar = articulo;
                break;
            }
        }

        if (articuloAConsultar != null) {
            // Se encontró el artículo, mostrar su información
            System.out.println("=== Información del Artículo ===");
            System.out.println("ID: " + articuloAConsultar.getId());
            System.out.println("Nombre: " + articuloAConsultar.getNombre());
            System.out.println("Descripción: " + articuloAConsultar.getDescripcion());
            System.out.println("Stock: " + articuloAConsultar.getStock());
            System.out.println("Precio: " + articuloAConsultar.getPrecio());
        } else {
            System.out.println("No se encontró ningún artículo con el ID " + idAConsultar);
        }
    }


    private static void listarArticulos() {
        System.out.println("\n=== Listado de Artículos ===");
        for (Articulo articulo : articulos) {
            System.out.println(articulo);
        }
    }

    private static void terminarPrograma() {
        System.out.println("Guardando información en el archivo...");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("articulos.dat"))) {
            oos.writeObject(articulos);
            System.out.println("Información guardada correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar la información en el archivo.");
            e.printStackTrace();
        }

        System.out.println("Programa terminado.");
    }
    
    private static void exportarArticulosCSV() {
        System.out.println("Exportando artículos a archivo CSV...");

        try (FileWriter csvWriter = new FileWriter("articulos.csv")) {
            // Escribir encabezados al archivo CSV
            csvWriter.append("ID,Nombre,Descripción,Stock,Precio");
            csvWriter.append("\n");

            // Escribir cada artículo al archivo CSV
            for (Articulo articulo : articulos) {
                csvWriter.append(String.valueOf(articulo.getId())).append(",");
                csvWriter.append(articulo.getNombre()).append(",");
                csvWriter.append(articulo.getDescripcion()).append(",");
                csvWriter.append(String.valueOf(articulo.getStock())).append(",");
                csvWriter.append(String.valueOf(articulo.getPrecio()));
                csvWriter.append("\n");
            }

            System.out.println("Artículos exportados correctamente a 'articulos.csv'.");
        } catch (IOException e) {
            System.out.println("Error al exportar artículos a archivo CSV.");
            e.printStackTrace();
        }
    }

}
