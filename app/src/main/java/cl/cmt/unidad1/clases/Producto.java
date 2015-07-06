package cl.cmt.unidad1.clases;

import java.util.ArrayList;

public class Producto {
	
	public int idProducto;
	public String nombreProducto;
	public int precio;
	
	
	public ArrayList<Producto> listaProductos(){
		
		ArrayList<Producto> lista = new ArrayList<Producto>();
		Producto producto = new Producto();
		
		producto.idProducto = 1;
		producto.nombreProducto = "Ramitas";
		producto.precio = 300;
		
		lista.add(producto);
		
		producto = new Producto();
		producto.idProducto = 2;
		producto.nombreProducto = "Papas Fritas";
		producto.precio = 600;
		
		lista.add(producto);
		
		producto = new Producto();
		producto.idProducto = 3;
		producto.nombreProducto = "Maní salado";
		producto.precio = 250;
		
		lista.add(producto);
		
		producto = new Producto();
		producto.idProducto = 4;
		producto.nombreProducto = "Snack Mix";
		producto.precio = 500;
		
		lista.add(producto);
		
		producto = new Producto();
		producto.idProducto = 5;
		producto.nombreProducto = "Galletas de soda";
		producto.precio = 200;
		
		lista.add(producto);
		
		producto = new Producto();
		producto.idProducto = 6;
		producto.nombreProducto = "Serranitas";
		producto.precio = 200;
		
		lista.add(producto);
		
		return lista;
	}
	
	public String toString(){
		return String.valueOf(this.idProducto)+": "+this.nombreProducto + " ($"+String.valueOf(this.precio)+")";
	}
	
	
}
