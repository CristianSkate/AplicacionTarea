package cl.cmt.unidad1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

import cl.cmt.unidad1.bd.BaseDatos;
import cl.cmt.unidad1.clases.Producto;
import cl.cmt.unidad1.clases.Ubicacion;

/**
 * Created by CMartinez on 17-07-2015.
 */
public class UbicacionesDS {

    private SQLiteDatabase database;
    private BaseDatos dbHelper;
    private String[] columnas = {"id","idUsuario", "latitud","longitud","ip","direccion"};
    private String tabla = "ubicaciones";

    public UbicacionesDS(Context context){

        dbHelper = new BaseDatos(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public Ubicacion insertarUbicacion(int idUsuario, String latitud, String longitud, String ip, String direccion){
        ContentValues values = new ContentValues();
        values.put(columnas[1], idUsuario);
        values.put(columnas[2], latitud);
        values.put(columnas[3], longitud);
        values.put(columnas[4], ip);
        values.put(columnas[5], direccion);
        long insertId = database.insert(tabla,null,values);
        Cursor cursor = database.query(tabla,columnas,columnas[0]+"="+insertId, null,null,null,null);
        cursor.moveToFirst();
        Ubicacion u = cursorToUbicacion(cursor);
        return u;
    }


    public void eliminarUbicacion(Ubicacion ubicacion){
        long id = ubicacion.idUbicacion;
        System.out.println("Ubicacion eliminada con id: "+id);
        database.delete(tabla,columnas[0]+"="+id,null);
    }

    public ArrayList<Ubicacion> traerTodasLasUbicaciones(){
        ArrayList<Ubicacion> ubicaciones= new ArrayList<Ubicacion>();
        Cursor cursor = database.query(tabla,columnas,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Ubicacion u = cursorToUbicacion(cursor);
            ubicaciones.add(u);
            cursor.moveToNext();
        }
        cursor.close();
        return ubicaciones;
    }


    public Ubicacion cursorToUbicacion(Cursor cursor){
        Ubicacion u = new Ubicacion();
        u.idUbicacion=cursor.getInt(0);
        u.idUsuario=cursor.getInt(1);
        u.latitud=cursor.getString(2);
        u.longitud=cursor.getString(3);
        u.ip=cursor.getString(4);
        u.direccion=cursor.getString(5);
        return u;
    }

}
