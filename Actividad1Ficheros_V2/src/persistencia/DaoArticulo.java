package persistencia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import entidad.Articulo;
import negocio.GestionAlmacen;

public class DaoArticulo {
	
	public static ArrayList<Articulo> articulos = new ArrayList<>();
	private static final String FICHERO = "articulos.dat";
	private File file;
	private GestionAlmacen gestionAlmacen = new GestionAlmacen();
	
	
	
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
     * Metodo que carga los artículos desde un archivo.
     * 
     * @param file representa el archivo desde el cual cargar los articulos.
     * @throws ClassNotFoundException Si no se encuentra la clase Articulo al leer
     *                                desde el archivo.
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
     * Metodo que agrega un nuevo articulo a la lista.
     */
    public void agregarArticulo(int id, String nombre, String descripcion, int stock, double precio ) {
    	
    	
        Articulo art = new Articulo();

        art.setId(id);        
        
        art.setNombre(nombre);
        
        art.setDescripcion(descripcion);

        art.setStock(stock);
        
        art.setPrecio(precio);
        
        articulos.add(art);      
        
    }
    /**
     * Metodo que borra un artículo de la lista.
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
     * Metodo que consulta un articulo por su ID.
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
     * Metodo que lista todos los articulos.
     */
    public ArrayList<Articulo> listarArticulos() {
    	ArrayList<Articulo> listaAuxiliar = new ArrayList<>();
        
        for (Articulo articulo : articulos) {
            listaAuxiliar.add(articulo);
        }
        return listaAuxiliar;
    }
    /**
     * Metodo que verifica si existe un artículo con un ID especifico.
     * 
     * @param id ID del articulo a verificar.
     * @return "true" si el artículo existe, "false" en caso contrario.
     */
    public boolean existeArticuloConID(int id) {
        for (Articulo articulo : articulos) {
            if (articulo.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Articulo> devolverArrayList() {
		return articulos;
    	
    }



}
