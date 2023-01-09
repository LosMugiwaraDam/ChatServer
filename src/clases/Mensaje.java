package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Mensaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Empleado empleado;
	private String texto;
	private LocalDate fecha;
	private LocalTime hora;

	public Mensaje(Empleado empleado, String texto) {
		this.empleado = empleado;
		this.texto = texto;
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public String getTexto() {
		return texto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	
	@Override
	public String toString() {
		return empleado.nombre + " " + empleado.apellido1 + ": " + texto + "\n"
				+ fecha + "  " + String.format("%02d",hora.getHour()) +":"+String.format("%02d",hora.getMinute());
	}

}
