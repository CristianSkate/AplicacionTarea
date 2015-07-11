package cl.cmt.unidad1.activity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MapFragment extends Fragment implements OnMapReadyCallback{

    private static View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (view != null) {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null)
                    parent.removeView(view);
            }
                view = inflater.inflate(R.layout.fragment_mapa, container, false);
            return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //View v = getView();
        //SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
        //.findFragmentById(R.id.mapa);
        //mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng sydney = new LatLng(-33.867, 151.206);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));

    }
}
