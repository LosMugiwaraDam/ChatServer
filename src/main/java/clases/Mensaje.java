package clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensaje implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Usuario usuEnv;
	public Usuario usuRec;
	public String texto;
	public Archivo archivo;
	public LocalDateTime fechaHora;
	
	public Mensaje(Usuario usuEnv, Usuario usuRec, String texto, Archivo archivo) {
		super();
		this.usuEnv = usuEnv;
		this.usuRec = usuRec;
		this.texto = texto;
		this.archivo = archivo;
		this.fechaHora = LocalDateTime.now();
	}
	
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
		String rec = "";
		
		if(usuRec != null) {
			rec = usuRec.nombre + " " + usuRec.apellido1;
		}else rec = "grupo";
		
		String archN = "";
		
		if(archivo != null) {
			archN = "archivo:" + archivo.getNombre() + "\n";
		}

		return usuEnv.nombre + " " + usuEnv.apellido1 +
				" a " + rec + ":\n" + 
				archN +
				texto + "\n";
	}
}
