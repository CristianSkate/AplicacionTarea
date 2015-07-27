package cl.cmt.unidad1.activity;

import cl.cmt.unidad1.clases.Cliente;
import cl.cmt.unidad1.dao.ClientesDS;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

public class MantenedorClientesActivity extends Fragment {

	public ClientesDS datasource;
	public ArrayList<Cliente> clientes;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_mantenedor_clientes, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View v = getView();
		
		final EditText txtIdCliente = (EditText)v.findViewById(R.id.txtIdManCliente);
		final EditText txtNomCliente = (EditText)v.findViewById(R.id.txtNomManCliente);
		final EditText txtNomNegCliente = (EditText)v.findViewById(R.id.txtNomNegManCliente);
		final EditText txtDirCliente = (EditText)v.findViewById(R.id.txtDirManCliente);
		final EditText txtFonoCliente = (EditText)v.findViewById(R.id.txtTelManCliente);
		final Button btnGuardar = (Button)v.findViewById(R.id.btnGuardarCliente);
		final Button btnEliminar = (Button)v.findViewById(R.id.btnEliminarCliente);
		final Button btnLimpiar = (Button)v.findViewById(R.id.btnLimpiarCliente);
		datasource = new ClientesDS(getActivity());
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SharedPreferences prefs = getActivity().getSharedPreferences("staticVars", Context.MODE_PRIVATE);
		final int idVendedor = prefs.getInt("idVendedor", 0);
		clientes = datasource.traerMisClientes(idVendedor);

		txtIdCliente.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				
				Cliente cliente = new Cliente(getActivity().getApplicationContext());
				int i = 0;
				int size = clientes.size();
				boolean ve = true;
				while(ve && i<size){
					if(String.valueOf(clientes.get(i).id_cliente).equals(txtIdCliente.getText().toString())){
						cliente.nombre_cliente=clientes.get(i).nombre_cliente;
						cliente.nombre_negocio=clientes.get(i).nombre_negocio;
						cliente.direccion_cliente=clientes.get(i).direccion_cliente;
						cliente.telefono_cliente=clientes.get(i).telefono_cliente;
						ve=false;
					}
					else{
						i++;
					}
					
				}
				txtNomCliente.setText(cliente.nombre_cliente);
				txtNomNegCliente.setText(cliente.nombre_negocio);
				txtDirCliente.setText(cliente.direccion_cliente);
				txtFonoCliente.setText(cliente.telefono_cliente);
			}
			
		});

		btnGuardar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				
				int i = 0;
				int size = clientes.size();
				boolean ve = true;
				while(ve && i<size){
					if(String.valueOf(clientes.get(i).id_cliente).equals(txtIdCliente.getText().toString())){
						clientes.get(i).nombre_cliente=txtNomCliente.getText().toString();
						clientes.get(i).nombre_negocio=txtNomNegCliente.getText().toString();
						clientes.get(i).direccion_cliente=txtDirCliente.getText().toString();
						clientes.get(i).telefono_cliente=txtFonoCliente.getText().toString();
						datasource.actualizarCliente(Integer.parseInt(txtIdCliente.getText().toString()),
								txtNomCliente.getText().toString(),txtNomNegCliente.getText().toString(),
								txtDirCliente.getText().toString(),txtFonoCliente.getText().toString(),idVendedor);
						//ClientesActivity.adapter.notifyDataSetChanged();
						ve = false;
					}else
					{
						i++; 
					}
					
				}
				
				if(!ve){
					Toast.makeText(getActivity(),R.string.txtClienteActualizado, Toast.LENGTH_SHORT).show();
				}else{
					Cliente nuevo = new Cliente(getActivity().getApplicationContext());
					nuevo.nombre_cliente = txtNomCliente.getText().toString();
					nuevo.nombre_negocio = txtNomNegCliente.getText().toString();
					nuevo.direccion_cliente = txtDirCliente.getText().toString();
					nuevo.telefono_cliente = txtFonoCliente.getText().toString();

					Cliente c = datasource.insertarCliente(txtNomCliente.getText().toString(),txtNomNegCliente.getText().toString(),
							txtDirCliente.getText().toString(),txtFonoCliente.getText().toString(),idVendedor);
					nuevo.id_cliente=c.id_cliente;
					clientes.add(nuevo);
					//ClientesActivity.adapter.notifyDataSetChanged();

					if(c != null) {
						Toast.makeText(getActivity(), R.string.txtClienteInsertado+c.id_cliente, Toast.LENGTH_SHORT).show();
					}
				}
				
				
				//Toast.makeText(getApplicationContext(), "Cliente insertado con exito", Toast.LENGTH_SHORT).show();
				
				
			}
			
		});
		
		btnEliminar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				int i = 0;
				int size = clientes.size();
				boolean ve = true;
				while (ve && i < size) {
					if (String.valueOf(clientes.get(i).id_cliente).equals(txtIdCliente.getText().toString())) {
						Cliente a = new Cliente(getActivity().getApplicationContext());
						a.id_cliente = Integer.parseInt(txtIdCliente.getText().toString());
						a.nombre_cliente = txtNomCliente.getText().toString();
						a.nombre_negocio = txtNomNegCliente.getText().toString();
						a.direccion_cliente = txtDirCliente.getText().toString();
						a.telefono_cliente = txtFonoCliente.getText().toString();
						//LoginActivity.eliminados.add(a);
						clientes.remove(i);
						datasource.insertarClienteEliminado(a.nombre_cliente,a.nombre_negocio,a.direccion_cliente,a.telefono_cliente,idVendedor);
						datasource.eliminarCliente(a);
						//ClientesActivity.adapter.notifyDataSetChanged();
						ve = false;
					} else {
						i++;
					}

				}
				if (size != clientes.size()) {
					Toast.makeText(getActivity(), R.string.txtClienteEliminado, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getActivity(), R.string.txtClienteNoExiste, Toast.LENGTH_SHORT).show();
				}
			}

		});

		btnLimpiar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				txtIdCliente.setText("");
				txtNomCliente.setText("");
				txtNomNegCliente.setText("");
				txtDirCliente.setText("");
				txtFonoCliente.setText("");
			}
			
		});
		
	}
}
