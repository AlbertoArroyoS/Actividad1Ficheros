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
     * @param id representa el identificador único del artículo.
     * @param nombre representa el nombre del artículo.
     * @param descripcion representa la descripción del artículo.
     * @param stock representa la cantidad en stock del artículo.
     * @param precio representa el precio del artículo.
     * @return True si el artículo se agregó correctamente, false en caso contrario.
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
     * @param idABorrar representa el identificador del artículo a borrar.
     * @return True si el artículo se borró correctamente, false en caso contrario.
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
     * @param idAConsultar representa el identificador del artículo a consultar.
     * @return El artículo consultado o null si no se encuentra.
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
     * @return ArrayList de Articulo que contiene todos los artículos, o null si no hay artículos.
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
     * @return 1 si la exportación fue exitosa, 2 si hubo un error, 3 si se produjo una excepción.
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
     * @return True si la operación fue exitosa, false si hubo un error.
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
     * Método para validar un entero que obtenemos desde la entrada del usuario.
     * Además, verifica si el número entero ingresado ya existe y solicita otro en caso afirmativo
     * Y se controla la excepción si no se introduce un entero.
     * 
     * @param leer representa la Instancia de Scanner utilizada para leer la entrada del usuario.
     * @param mensaje representa el mensaje a mostrar al usuario para solicitar la entrada.
     * @return El número entero ingresado por el usuario, garantizando que no sea duplicado.
     */
    public int obtenerEntero(Scanner leer,String mensaje) {
        int valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println(mensaje);
                valor = leer.nextInt();
                while (existe(valor)) {       	
                    System.out.println("Ya existe un artículo con el ID " + valor + ". No se puede agregar.");
                    System.out.println("Introduzca Id del articulo: ");
                    valor = leer.nextInt();
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
                leer.nextLine();            
            }
        }
        return valor;
    }
    
    /**
     * Método para validar un double que obtenemos desde la entrada del usuario con manejo de excepciones.
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
    /**
     * Verifica si existe un artículo con el ID proporcionado.
     *
     * @param id representa el identificador del artículo a verificar.
     * @return True si existe un artículo con el ID, false en caso contrario.
     */
    public boolean existe(int id) {
    	DaoArticulo daoArticulo = new DaoArticulo();
    	if(daoArticulo.existeArticuloConID(id)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * Inicia los datos del almacén, creando el fichero si no existe.
     */
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
