import java.io.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que gestiona un almacen de articulos con funcionalidades como agregar,
 * borrar, consultar, listar, exportar a CSV y terminar el programa.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.1
 */
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
	
			System.out.println("Creado el archivo " + file.getName());
		}else {
			System.out.println("Cargado el archivo " + file.getName());
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
			while (opcion<1 || opcion>6){
				opcion = menu();
			}

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
	                    continuar=false;
	                    break;
	                default:
	                    System.out.println("Opción no válida. Inténtalo de nuevo.");
	            }
			
        } while (continuar);
    }
    
    /**
     * Metodo que muestra un menu de opciones y devuelve la opción elegida por el
     * usuario.
     * 
     * @return Opcion elegida por el usuario.
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
    /**
     * Metodo que carga los artículos desde un archivo.
     * 
     * @param file representa el archivo desde el cual cargar los articulos.
     * @throws ClassNotFoundException Si no se encuentra la clase Articulo al leer
     *                                desde el archivo.
     */
    private static void cargarArticulosDesdeArchivo(File file) throws ClassNotFoundException {
    	
		try (FileInputStream fis = new FileInputStream(file);
			 ObjectInputStream escritor = new ObjectInputStream(fis);) {
            articulos = (ArrayList<Articulo>) escritor.readObject();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (EOFException e) {
	        System.out.println("Se alcanzó el final del archivo. No hay más objetos para leer.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
    }

    /**
     * Metodo que agrega un nuevo articulo a la lista.
     */
    private static void agregarArticulo() {
    	// Limpiar el búfer de nueva línea
    	//leer.nextLine();
    	
        Articulo art = new Articulo();

        int id = obtenerEntero("Introduzca Id del articulo: ");
     // Verificar si ya existe un artículo con el mismo ID
        while (existeArticuloConID(id)) {
            System.out.println("Ya existe un artículo con el ID " + id + ". No se puede agregar.");
            id = obtenerEntero("Introduzca Id del articulo: ");
        }
        art.setId(id);
        System.out.println("Introduzca nombre del articulo: ");
        String nombre = leer.next();
        leer.nextLine();
        art.setNombre(nombre);
        System.out.println("Introduzca descripcion del articulo: ");
        String descripcion = leer.nextLine();
        art.setDescripcion(descripcion);
        int stock = obtenerEntero("Introduzca stock del articulo: ");
        art.setStock(stock);
        double precio = obtenerDouble("Introduzca precio del articulo: ");
        art.setPrecio(precio);
        
        articulos.add(art);
        
        
    }
    /**
     * Metodo que borra un artículo de la lista.
     */
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
    /**
     * Metodo que consulta un articulo por su ID.
     */
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

    /**
     * Metodo que lista todos los articulos.
     */
    private static void listarArticulos() {
        System.out.println("\n=== Listado de Artículos ===");
        for (Articulo articulo : articulos) {
            System.out.println(articulo);
        }
    }
    /**
     * Método que guarda la informacion en un archivo .dat y termina el programa.
     */
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
    
    /**
     * Metodo que exporta los articulos a un archivo CSV.
     */
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
    
    /**
     * Metodo que verifica si existe un artículo con un ID especifico.
     * 
     * @param id ID del articulo a verificar.
     * @return "true" si el artículo existe, "false" en caso contrario.
     */
    private static boolean existeArticuloConID(int id) {
        for (Articulo articulo : articulos) {
            if (articulo.getId() == id) {
                return true;
            }
        }
        return false;
    }
    
    //metodos para validar la entrada de datos
        
    /**
     * Metodo para obtener un entero desde la entrada del usuario con manejo de
     * excepciones.
     * 
     * @param mensaje Mensaje a mostrar al usuario para solicitar la entrada.
     * @return Valor entero ingresado por el usuario.
     */
    private static int obtenerEntero(String mensaje) {
        int valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                valor = leer.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
                leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
            }
        }
        return valor;
    }
    
    /**
     * Metodo para obtener un double desde la entrada del usuario con manejo de
     * excepciones.
     * 
     * @param mensaje Mensaje a mostrar al usuario para solicitar la entrada.
     * @return Valor decimal ingresado por el usuario.
     */
    private static double obtenerDouble(String mensaje) {
        double valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                valor = leer.nextDouble();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Ingrese un número decimal.");
                leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
            }
        }
        return valor;
    }
    
}
