package negocio;
import java.io.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.opencsv.CSVWriter;

import entidad.Articulo;

/**
 * Clase que gestiona un almacen de articulos con funcionalidades como agregar,
 * borrar, consultar, listar, exportar a CSV y terminar el programa.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.1
 */
public class GestionAlmacen {
    

    /**
     * Método que guarda la informacion en un archivo .dat y termina el programa.
     */
    public void terminarPrograma(ArrayList articulos) {
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
     * Metodo que exporta los articulos a un archivo CSV. Utilizo la libreria Opencsv.
     * Se utiliza una variable numeroSecuencial que se incrementa hasta que se encuentra un nombre 
     * de archivo que no existe. Cada vez que se incrementa, se verifica si el nuevo nombre de archivo existe
     */
    public void exportarArticulosCSV(ArrayList articulos) {
        System.out.println("Exportando artículos a archivo CSV...");

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

            System.out.println("Artículos exportados correctamente a '" + nombreArchivo + "'.");
        } catch (IOException e) {
            System.out.println("Error al exportar artículos a archivo CSV.");
            e.printStackTrace();
        }
    }
    
    //Otro metodo alternativo para los CSV con FileWriter
    
    public void exportarArticulosCSV2() {
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
    
    
    
    //metodos para validar la entrada de datos
        
    /**
     * Metodo para obtener un entero desde la entrada del usuario con manejo de
     * excepciones.
     * 
     * @param mensaje Mensaje a mostrar al usuario para solicitar la entrada.
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
                leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
            }
        }
        return valor;
    }
    
}
