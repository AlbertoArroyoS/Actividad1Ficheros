package modelo.presentacion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import modelo.negocio.GestorUsuario;
import modelo.presentacion.opciones.OpcionesVista;

public class VistaUsuario {
	
	private static Scanner leer;
	//Para poder leer las opciones del menu	que estan en un metodo estatico
	static {
			leer = new Scanner(System.in);
	}

	public static void main(String[] args) {
		
		OpcionesVista opciones = new OpcionesVista();
		opciones.iniciarPrograma();

		boolean continuar = true;
		
		do {
        	//Cargamos el menu inicial y recuperamos la opción elegida
			int opcion = menu();
			//Si la opcion está fuera del rango de opciones se repetira el menu
			while (opcion<1 || opcion>4){
				opcion = menu();
			}

	            switch (opcion) {
	                case 1://login del usuario, lectura del archivo
	                	opciones.opcion1(leer);
	                    break;
	                case 2://nuevo usuario, escritura del archivo si no existe ya
	                	opciones.opcion2(leer);
	                    break;
	                case 3:
	                	opciones.opcion3();
	                    break;
	                case 4:
	                	System.out.println("Programa terminado");
	                    continuar=false;
	                    break;
	                default:
	                    System.out.println("Opción no válida. Inténtalo de nuevo.");
	            }
			
        } while (continuar);
		

        // Cierro el scanner
        if (leer != null) {
            leer.close();
        }
				
}


public static int menu() {
	
	int opcion = 0;
	System.out.println("----------------------------------------------------");
	System.out.println("|                      MENU                        |");
	System.out.println("----------------------------------------------------");
	System.out.println("1. Login de usuario");
	System.out.println("2. Añadir nuevo usuario ");
	System.out.println("3. Lista de usuarios registrados ");
	System.out.println("4. Terminar el programa");
	System.out.println("----------------------------------------------------");
	System.out.println("Introduzca una opción del 1 al 4, si quiere salir 4");
	System.out.println("----------------------------------------------------");
	
	try {
		opcion = leer.nextInt();
		
	} catch (java.util.InputMismatchException e) {
        // Atrapar la excepción si se ingresa algo que no es un entero
        System.out.println("Entrada no válida. Ingrese un número entero.");
        leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
    }
	
	if (opcion<1 || opcion > 4) {
		System.out.println("OPCION INCORRECTA");
	}
	
	return opcion;	
	}

}
	

	


