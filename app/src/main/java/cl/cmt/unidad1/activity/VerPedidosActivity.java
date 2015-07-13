package cl.cmt.unidad1.activity;

import cl.cmt.unidad1.adapters.PedidosAdapter;
import cl.cmt.unidad1.clases.Pedido;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class VerPedidosActivity extends Fragment {
	public static ArrayAdapter<Pedido> adapter;


	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_ver_pedidos, container, false);
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	View v = getView();

		ListView lvPedidos = (ListView)v.findViewById(R.id.lvPedidos);
		adapter = new PedidosAdapter(getActivity(), LoginActivity.pedidos);
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
