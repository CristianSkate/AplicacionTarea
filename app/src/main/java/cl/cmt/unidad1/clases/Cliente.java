package cl.cmt.unidad1.clases;

import android.content.Context;
import android.content.res.Resources;


import java.util.ArrayList;

import cl.cmt.unidad1.activity.R;

public class Cliente {
	Context ctx;
	public Cliente(Context ctx){
			this.ctx = ctx;
	}

	// Se declaran las variables del objeto cliente
	public int id_Vendedor;
	public int id_cliente;
	public String nombre_negocio;
	public String nombre_cliente;
	public String direccion_cliente;
	public String telefono_cliente;

	
	
	//Este metodo devuelve el string de la clase cliente o "lo que se muestra en la lista"
	public String toString(){
		return String.valueOf(this.id_cliente) + ": "+ this.nombre_cliente +" ("+this.nombre_negocio+")"+System.getProperty ("line.separator")
				+ctx.getString(R.string.txtDireccion)+" :"+this.direccion_cliente+System.getProperty ("line.separator")
				+ ctx.getString(R.string.txtTelefono)+" :"+this.telefono_cliente ;
	}
}
