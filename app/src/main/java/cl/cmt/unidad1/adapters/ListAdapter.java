package cl.cmt.unidad1.adapters;

import java.util.ArrayList;

import cl.cmt.unidad1.activity.R;
import cl.cmt.unidad1.clases.Usuario;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//Clase adaptador para utilizar estilo de lista perzonalizada
//Se extiende la clase de un ArrayAdapter del tipo de objeto que mostraremos, en este caso usuario

public class ListAdapter extends ArrayAdapter<Usuario> {
	private final Context context;
	private final ArrayList<Usuario> usuarios;
	
	//Creamos el constructor que recibe el contexto y el arraylist que mostraremos en la lista
	//Además sobreescribimos el fomrmato de lista por el que creamos en xml en este caso
	//el formato de mi lista se llama lista_formato.xml y está en la carpeta layout
	
	public ListAdapter(Context context, ArrayList<Usuario> usuarios){
		super(context, R.layout.lista_formato, usuarios);
		this.context = context;
		this.usuarios = usuarios;
	}
	//Sobreescribimos la funcion getView del adaptador común para escribir en nuestras variables
	//y asignar la vista personalizada a la fila
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.lista_formato, parent, false);
		
		//Obtenemos el textview del formato de la fila y le asignamos el texto
		TextView textView = (TextView) rowView.findViewById(R.id.name);
		textView.setText(usuarios.get(position).toString());
		return rowView;
	}

}
