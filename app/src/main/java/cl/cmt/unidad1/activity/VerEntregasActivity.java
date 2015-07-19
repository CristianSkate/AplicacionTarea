package cl.cmt.unidad1.activity;

import cl.cmt.unidad1.adapters.EntregasAdapter;
import cl.cmt.unidad1.clases.Entrega;
import cl.cmt.unidad1.dao.EntregasDS;

import android.app.Activity;
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

public class VerEntregasActivity extends Fragment {
	public static ArrayAdapter<Entrega> adapter;
	public EntregasDS datasource;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_ver_entregas,container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View v =getView();

		ListView lvEntregas = (ListView)v.findViewById(R.id.lvEntregas);
		datasource = new EntregasDS(getActivity());
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SharedPreferences prefs = getActivity().getSharedPreferences("staticVars", Context.MODE_PRIVATE);
		int idVendedor = prefs.getInt("idVendedor", 0);
		ArrayList<Entrega> entregas = datasource.traerMisEntregas(idVendedor);
		adapter = new EntregasAdapter(getActivity(), entregas);//<Usuario>(getApplicationContext(), R.layout.lista_formato , usuarios);
		lvEntregas.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
