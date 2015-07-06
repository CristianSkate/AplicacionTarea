package cl.cmt.unidad1.clases;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido {

	public int idPedido;
	public String cliente;
	public String producto;
	public int cantidad;
	public int total;
	public Date fechaEntrega;
	public Date fechaPedido;
	
	public Pedido(){
		
	}
	
	public String toString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return "Fecha Pedido: "+String.valueOf(dateFormat.format(this.fechaPedido))+System.getProperty("line.separator")
				+String.valueOf(this.idPedido) + ": "+this.cliente +System.getProperty("line.separator")
				 +"Producto "+this.cantidad+" "+ this.producto +System.getProperty("line.separator")
				  +"Total $"+String.valueOf(this.total)+System.getProperty("line.separator")
				   +"Fecha entrega: "+String.valueOf(dateFormat.format(this.fechaEntrega)); 
	}
	
}
