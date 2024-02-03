package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Usuario;

public interface DaoUsuario {
	
	boolean altaUsuario(Usuario u);
	Usuario obtenerUsuario(String nombre);
	List<Usuario> listarTodosUsuarios();
	
	int crearFichero();

}
