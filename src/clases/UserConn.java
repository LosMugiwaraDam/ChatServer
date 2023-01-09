package clases;

import java.io.IOException;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;

import conexiones.Puertos;
import controllers.UsuariosController;

public class UserConn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Empleado empleado;
	private String ip;
	private LocalDate fecha;
	private LocalTime hora;

	public UserConn(Empleado empleado, String ip) {
		this.empleado = empleado;
		this.ip = ip;
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public String getIp() {
		return ip;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	@Override
	public String toString() {
		return "UserConn [empleado=" + empleado.nombre + " " + empleado.apellido1 + ", ip=" + ip + ", fecha=" + fecha
				+ ", hora=" + hora + "]";
	}
}
