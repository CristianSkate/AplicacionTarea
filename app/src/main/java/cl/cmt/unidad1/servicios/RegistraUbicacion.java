package cl.cmt.unidad1.servicios;

import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.Formatter;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import cl.cmt.unidad1.activity.LoginActivity;
import cl.cmt.unidad1.activity.VerUbicacionesRegistradasActivity;
import cl.cmt.unidad1.clases.Ubicacion;

public class RegistraUbicacion extends Service implements LocationListener {
    private static final long MIN_DISTANCE = 5; // distancia mínima
    private static final long MIN_TIME = 180 * 1000; // 3 minutos
    private LocationManager mLocationManager; // objeto location
    private String mProvider; // variable para proveedor
    public String TAG="IP";
    private Criteria criteria;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        criteria = new Criteria();
        criteria.setCostAllowed(false);
        criteria.setAltitudeRequired(false);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mProvider = mLocationManager.getBestProvider(criteria, true);
        Location loc = mLocationManager.getLastKnownLocation(mProvider);
        mLocationManager.requestLocationUpdates(mProvider, MIN_TIME, MIN_DISTANCE, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(Location location) {

        Ubicacion ub = new Ubicacion();

        ub.latitud = String.valueOf(location.getLatitude());
        ub.longitud = String.valueOf(location.getLongitude());
        ub.ip = getLocalIpAddress();
        ub.direccion = obtenerDireccion(ub.latitud,ub.longitud);

        LoginActivity.ubicacionesRegistradas.add(ub);
        if (VerUbicacionesRegistradasActivity.adapter != null) {
            VerUbicacionesRegistradasActivity.adapter.notifyDataSetChanged();
        }

    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            //
        }
        return null;
    }

    public String obtenerDireccion(String lat, String longitud){

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());

        List<Address> matches = null;
        try {
            matches = geoCoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(longitud), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address bestMatch = (matches.isEmpty() ? null : matches.get(0));
        String addressText = String.format("%s, %s, %s",
                bestMatch.getMaxAddressLineIndex() > 0 ? bestMatch.getAddressLine(0) : "",
                bestMatch.getLocality(),
                bestMatch.getCountryName());
        return addressText;
    }

    public String traerLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(TAG, ex.toString());
        }
        return "";
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
