package cl.cmt.unidad1.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

import cl.cmt.unidad1.adapters.UbicacionesAdapter;
import cl.cmt.unidad1.clases.Ubicacion;
import cl.cmt.unidad1.dao.UbicacionesDS;

public class VerUbicacionesRegistradasActivity extends Fragment {
	public static ArrayAdapter<Ubicacion> adapter;
	public UbicacionesDS datasource;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_ver_ubicaciones_registradas, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		datasource = new UbicacionesDS(getActivity());
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		View v = getView();
		SharedPreferences prefs = getActivity().getSharedPreferences("staticVars", Context.MODE_PRIVATE);
		int idVendedor = prefs.getInt("idVendedor",0);
		ArrayList<Ubicacion> ubicacionesRegistradas = datasource.traerMisUbicaciones(idVendedor);
		ListView lvUbicacionesRegistradas = (ListView)v.findViewById(R.id.lvUbicacionesRegistradas);
		adapter = new UbicacionesAdapter(getActivity(), ubicacionesRegistradas);
		lvUbicacionesRegistradas.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
