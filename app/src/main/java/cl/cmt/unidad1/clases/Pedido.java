package cl.cmt.unidad1.clases;

import android.content.Context;
import android.content.res.Resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import cl.cmt.unidad1.activity.R;

public class Pedido {

	public int idPedido;
	public int idVendedor;
	public String cliente;
	public String producto;
	public int cantidad;
	public int total;
	public Date fechaEntrega;
	public Date fechaPedido;
	Context ctx;

	public Pedido(Context ctx){
		this.ctx=ctx;
	}
	
	public String toString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return ctx.getString(R.string.txtFechaPedido)+": "+String.valueOf(dateFormat.format(this.fechaPedido))+System.getProperty("line.separator")
				+String.valueOf(this.idPedido) + ": "+this.cliente +System.getProperty("line.separator")
				 +ctx.getString(R.string.txtProducto)+" "+this.cantidad+" "+ this.producto +System.getProperty("line.separator")
				  +ctx.getString(R.string.txtTotal) + " $" + String.valueOf(this.total) + System.getProperty("line.separator")
				+ ctx.getString(R.string.txtFechaEntrega) + ": " + String.valueOf(dateFormat.format(this.fechaEntrega));
	}
	
}
