package cl.cmt.unidad1.clases;

import java.util.ArrayList;

public class Cliente {

	// Se declaran las variables del objeto cliente
	public int id_Vendedor;
	public int id_cliente;
	public String nombre_negocio;
	public String nombre_cliente;
	public String direccion_cliente;
	public String telefono_cliente;
	
	//Se define el metodo para crear y devolver la lista de clientes
	public ArrayList<Cliente> listaClientes(){
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		Cliente cliente = new Cliente();
		
		cliente.id_cliente = 1;
		cliente.nombre_cliente ="Pedro Sandoval";
		cliente.nombre_negocio = "Minimarket La avenida";
		cliente.direccion_cliente = "avenida el palomar #1287, Copiapó";
		cliente.telefono_cliente = "8767876";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 2;
		cliente.nombre_cliente ="Carlos Martinez";
		cliente.nombre_negocio = "Bazar A la vuelta";
		cliente.direccion_cliente = "angel pimentel #0738, Puente alto";
		cliente.telefono_cliente = "9728398";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 3;
		cliente.nombre_cliente ="Miranda Zuñiga";
		cliente.nombre_negocio = "Carillon";
		cliente.direccion_cliente = "pedro aguirre cerda #0285, Puente alto";
		cliente.telefono_cliente = "228378791";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 4;
		cliente.nombre_cliente ="Rosa Astorga";
		cliente.nombre_negocio = "Donde la tia Rosa";
		cliente.direccion_cliente = "concha y toro #4890, Puente alto";
		cliente.telefono_cliente = "227873982";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 5;
		cliente.nombre_cliente ="Carlos Moraga";
		cliente.nombre_negocio = "Bazar de carlitos";
		cliente.direccion_cliente = "el cipres #2021, La florida";
		cliente.telefono_cliente = "9728398";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 6;
		cliente.nombre_cliente ="Marta Alvarez";
		cliente.nombre_negocio = "Bazar de Martita";
		cliente.direccion_cliente = "la colmena #3452, La pintana";
		cliente.telefono_cliente = "8978765";
		
		lista.add(cliente);
				
		return lista;
	}
	
public ArrayList<Cliente> listaClientes1(){
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		Cliente cliente = new Cliente();
		
		cliente.id_cliente = 7;
		cliente.nombre_cliente ="Lita de Achondo";
		cliente.nombre_negocio = "Panadería Cositas Ricas";
		cliente.direccion_cliente = "nemesio vikuña #8000, Puente alto";
		cliente.telefono_cliente = "8390498";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 8;
		cliente.nombre_cliente ="David Peñasco";
		cliente.nombre_negocio = "Botillería San carlos";
		cliente.direccion_cliente = "avda san carlos #1520, Puente alto";
		cliente.telefono_cliente = "73847623";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 9;
		cliente.nombre_cliente ="Marisol Vargas";
		cliente.nombre_negocio = "Restobar Mar & Sol";
		cliente.direccion_cliente = "los guindos #20000, La laja";
		cliente.telefono_cliente = "2378736";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 10;
		cliente.nombre_cliente ="Carmen Flores";
		cliente.nombre_negocio = "El kioskito";
		cliente.direccion_cliente = "jose luis coo #3025, Puente alto";
		cliente.telefono_cliente = "283928364";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 11;
		cliente.nombre_cliente ="Elsa Godoy";
		cliente.nombre_negocio = "El Ratatui";
		cliente.direccion_cliente = "colombia #2555, La florida";
		cliente.telefono_cliente = "938293874";
		
		lista.add(cliente);
		
		cliente = new Cliente();
		cliente.id_cliente = 12;
		cliente.nombre_cliente ="Carmela Carvajal";
		cliente.nombre_negocio = "La tia Carmela";
		cliente.direccion_cliente = "santa rosa #3600, La pintana";
		cliente.telefono_cliente = "8978765";
		
		lista.add(cliente);
				
		return lista;
	}
	
	
	//Este metodo devuelve el string de la clase cliente o "lo que se muestra en la lista"
	public String toString(){
		return String.valueOf(this.id_cliente) + ": "+ this.nombre_cliente +" ("+this.nombre_negocio+")"+System.getProperty ("line.separator")+" Dirección: "+this.direccion_cliente+System.getProperty ("line.separator")+" Telefono: "+this.telefono_cliente ;
	}
}
