package cl.cmt.unidad1.activity;

import java.sql.SQLException;
import java.util.ArrayList;

import cl.cmt.unidad1.adapters.ClienteListAdapter;
import cl.cmt.unidad1.clases.Cliente;
import cl.cmt.unidad1.dao.ClientesDS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ClientesActivity extends Fragment {
	//Creo un adaptador de tipo Cliente
	public static ArrayAdapter<Cliente> adapter;
	public ClientesDS datasource;
	private ListView lv_clientes;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_clientes, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
			//Obtengo las variables que vienen del otro activity
		View view = getView();
		SharedPreferences prefs = obtenerPreferencias();
		datasource = new ClientesDS(getActivity());
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int idVendedor = prefs.getInt("idVendedor", 0);
		ArrayList<Cliente> clientes = datasource.traerMisClientes(idVendedor);
		//Obtengo el control de mi listview en la variable lv_clientes
		lv_clientes = (ListView)view.findViewById(R.id.lv_clientes);
		//Le asigno al adaptador mi lista personalizada, enviandole el contexto y la lista de clientes
		adapter = new ClienteListAdapter(getActivity(), clientes);//<Usuario>(getApplicationContext(), R.layout.lista_formato , usuarios);
		//Se asigna el adaptador al listview para que se muestre en pantalla
		lv_clientes.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		lv_clientes.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				
			}
			
		});
	}
	public SharedPreferences obtenerPreferencias(){
		SharedPreferences prefs = getActivity().getSharedPreferences("staticVars", Context.MODE_PRIVATE);
		return prefs;
	}


}
