package clases;

import java.io.Serializable;

public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String nombre;
	public String apellido1;
	public String apellido2;
	public String fotoPerfil;
	public Usuario(String nombre, String apellido1, String apellido2) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fotoPerfil = "";
	}
	
}
