package negocio;

import java.io.IOException;
import java.util.ArrayList;
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
 * @version v2.1
 */
public class GestionAlmacen {
	
	/**
     * Agrega un nuevo artículo solicitando la información al usuario.
     *
     * @param gestionAlmacen representa la instancia de GestionAlmacen utilizada para obtener información del usuario.
     * @param daoArticulo representa la instancia de DaoArticulo utilizada para agregar el artículo.
     * @param leer representa la Instancia de Scanner utilizada para leer la entrada del usuario.
     */
    public boolean addArticulo(int id, String nombre, String descripcion, int stock, double precio) {
    	DaoArticulo daoArticulo = new DaoArticulo();
    	
        if(daoArticulo.agregarArticulo(id, nombre, descripcion, stock, precio)) {
        	return true;
        }else {
        	return false;
        }
    }
    /**
     * Borra un artículo solicitando el ID al usuario.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para borrar el artículo.
     * @param leer representa la Instancia de Scanner utilizada para leer la entrada del usuario.
     */
    public boolean borrarArticulo(int idABorrar){
    	                  
    	DaoArticulo daoArticulo = new DaoArticulo();
        if (daoArticulo.borrarArticulo(idABorrar)) {
        	return true;
        }else {
        	return false;
        }
    }
    /**
     * Consulta un artículo por su ID solicitando la información al usuario.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para consultar el artículo.
     * @param leer representa la Instancia de Scanner utilizada para leer la entrada del usuario.
     */
    public Articulo consultarPorId(int idAConsultar) {
    	DaoArticulo daoArticulo = new DaoArticulo();
        Articulo articuloAConsultar = daoArticulo.consultarArticulo(idAConsultar);
        if (articuloAConsultar!= null) {
        	return articuloAConsultar;
        }else {
        	return null;
        }
    	
    }
    /**
     * Lista todos los artículos.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para listar los artículos.
     */

    public ArrayList<Articulo> listarTodos() {
    	DaoArticulo daoArticulo = new DaoArticulo();
    	
    	ArrayList<Articulo> listaAuxiliar = new ArrayList<>();
    	
		for(Articulo ele: daoArticulo.listarArticulos()) {
			listaAuxiliar.add(ele);
		}
		if(listaAuxiliar.isEmpty()) {
			return null;
		}else {
			return listaAuxiliar;
		}
	}
    
    /**
     * Exporta los artículos a un archivo CSV.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para obtener la lista de artículos.
     */
    public int exportarCsv() {
    	DaoArticulo daoArticulo = new DaoArticulo();
    	try {
    		if(daoArticulo.exportarArticulosCSV(daoArticulo.devolverArrayList())) {
    			
    			return 1;
    		}else {
    			
    			return 2;
    		}	                	
    	}catch (Exception e){
    		
    		return 3;
    	}
    	
    }
    /**
     * Finaliza el programa y guarda la información en un archivo .dat.
     *
     * @param daoArticulo La instancia de DaoArticulo utilizada para obtener la lista de artículos.
     */
    public boolean terminar() {
    	DaoArticulo daoArticulo = new DaoArticulo();
    	if(daoArticulo.guardarDat(daoArticulo.devolverArrayList())){
    		
    		return true;
    	}else {
    		
    		return false;
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
                leer.nextLine();            
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
                leer.nextLine(); 
            }
        }
        return valor;
    }
    
    public boolean existe(int id) {
    	DaoArticulo daoArticulo = new DaoArticulo();
    	if(daoArticulo.existeArticuloConID(id)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public void iniciarDatos() {
    	DaoArticulo daoArticulo = new DaoArticulo();
    	try {
			daoArticulo.crearFichero();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
}
