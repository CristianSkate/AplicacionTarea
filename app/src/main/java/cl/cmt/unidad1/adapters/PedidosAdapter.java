package cl.cmt.unidad1.adapters;

import java.util.ArrayList;

import cl.cmt.unidad1.activity.R;
import cl.cmt.unidad1.clases.Pedido;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PedidosAdapter extends ArrayAdapter<Pedido>{
	private final Context context;
	private final ArrayList<Pedido> pedidos;
	
	public PedidosAdapter(Context context, ArrayList<Pedido> pedidos){
		super(context, R.layout.lista_formato, pedidos);
		this.context = context;
		this.pedidos = pedidos;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.lista_formato, parent, false);
		
		//Obtenemos el textview del formato de la fila y le asignamos el texto
		TextView textView = (TextView) rowView.findViewById(R.id.name);
		textView.setText(pedidos.get(position).toString());
		return rowView;
	}

}
