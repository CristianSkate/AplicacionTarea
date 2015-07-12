package cl.cmt.unidad1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cl.cmt.unidad1.adapters.UbicacionesAdapter;
import cl.cmt.unidad1.clases.Ubicacion;

public class VerUbicacionesRegistradasActivity extends Fragment {
	public static ArrayAdapter<Ubicacion> adapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_ver_ubicaciones_registradas, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		View v = getView();

		ListView lvUbicacionesRegistradas = (ListView)v.findViewById(R.id.lvUbicacionesRegistradas);
		adapter = new UbicacionesAdapter(getActivity(), LoginActivity.ubicacionesRegistradas);
		lvUbicacionesRegistradas.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
