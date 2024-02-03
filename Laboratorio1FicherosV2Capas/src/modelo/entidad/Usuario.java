package modelo.entidad;

public class Usuario {
	//Atributos privados
	private String nombre;
	private String password;
	
	//constructor por defecto
	public Usuario() {
		super();
	}
	
	//getter y setters
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//toString

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", password=" + password + "]";
	}
	
	
	
}
