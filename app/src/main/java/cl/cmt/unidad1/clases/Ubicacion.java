package cl.cmt.unidad1.clases;


import cl.cmt.unidad1.activity.R;

import android.content.Context;
import android.content.res.Resources;

public class Ubicacion {

	// Se declaran las variables del objeto cliente
	public int idUbicacion;
	public int idUsuario;
	public String latitud;
	public String longitud;
	public String ip;
	public String direccion;
	Context ctx;
	public Ubicacion(Context ctx){
		this.ctx=ctx;
	}
	
	//Este metodo devuelve el string de la clase cliente o "lo que se muestra en la lista"
	public String toString(){
		return R.string.txtLatitud+" = " + this.latitud
				+ System.getProperty("line.separator")+ ctx.getString(R.string.txtLongitud)+" = " + this.longitud
				+ System.getProperty("line.separator")+ctx.getString(R.string.txtIp)+" = "+ this.ip
				+ System.getProperty("line.separator")+ctx.getString(R.string.txtDireccion)+" = "+ this.direccion;
	}
}
