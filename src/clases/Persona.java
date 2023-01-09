package clases;

import java.io.Serializable;
import java.time.LocalDate;

import clases.Enums.Sexo;

public abstract class Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String apellido1;
	protected String apellido2;
	protected Sexo genero;
	protected String dni;
	protected LocalDate fNacimiento;
	
	public Persona(String nombre, String apellido1, String apellido2, Sexo genero, String dni, LocalDate fNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.genero = genero;
		this.dni = dni;
		this.fNacimiento = fNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Enums.Sexo getGenero() {
		return genero;
	}

	public void setGenero(Enums.Sexo genero) {
		this.genero = genero;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getfNacimineto() {
		return fNacimiento;
	}
	
	public String getfNacString() {
		return fNacimiento.getDayOfMonth()+"/"+fNacimiento.getMonthValue()+"/"+fNacimiento.getYear();
	}

	public void setfNacimineto(LocalDate fNacimineto) {
		this.fNacimiento = fNacimineto;
	}
}
