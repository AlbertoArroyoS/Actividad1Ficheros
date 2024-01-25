package entidad;
import java.io.Serializable;
/**
 * La clase Articulo representa un artículo con los siguientes atributos: ID, nombre, descripción, stock y precio.
 * Implementa la interfaz Serializable para permitir la serialización de objetos de esta clase.
 * 
 * @author Alberto Arroyo Santofimia
 * 
 * @version v1.1
 */
public class Articulo implements Serializable {
   
	private static final long serialVersionUID = -3373007919436317548L;
	private int id;
    private String nombre;
    private String descripcion;
    private int stock;
    private double precio;

    // Constructor, getters y setters
    // ...
    

    @Override
    public String toString() {
        return "Articulo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", stock=" + stock + ", precio=" + precio + "]";
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}