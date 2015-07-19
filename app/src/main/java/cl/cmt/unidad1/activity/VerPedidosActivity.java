package cl.cmt.unidad1.activity;

import cl.cmt.unidad1.adapters.PedidosAdapter;
import cl.cmt.unidad1.clases.Pedido;
import cl.cmt.unidad1.dao.PedidosDS;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

public class VerPedidosActivity extends Fragment {
	public static ArrayAdapter<Pedido> adapter;
	public PedidosDS datasource;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_ver_pedidos, container, false);
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View v = getView();
		datasource = new PedidosDS(getActivity());
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ListView lvPedidos = (ListView)v.findViewById(R.id.lvPedidos);
		SharedPreferences prefs = getActivity().getSharedPreferences("staticVars", Context.MODE_PRIVATE);
		int idVendedor = prefs.getInt("idVendedor", 0);
		ArrayList<Pedido> pedidos = datasource.traerMisPedidos(idVendedor);
		adapter = new PedidosAdapter(getActivity(), pedidos);
		lvPedidos.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		lvPedidos.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				confirmarEntrega(position);
			}
			
		});
		
		
	}
	
	private void confirmarEntrega(final int index){
		
		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getActivity());
        dialogo1.setTitle("Confirmacion");  
        dialogo1.setMessage("¿Desea realizar la entrega ahora?");
        dialogo1.setPositiveButton("Si", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogo1, int id) {  
            	
            	Intent intent = new Intent(getActivity(), EntregasActivity.class);
				intent.putExtra("index", index);
				startActivity(intent);
            	
            }  
        });  
        dialogo1.setNegativeButton("No", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialogo1, int id) {  
                return;
            }  
        });            
        dialogo1.show();
		
	}
}
