import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login {
	
	
	public static final String NOMBRE_FICHERO = "datos.txt";
	
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
	                case 1:
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
	                case 2:
	                	System.out.println("Nombre de usuario");
	                    nombreUsuario = leer.next();
	                    System.out.println("Contraseña");
	                    passUsuario = leer.next();

	                    // Verificar si el usuario ya existe
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
	
	
	
	/**
     * Verifica si las credenciales proporcionadas coinciden con las almacenadas en el archivo.
     *
     * @param nombreUsuario represante el nombre de usuario proporcionado.
     * @param passUsuario representa la contraseña proporcionada.
     * @return "true" si las credenciales son válidas, "false" en caso contrario.
     */
	private static boolean verificarCredenciales(String nombreUsuario, String passUsuario) {
        //leo el archivo y lo separo en un String Array, en la posicion 0 usuario y en la 1 contraseña
		//try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_FICHERO))) {
		try (FileReader fr = new FileReader(NOMBRE_FICHERO);
				 BufferedReader br = new BufferedReader(fr);) {    
			String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("/");
                if (partes.length == 2) {
                    String usuario = partes[0];
                    String password = partes[1];
                    if (usuario.equals(nombreUsuario) && password.equals(passUsuario)) {
                        return true; // Usuario y contraseña encontrados
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No hay ningún usuario registrado.");
        }
        return false; // Usuario y contraseña no encontrados
    }
	
	//metodo para comprobar si el usuario existe
	
	private static boolean usuarioExistente(String nombreUsuario) {
	    try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_FICHERO))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split("/");
	            if (partes.length == 2) {
	                String usuario = partes[0];
	                if (usuario.equals(nombreUsuario)) {
	                    return true; // Usuario encontrado
	                }
	            }
	        }
	    } catch (IOException e) {
	        
	    }
	    return false; // Usuario no encontrado
	}

}
