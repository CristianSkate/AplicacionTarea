package cl.cmt.unidad1.clases;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entrega {
	
	public int idVendedor;
	public int idEntrega;
	public String cliente;
	public String producto;
	public int cantidad;
	public Date fechaEntrega;
//	public int precio;
	public int total;
	
	public Entrega(){
		
	}
	
	public String toString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return String.valueOf(dateFormat.format(this.fechaEntrega))+System.getProperty ("line.separator")+"Cliente: " +this.cliente +System.getProperty ("line.separator")+this.cantidad+" "+this.producto +System.getProperty ("line.separator")+"Total: $"+String.valueOf(this.total);
		}
	

}
