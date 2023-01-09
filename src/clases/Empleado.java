package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Empleado extends Persona implements Serializable, Borrable, Comparable<Empleado>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long nEmpl;
	private Enums.Puesto puesto;
	private Double sueldo;
	private String contr;
	private Boolean activo;

	public Empleado(String nombre, String apellido1, String apellido2, Enums.Sexo genero, String dni, LocalDate fNacimiento,
                    Long nEmpl, Enums.Puesto puesto, Double sueldo, String contr) {
		super(nombre, apellido1, apellido2, genero, dni, fNacimiento);
		this.nEmpl = nEmpl;
		this.puesto = puesto;
		this.sueldo = sueldo;
		this.contr = contr;
		this.activo = true;
	}

	public Long getnEmpl() {
		return nEmpl;
	}

	public void setnEmpl(Long nEmpl) {
		this.nEmpl = nEmpl;
	}

	public Enums.Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Enums.Puesto puesto) {
		this.puesto = puesto;
	}

	public Double getSueldo() {
		return sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}

	public String getContr() {
		return contr;
	}

	public void setContr(String contr) {
		this.contr = contr;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public void borrar() {
		activo = false;
	}

	public void recuperar() {
		activo = true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nEmpl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(nEmpl, other.nEmpl);
	}

	@Override
	public int compareTo(Empleado o) {
		return (int) (this.nEmpl - o.nEmpl);
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", genero="
				+ genero + ", dni=" + dni + ", fNacimiento=" + fNacimiento + ", nEmpl=" + nEmpl + ", puesto=" + puesto
				+ ", sueldo=" + sueldo + ", contraseña=" + contr + ", activo=" + activo + "]";
	}

}