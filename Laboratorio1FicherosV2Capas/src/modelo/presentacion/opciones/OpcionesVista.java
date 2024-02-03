package modelo.presentacion.opciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Usuario;
import modelo.negocio.GestorUsuario;

public class OpcionesVista {
	
	//login del usuario
	public void opcion1(Scanner leer) {
		GestorUsuario gestor= new GestorUsuario();
		Usuario usuarioAuxiliar =introducirDatos(leer);		
		if (gestor.validarUsuario(usuarioAuxiliar) == 2) {
			System.out.println("¡Bienvenido, " + usuarioAuxiliar.getNombre() + "!");
		}else {
        	
            System.out.println("Usuario o contraseña incorrectos.");
        }
			
	}
	//nuevo usuario, escritura del archivo si no existe ya
	public void opcion2(Scanner leer) {
		GestorUsuario gestor= new GestorUsuario();
		Usuario usuarioAuxiliar = introducirDatos(leer);
		if (gestor.validarUsuario(usuarioAuxiliar) == 1 || gestor.validarUsuario(usuarioAuxiliar) == 2) {
			System.out.println("El usuario ya existe. No se puede crear.");
		}else {
			System.out.println("Usuario añadido correctamente.");
        	gestor.introducir(usuarioAuxiliar);
        }
		
	}
	//listar las usuarios
	public void opcion3() {
		GestorUsuario gestor= new GestorUsuario();
		List<Usuario> listaAuxiliar = gestor.usuariosRegistrados();
		if (listaAuxiliar.isEmpty()) {
			System.out.println("No existe ningun usuario registrado");
		}
		else {
			System.out.println("------LISTADO DE USUARIOS REGISTRADOS --------");
			for(Usuario usuario : listaAuxiliar) {				
				System.out.println("Nombre: " + usuario.getNombre());
				System.out.println("Contraseña: " + usuario.getPassword()+"\n");
			}
			
		}	
		
	}
	
	public Usuario introducirDatos(Scanner leer) {
		Usuario usuarioAuxiliar = new Usuario();
		System.out.println("Nombre de usuario");
		String nombreUsuario = leer.next();
		System.out.println("Contraseña");
		String passUsuario = leer.next();
		usuarioAuxiliar.setNombre(nombreUsuario);
		usuarioAuxiliar.setPassword(passUsuario);
		return usuarioAuxiliar;
		
	}
	//metodos al iniciar el programa, de momento llamamos al metodos para crear el archivo
	
	public void iniciarPrograma() {
		GestorUsuario gestor= new GestorUsuario();
		int crearFichero= gestor.iniciarFichero();
		switch (crearFichero) {
        case 1://login del usuario, lectura del archivo
        	System.out.println("Creado el archivo");
            break;
        case 2://nuevo usuario, escritura del archivo si no existe ya
        	System.out.println("Error al crear el archivo");
            break;
        case 3:
        	System.out.println("El archivo ya existe");
            break;

		}
		
		
	}
	
	

}
