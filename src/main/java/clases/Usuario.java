package clases;

import java.io.Serializable;

public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double nEmpl;
	public String nombre;
	public String apellido1;
	public String apellido2;
	public Usuario(double nEmpl,String nombre, String apellido1, String apellido2) {
		super();
		this.nEmpl = nEmpl;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}
	
}
