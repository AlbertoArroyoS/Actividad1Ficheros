package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Usuario;

public interface DaoUsuario {
	
	/**
	 * Metodo que da de alta una persona en la BBDD. Se generar� el ID de manera
	 * autom�tica.
	 * @param p la persona a dar de alta
	 * @return true en caso de que se haya dado de alta. false en caso de error
	 * con la BBDD.
	 * 
	 * 
	 */
	boolean alta(Usuario u);
	boolean baja(int id);
	/**
	 * Metodo que modifica una persona en la BBDD. La modificaci�n ser� a partir
	 * del ID que contenga la persona.
	 * @param p la persona a modificar
	 * @return true en caso de que se haya modificado. False en caso de error
	 * con la BBDD.
	 */

	Usuario obtenerPassword (int id);
	List<Usuario> listar();

}
