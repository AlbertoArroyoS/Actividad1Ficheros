package persistencia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

import entidad.Articulo;

/**
 * La clase DaoArticulo gestiona la persistencia y manipulación de los datos relacionados con los
 * artículos en un sistema de gestión de almacén. Utiliza un archivo .dat para almacenar
 * y recuperar la información de los artículos.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v2.1
 */
public class DaoArticulo {
	
	/**
     * ArrayList que almacena los objetos de tipo Articulo.
     */
	public static ArrayList<Articulo> articulos = new ArrayList<>();
	private static final String FICHERO = "articulos.dat";
	private File file;

		
	/**
     * Crea el archivo .dat si no existe y si existe, llama al método cargarArticulosDesdeArchivo()
     * que se encarga de cargar los artículos desde un archivo .dat.
     *
     * @throws IOException Si ocurre un error de entrada/salida.
     */
	public void crearFichero() throws IOException {
		file = new File(FICHERO);
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
	}
		
	
	/**
     * Carga los artículos desde un archivo .dat.
     *
     * @param file representa el archivo desde el cual cargar los artículos.
     * @throws ClassNotFoundException Si no se encuentra la clase Articulo al leer desde el archivo.
     */
    public void cargarArticulosDesdeArchivo(File file) throws ClassNotFoundException {
    	
		try (FileInputStream fis = new FileInputStream(file);
			 ObjectInputStream escritor = new ObjectInputStream(fis);) {
            articulos = (ArrayList<Articulo>) escritor.readObject();
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (EOFException e) {
	        System.out.println("El archivo .dat existe, pero no contiene datos.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
    }
    /**
     * Agrega un nuevo artículo a la lista de artículos.
     *
     * @param id representa el ID del nuevo artículo.
     * @param nombre representa el nombre del nuevo artículo.
     * @param descripcion representa la descripción del nuevo artículo.
     * @param stock representa el stock del nuevo artículo.
     * @param precio representa el precio del nuevo artículo.
     * @return "true" si el artículo se agrega exitosamente, "false" en caso contrario.
     */
    public boolean agregarArticulo(int id, String nombre, String descripcion, int stock, double precio ) {
    	    	
        Articulo art = new Articulo();
        art.setId(id);                
        art.setNombre(nombre);        
        art.setDescripcion(descripcion);
        art.setStock(stock);        
        art.setPrecio(precio);
        
        if(articulos.add(art)) {
        	return true;
        }else {
        	return false;
        }
        
    }
    /**
     * Borra un artículo de la lista de artículos.
     *
     * @param idABorrar representa el ID del artículo a borrar.
     * @return "true" si el artículo se borra exitosamente, "false" si no se encuentra.
     */
    public boolean borrarArticulo(int idABorrar) {
        
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
            
            return true;
        } else {
            
            return false;
        }
    }
    /**
     * Consulta un artículo por su ID.
     *
     * @param idAConsultar representa el ID del artículo a consultar.
     * @return El objeto Articulo correspondiente al ID o null si no se encuentra.
     */
    public Articulo consultarArticulo(int idAConsultar) {
        
        // Buscar el artículo con el ID proporcionado
        Articulo articuloAConsultar = null;
        for (Articulo articulo : articulos) {
            if (articulo.getId() == idAConsultar) {
                articuloAConsultar = articulo;
                break;
            }
        }

        if (articuloAConsultar != null) {
            
            return articuloAConsultar;
        } else {
            
            return null;
        }
    }
    /**
     * Verifica si existe un artículo con un ID específico en el ArrayList existente.
     *
     * @param id representa el ID del artículo a verificar.
     * @return "true" si el artículo existe, "false" si no existe.
     */
    public boolean existeArticuloConID(int id) {
        for (Articulo articulo : articulos) {
            if (articulo.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lista todos los artículos almacenados.
     *
     * @return ArrayList que contiene todos los objetos Articulo almacenados.
     */
    public ArrayList<Articulo> listarArticulos() {
    	ArrayList<Articulo> listaAuxiliar = new ArrayList<>();
        
        for (Articulo articulo : articulos) {
            listaAuxiliar.add(articulo);
        }
        return listaAuxiliar;
    }
    
    /**
     * Devuelve la lista de artículos como un ArrayList.
     *
     * @return ArrayList que contiene todos los objetos Articulo almacenados.
     */
    public ArrayList<Articulo> devolverArrayList() {
		return articulos;
    	
    }
    /**
     * Exporta los artículos a un archivo CSV utilizando la librería OpenCSV.
     *
     * @param articulos representa el ArrayList de objetos Articulo a exportar.
     * @return "true" si la operación se realiza con éxito, "false" si hay un error.
     */
    public boolean exportarArticulosCSV(ArrayList<Articulo> articulos) {
        

        int numeroSecuencial = 1;
        String nombreArchivoBase = "articulos";
        String extension = ".csv";
        String nombreArchivo = nombreArchivoBase + extension;

        // Iterar hasta encontrar un nombre de archivo que no existe
        while (new File(nombreArchivo).exists()) {
            numeroSecuencial++;
            nombreArchivo = nombreArchivoBase + numeroSecuencial + extension;
        }

        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(nombreArchivo))) {
            // Escribir encabezados al archivo CSV
            csvWriter.writeNext(new String[]{"ID", "Nombre", "Descripcion", "Stock", "Precio"});

            // Escribir cada artículo al archivo CSV
            for (Articulo articulo : articulos) {
                String[] data = {
                    String.valueOf(articulo.getId()),
                    articulo.getNombre(),
                    articulo.getDescripcion(),
                    String.valueOf(articulo.getStock()),
                    String.valueOf(articulo.getPrecio())
                };
                csvWriter.writeNext(data);
            }
            return true;

        } catch (IOException e) {          
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Método alternativo para exportar artículos a un archivo CSV utilizando FileWriter.
     *
     * @param articulos representa el ArrayList de objetos Articulo a exportar.
     */
    /*
    public void exportarArticulosCSV2(ArrayList<Articulo> articulos) {
        //System.out.println("Exportando artículos a archivo CSV...");

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

           // System.out.println("Artículos exportados correctamente a 'articulos.csv'.");
        } catch (IOException e) {
            System.out.println("Error al exportar artículos a archivo CSV.");
            e.printStackTrace();
        }
    }*/
    
    /**
     * Guarda la información en un archivo .dat y termina el programa.
     *
     * @param articulos representa un ArrayList de objetos Articulo.
     * @return "true" si la operación se realiza con éxito, "false" si hay un error.
     */
    public boolean guardarDat(ArrayList<Articulo> articulos) {
    	
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("articulos.dat"))) {
            oos.writeObject(articulos);           
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar la información en el archivo.");
            e.printStackTrace();
            return false;
        }
       
    }


}
