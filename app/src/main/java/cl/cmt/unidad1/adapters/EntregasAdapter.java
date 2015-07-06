package cl.cmt.unidad1.adapters;

import java.util.ArrayList;

import cl.cmt.unidad1.activity.R;
import cl.cmt.unidad1.clases.Entrega;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EntregasAdapter extends ArrayAdapter<Entrega>{
	private final Context context;
	private final ArrayList<Entrega> entregas;
	
	public EntregasAdapter(Context context, ArrayList<Entrega> entregas){
		super(context, R.layout.lista_formato, entregas);
		this.context = context;
		this.entregas = entregas;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.lista_formato, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.name);
		textView.setText(entregas.get(position).toString());
		return rowView;
	}
}
