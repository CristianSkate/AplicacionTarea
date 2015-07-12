package cl.cmt.unidad1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import cl.cmt.unidad1.activity.R;
import cl.cmt.unidad1.clases.Ubicacion;

public class UbicacionesAdapter extends ArrayAdapter<Ubicacion>{
	private final Context context;
	private final ArrayList<Ubicacion> ubicaciones;

	public UbicacionesAdapter(Context context, ArrayList<Ubicacion> ubicaciones){
		super(context, R.layout.lista_formato, ubicaciones);
		this.context = context;
		this.ubicaciones= ubicaciones;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.lista_formato, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.name);
		textView.setText(ubicaciones.get(position).toString());
		return rowView;
	}
}
