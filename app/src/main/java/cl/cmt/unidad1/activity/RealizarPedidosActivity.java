package cl.cmt.unidad1.activity;

import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cl.cmt.unidad1.clases.Cliente;
import cl.cmt.unidad1.clases.Pedido;
import cl.cmt.unidad1.clases.Producto;
import cl.cmt.unidad1.dao.ClientesDS;
import cl.cmt.unidad1.dao.PedidosDS;
import cl.cmt.unidad1.dao.ProductosDS;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class RealizarPedidosActivity extends Fragment {
	public static ArrayList<Producto> prods = new ArrayList<Producto>();
	public ClientesDS datasourceCl;
	public ProductosDS datasourcePr;
	public PedidosDS datasourcePe;
	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
								Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_realizar_pedidos, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	View v = getView();

		final Button btnGuardar = (Button)v.findViewById(R.id.btnPedGuardar);
	    final EditText txtCantidad=(EditText)v.findViewById(R.id.txtPedCantidad);
	    final EditText txtTotal=(EditText)v.findViewById(R.id.txtPedTotal);
	    final EditText txtFechaEntrega=(EditText)v.findViewById(R.id.txtPedFecha);
	    final Spinner spnClientes = (Spinner)v.findViewById(R.id.spnPedClientes);
	    final Spinner spnProductos=(Spinner)v.findViewById(R.id.spnPedProductos);
	    

	    txtFechaEntrega.setHint("dd/mm/yyyy");
		datasourceCl = new ClientesDS(getActivity());
		datasourcePr = new ProductosDS(getActivity());
		datasourcePe = new PedidosDS(getActivity());
		try {
			datasourceCl.open();
			datasourcePr.open();
			datasourcePe.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SharedPreferences prefs = getActivity().getSharedPreferences("staticVars", Context.MODE_PRIVATE);
		final int idVendedor = prefs.getInt("idVendedor", 0);
		final ArrayList<Cliente> c = datasourceCl.traerMisClientes(idVendedor);
		final ArrayAdapter<Cliente> clientes = new ArrayAdapter<Cliente>(getActivity(),R.layout.spinner_item , c);
	    clientes.setDropDownViewResource(R.layout.spinner_item);
	    spnClientes.setAdapter(clientes);
	    clientes.notifyDataSetChanged();
	    prods = datasourcePr.traerTodosLosProductos();
		ArrayAdapter<Producto> productos = new ArrayAdapter<Producto>(getActivity(),R.layout.spinner_item , prods);
		productos.setDropDownViewResource(R.layout.spinner_item);
		spnProductos.setAdapter(productos);
		productos.notifyDataSetChanged();
		
		spnProductos.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				
				int precio = prods.get(position).precio;
				if(!txtCantidad.getText().toString().equals("")){
					txtTotal.setText(precio*Integer.parseInt(txtCantidad.getText().toString()));
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

				
			}

			
		});
	
		txtCantidad.setOnFocusChangeListener(new OnFocusChangeListener(){


			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if(!hasFocus){
				int precio=prods.get(spnProductos.getSelectedItemPosition()).precio;
				    if(!txtCantidad.getText().toString().equals("")){
					    txtTotal.setText(String.valueOf(precio*Integer.parseInt(txtCantidad.getText().toString())));
				    }
			    }
			}
			
		});

		txtFechaEntrega.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if (hasFocus){

				}
			}
			
		});

		btnGuardar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				final Date date = new Date();
				Pedido pedido = new Pedido();

				pedido.cliente = c.get(spnClientes.getSelectedItemPosition()).nombre_cliente +"("+ c.get(spnClientes.getSelectedItemPosition()).nombre_negocio+")";
				pedido.producto = prods.get(spnProductos.getSelectedItemPosition()).nombreProducto;
				pedido.cantidad = Integer.parseInt(txtCantidad.getText().toString());
				pedido.total = Integer.parseInt(txtTotal.getText().toString());
				pedido.fechaPedido = date;
				try {
					pedido.fechaEntrega = dateFormat.parse(txtFechaEntrega.getText().toString());
					Pedido p = datasourcePe.insertarPedido(pedido.cliente, pedido.producto, pedido.cantidad, pedido.fechaPedido, pedido.fechaEntrega, pedido.total, idVendedor);
					if(p != null){
					Toast.makeText(getActivity(), "Pedido agregado con éxito", Toast.LENGTH_SHORT).show();}

				} catch (ParseException e) {

					Toast.makeText(getActivity(), "Escriba bien la fecha de entrega", Toast.LENGTH_SHORT).show();
					//e.printStackTrace();
				}
				
				
			}
			
		});
	
	}
}
