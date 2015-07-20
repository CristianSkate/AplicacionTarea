package cl.cmt.unidad1.activity;

import cl.cmt.unidad1.adapters.ClienteListAdapter;
import cl.cmt.unidad1.clases.Cliente;
import cl.cmt.unidad1.dao.ClientesDS;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;
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

import java.sql.SQLException;
import java.util.ArrayList;

public class VerClientesEliminadosActivity extends Fragment {
	public static ArrayAdapter<Cliente> adapter;
	public ClientesDS datasource;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_ver_clientes_eliminados, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		datasource = new ClientesDS(getActivity());
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		View v = getView();
		SharedPreferences prefs = getActivity().getSharedPreferences("staticVars", Context.MODE_PRIVATE);
		int idVendedor = prefs.getInt("idVendedor", 0);
		ArrayList<Cliente> clientesEliminados = datasource.traerMisClientesEliminados(idVendedor);
		ListView lvCliEliminados = (ListView)v.findViewById(R.id.lvClieEliminados);
		adapter = new ClienteListAdapter(getActivity(), clientesEliminados);//<Usuario>(getApplicationContext(), R.layout.lista_formato , usuarios);
		lvCliEliminados.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
