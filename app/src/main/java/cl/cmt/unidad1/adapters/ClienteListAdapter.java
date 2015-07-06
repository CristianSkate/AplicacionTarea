package cl.cmt.unidad1.adapters;

import java.util.ArrayList;
import java.util.List;

import cl.cmt.unidad1.activity.R;
import cl.cmt.unidad1.clases.Cliente;
import cl.cmt.unidad1.clases.Usuario;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClienteListAdapter extends ArrayAdapter<Cliente> {
	private final Context context;
	private final ArrayList<Cliente> clientes;
	
	public ClienteListAdapter(Context context, ArrayList<Cliente> clientes){
		super(context, R.layout.lista_formato, clientes);
		this.context = context;
		this.clientes = clientes;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.lista_formato, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.name);
		textView.setText(clientes.get(position).toString());
		return rowView;
	}

}
