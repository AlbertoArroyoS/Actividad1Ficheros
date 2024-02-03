package modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Usuario;
import modelo.persistencia.acceso.DaoUsuarioFicheros;
import modelo.persistencia.interfaces.DaoUsuario;


public class GestorUsuario {
	

	/**
	 * Método que valida si un usuario puede acceder a nuestro sistema
	 * 
	 * @param usuario El objeto Usuario a ser validado.
	 * @return Entero que indica el resultado de la validación:
	 *         - 2 si el usuario y la contraseña coinciden.
	 *         - 1 si el nombre de usuario ya existe.
	 *         - 0 si el usuario no fue encontrado o no coincide la contraseña.
	 */
	public int validarUsuario(Usuario usuario) {
		DaoUsuario daoUsuario = new DaoUsuarioFicheros();
		Usuario usuarioObtenido = daoUsuario.obtenerUsuario(usuario.getNombre());
		
		if(usuarioObtenido != null) {
			if(usuarioObtenido.getNombre().equalsIgnoreCase(usuario.getNombre()) 
					&& usuarioObtenido.getPassword().equals(usuario.getPassword())) {
			return 2;
			}if(usuarioObtenido.getNombre().equalsIgnoreCase(usuario.getNombre())){
				return 1; 	
			}
			else {
				return 0;
			}
		
		}else {
			return 0;
		}

	}
	
	/**
	 * Método que agrega un usuario a la persistencia
	 * @param usuario a agregar al motor de persistencia
	 * @return true en caso de que usuario se haya agregado, false
	 * en caso contrario.
	 */
	public boolean introducir(Usuario usuario) {
		DaoUsuario daoUsuario = new DaoUsuarioFicheros();
		boolean estaAgregado = daoUsuario.altaUsuario(usuario);
		return estaAgregado;
	}
	
	/**
	 * Método que devuelve la lista de usuarios registrados
	 * @return List<Usuario> que representa la lista de los usuarios .
	 */
	public List<Usuario> usuariosRegistrados(){
		DaoUsuario daoUsuario = new DaoUsuarioFicheros();
		return daoUsuario.listarTodosUsuarios();
	}
	/**
	 * Inicia el proceso de creación del fichero de almacenamiento de usuarios utilizando
	 * un objeto DaoUsuario con implementación de ficheros.
	 *
	 * @return Entero que indica el resultado de la creación del fichero:
	 *         - 1 si el fichero se creó exitosamente.
	 *         - 2 si hubo un error al intentar crear el fichero.
	 *         - 3 si el fichero ya existe.
	 */
	public int iniciarFichero() {
		DaoUsuario daoUsuario = new DaoUsuarioFicheros();		
		int crearFichero = daoUsuario.crearFichero();
		return crearFichero;
	}
	
	
}
