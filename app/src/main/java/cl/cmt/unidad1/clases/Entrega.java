package cl.cmt.unidad1.clases;

import android.content.Context;
import android.content.res.Resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import cl.cmt.unidad1.activity.R;

public class Entrega {
	
	public int idVendedor;
	public int idEntrega;
	public String cliente;
	public String producto;
	public int cantidad;
	public Date fechaEntrega;
//	public int precio;
	public int total;
	Context ctx;

	public Entrega(Context ctx){
		this.ctx=ctx;
	}
	
	public String toString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return String.valueOf(dateFormat.format(this.fechaEntrega))+System.getProperty("line.separator")
				+ ctx.getString(R.string.txtCliente)+": " +this.cliente +System.getProperty ("line.separator")
				+this.cantidad+" "+this.producto +System.getProperty ("line.separator")
				+ctx.getString(R.string.txtTotal) + ": $" + String.valueOf(this.total);
		}
	

}
