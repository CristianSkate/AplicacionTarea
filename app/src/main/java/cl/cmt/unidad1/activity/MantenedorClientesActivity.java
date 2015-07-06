package cl.cmt.unidad1.activity;

import cl.cmt.unidad1.clases.Cliente;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MantenedorClientesActivity extends Fragment {


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
		
		txtIdCliente.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				
				Cliente cliente = new Cliente();
				int i = 0;
				int size = LoginActivity.clientes.size();
				boolean ve = true;
				while(ve && i<size){
					if(String.valueOf(LoginActivity.clientes.get(i).id_cliente).equals(txtIdCliente.getText().toString())){
						cliente.nombre_cliente=LoginActivity.clientes.get(i).nombre_cliente;
						cliente.nombre_negocio=LoginActivity.clientes.get(i).nombre_negocio;
						cliente.direccion_cliente=LoginActivity.clientes.get(i).direccion_cliente;
						cliente.telefono_cliente=LoginActivity.clientes.get(i).telefono_cliente;
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
				// TODO Auto-generated method stub
				
				int i = 0;
				int size = LoginActivity.clientes.size();
				boolean ve = true;
				while(ve && i<size){
					if(String.valueOf(LoginActivity.clientes.get(i).id_cliente).equals(txtIdCliente.getText().toString())){
						LoginActivity.clientes.get(i).nombre_cliente=txtNomCliente.getText().toString();
						LoginActivity.clientes.get(i).nombre_negocio=txtNomNegCliente.getText().toString();
						LoginActivity.clientes.get(i).direccion_cliente=txtDirCliente.getText().toString();
						LoginActivity.clientes.get(i).telefono_cliente=txtFonoCliente.getText().toString();
						//ClientesActivity.adapter.notifyDataSetChanged();
						ve = false;
					}else
					{
						i++; 
					}
					
				}
				
				if(!ve){
					Toast.makeText(getActivity(), "Cliente actualizado con éxito", Toast.LENGTH_SHORT).show();
				}else{
					Cliente nuevo = new Cliente();
					nuevo.id_cliente = Integer.parseInt(txtIdCliente.getText().toString());
					nuevo.nombre_cliente = txtNomCliente.getText().toString();
					nuevo.nombre_negocio = txtNomNegCliente.getText().toString();
					nuevo.direccion_cliente = txtDirCliente.getText().toString();
					nuevo.telefono_cliente = txtFonoCliente.getText().toString();
					LoginActivity.clientes.add(nuevo);
					//ClientesActivity.adapter.notifyDataSetChanged();
					Toast.makeText(getActivity(), "Cliente insertado con éxito", Toast.LENGTH_SHORT).show();
				}
				
				
				//Toast.makeText(getApplicationContext(), "Cliente insertado con exito", Toast.LENGTH_SHORT).show();
				
				
			}
			
		});
		
		btnEliminar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int i = 0;
				int size = LoginActivity.clientes.size();
				boolean ve = true;
				while(ve && i<size){
					if(String.valueOf(LoginActivity.clientes.get(i).id_cliente).equals(txtIdCliente.getText().toString())){
						Cliente a = new Cliente();
						a.id_cliente = Integer.parseInt(txtIdCliente.getText().toString());
						a.nombre_cliente = txtNomCliente.getText().toString();
						a.nombre_negocio = txtNomNegCliente.getText().toString();
						a.direccion_cliente = txtDirCliente.getText().toString();
						a.telefono_cliente = txtFonoCliente.getText().toString();
						LoginActivity.eliminados.add(a);						
						LoginActivity.clientes.remove(i);
						//ClientesActivity.adapter.notifyDataSetChanged();
						ve = false;
					}else
					{
						i++; 
					}
					
				}
				if(size !=LoginActivity.clientes.size()){
					Toast.makeText(getActivity(), "Se eliminó el cliente con exito", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getActivity(), "El cliente seleccionado no existe", Toast.LENGTH_SHORT).show();
				}
			}
			
		});

		btnLimpiar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtIdCliente.setText("");
				txtNomCliente.setText("");
				txtNomNegCliente.setText("");
				txtDirCliente.setText("");
				txtFonoCliente.setText("");
			}
			
		});
		
	}
}
