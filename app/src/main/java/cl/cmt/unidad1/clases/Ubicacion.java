package cl.cmt.unidad1.clases;

import java.util.ArrayList;

public class Ubicacion {

	// Se declaran las variables del objeto cliente
	public int idUbicacion;
	public int idUsuario;
	public String latitud;
	public String longitud;
	public String ip;
	public String direccion;

	
	//Este metodo devuelve el string de la clase cliente o "lo que se muestra en la lista"
	public String toString(){
		return "Latitud = " + this.latitud
				+ System.getProperty("line.separator")+ "Longitud = " + this.longitud
				+ System.getProperty("line.separator")+"Dirección IP = "+ this.ip
				+ System.getProperty("line.separator")+"Dirección = "+ this.direccion;
	}
}
