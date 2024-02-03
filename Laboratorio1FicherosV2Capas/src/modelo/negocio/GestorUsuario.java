package modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Usuario;
import modelo.persistencia.acceso.DaoUsuarioFicheros;
import modelo.persistencia.interfaces.DaoUsuario;


public class GestorUsuario {
	
	/**
	 * Método que valida si un usuario puede acceder a nuestro sistema
	 * @param usuario que queremos validar, tiene que venir relleno
	 * @return 1 en el caso de que el nombre coincida, 2 en caso de que el usuario y el password coincidan con
	 * el usuario y password almacenado, 0 en caso de que el usuario
	 * no exista o el usuario y password no coincidan
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
	
	public int iniciarFichero() {
		DaoUsuario daoUsuario = new DaoUsuarioFicheros();		
		int crearFichero = daoUsuario.crearFichero();
		return crearFichero;
	}
	
	
}
