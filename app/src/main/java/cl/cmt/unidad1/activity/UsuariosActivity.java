package cl.cmt.unidad1.activity;

import java.util.ArrayList;

import cl.cmt.unidad1.adapters.ListAdapter;
import cl.cmt.unidad1.clases.Usuario;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UsuariosActivity extends Fragment {
	private ArrayAdapter<Usuario> adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_usuarios, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		View v = getView();

		ListView lv_usuarios = (ListView)v.findViewById(R.id.lv_usuarios);
		
		Usuario usuario = new Usuario();
		ArrayList<Usuario> usuarios = usuario.listaUsuarios();
		
		adapter = new ListAdapter(getActivity(), usuarios);//<Usuario>(getApplicationContext(), R.layout.lista_formato , usuarios);
	
		lv_usuarios.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
