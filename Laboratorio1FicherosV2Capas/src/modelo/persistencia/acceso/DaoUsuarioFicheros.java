package modelo.persistencia.acceso;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Usuario;
import modelo.persistencia.interfaces.DaoUsuario;

public class DaoUsuarioFicheros implements DaoUsuario{
	
	
	private static final String NOMBRE_FICHERO = "datos.txt";
	private File file;
	/**
	 * Método que introduce un objeto usuario en un fichero en formato USUARIO/PASSWORD
	 * @param usuario que queremos introducir en el ficheor
	 * @return <b>true</b> en caso de que hayamos introducido el usuario en el fichoro
	 * , <b>false</b> en caso contrario.
	 */
	@Override
	public boolean altaUsuario (Usuario u) {
		try(FileWriter fw = new FileWriter(NOMBRE_FICHERO, true);//configuramos el fichero para add
				BufferedWriter pw = new BufferedWriter(fw);) {
			//pw.newLine();
			pw.write(u.getNombre());
			pw.write("/");
			pw.write(u.getPassword());
			pw.newLine();
			//pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		return true;
	}

	/**
	 * Método que devuelve un objeto usuario a partir de un nombre
	 * @param nombre representa el nombre del usuario del que vamos a
	 * hacer la busqueda.
	 * @return el objeto usuario si existe en el fichero, <b>>null</b> en caso de
	 * que no exista
	 */
	@Override
	public Usuario obtenerUsuario(String nombre) {
		
		try (FileReader fr = new FileReader(NOMBRE_FICHERO);
				 BufferedReader br = new BufferedReader(fr);) {
			String usuarioTexto= br.readLine();
						
			while(usuarioTexto != null){
				String[] arrayUsuario = usuarioTexto.split("/");
				if(nombre.equals(arrayUsuario[0])) {
					Usuario usuario = new Usuario();
					usuario.setNombre(arrayUsuario[0]);
					usuario.setPassword(arrayUsuario[1]);
					
					return usuario;
				}
				usuarioTexto = br.readLine();								
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que devuelve una lista con los usuarios que ya están registrados
	 * @return <b>List<Usuario> </b> la lista de los usuarios
	 * , <b>null</b> si no existe aun ningun usuario registrado y está vacia.
	 */
	@Override
	public List<Usuario> listarTodosUsuarios() {
		List<Usuario> listaAuxiliar = new ArrayList<>();
		try (FileReader fr = new FileReader(NOMBRE_FICHERO);
				 BufferedReader br = new BufferedReader(fr);) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	        	//verifico si, después de dividir la línea utilizando linea.split("/"), se obtienen exactamente dos partes. 
	            //Si partes.length es diferente de 2, significa que el formato no es el esperado 
	        	//y la línea no es válida para representar un usuario en el archivo.
	        	String[] partes = linea.split("/");
	            if (partes.length == 2) {
	            	//asigna la primera parte (índice 0) al String usuario. En el formato "usuario/contraseña", 
	            	//la primera parte es el nombre de usuario.
	                String nombreTexto = partes[0];
	                String passTexto = partes[1];
	                Usuario usuario = new Usuario(); 
	                usuario.setNombre(nombreTexto);
	                usuario.setPassword(passTexto);
	                listaAuxiliar.add(usuario);
	            }
	        }
	    } catch (IOException e) {
	        
	    }
		
		return listaAuxiliar;
	}

	@Override
	public int crearFichero() {
		file = new File(NOMBRE_FICHERO);
		//si el archivo existe
		
		if (!file.exists()) {// Averiguamos si existe
			//Creamos el fichero
			try {
				file.createNewFile();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return 2;//error ioexception
			}
			return 1;//creado el archivo
			//System.out.println("Creado el archivo " + file.getName());
		}else {
			//System.out.println("Cargado el archivo " + file.getName());
			return 3;//el archivo ya existe y se ha cargado
			
		}
	}

	
	


}
