package modelo.presentacion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VistaUsuario {
	
	private static Scanner leer;
	//Para poder leer las opciones del menu	que estan en un metodo estatico
	static {
			leer = new Scanner(System.in);
	}

	public static void main(String[] args) {
		String nombreUsuario;
		String passUsuario;

		boolean continuar = true;
		
		do {
        	//Cargamos el menu inicial y recuperamos la opción elegida
			int opcion = menu();
			//Si la opcion está fuera del rango de opciones se repetira el menu
			while (opcion<1 || opcion>3){
				opcion = menu();
			}

	            switch (opcion) {
	                case 1://login del usuario, lectura del archivo
	                	System.out.println("Nombre de usuario");
	            		nombreUsuario = leer.next();
	            		System.out.println("Contraseña");
	            		passUsuario = leer.next();
	                	if (verificarCredenciales(nombreUsuario, passUsuario)) {
	                        System.out.println("¡Bienvenido, " + nombreUsuario + "!");
	                    } else {
	                    	
	                        System.out.println("Usuario o contraseña incorrectos.");
	                    }
	                    break;
	                case 2://nuevo usuario, escritura del archivo si no existe ya
	                	System.out.println("Nombre de usuario");
	                    nombreUsuario = leer.next();
	                    System.out.println("Contraseña");
	                    passUsuario = leer.next();

	                    // Verificar si el usuario ya existe y si no existe lo escribo en el archivo
	                    if (usuarioExistente(nombreUsuario)) {
	                        System.out.println("El usuario ya existe. No se puede crear.");
	                    } else {
	                        try (FileWriter fw = new FileWriter(NOMBRE_FICHERO, true);
	                             BufferedWriter pw = new BufferedWriter(fw);) {
	                            // Si ponemos (nombreFichero,true) add en vez de borrar
	                            pw.write(nombreUsuario + "/" + passUsuario);
	                            pw.newLine();
	                            System.out.println("Datos escritos en el archivo correctamente.");
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    break;
	                case 3:
	                	System.out.println("Programa terminado");
	                    continuar=false;
	                    break;
	                default:
	                    System.out.println("Opción no válida. Inténtalo de nuevo.");
	            }
			
        } while (continuar);
		
		
		//verifico si el usuario y la contraseña introducidos ya estan en el txt
		
		

        // Cierro el scanner
        if (leer != null) {
            leer.close();
        }
				

	}
}

public static int menu() {
	
	int opcion = 0;
	System.out.println("----------------------------------------------------");
	System.out.println("|                      MENU                        |");
	System.out.println("----------------------------------------------------");
	System.out.println("1. Login de usuario");
	System.out.println("2. Añadir nuevo usuario ");
	System.out.println("3. Terminar el programa");
	System.out.println("----------------------------------------------------");
	System.out.println("Introduzca una opción del 1 al 3, si quiere salir 3");
	System.out.println("----------------------------------------------------");
	
	try {
		opcion = leer.nextInt();
		
	} catch (java.util.InputMismatchException e) {
        // Atrapar la excepción si se ingresa algo que no es un entero
        System.out.println("Entrada no válida. Ingrese un número entero.");
        leer.next(); // Limpiar el búfer de entrada para evitar un bucle infinito
    }
	
	if (opcion<1 || opcion > 3) {
		System.out.println("OPCION INCORRECTA");
	}
	
	return opcion;	
}
	

}
