package cl.cmt.unidad1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cl.cmt.unidad1.bd.BaseDatos;
import cl.cmt.unidad1.clases.Cliente;
import cl.cmt.unidad1.clases.Entrega;

/**
 * Created by CMartinez on 17-07-2015.
 */
public class EntregasDS {

    private SQLiteDatabase database;
    private BaseDatos dbHelper;
    private String[] columnas = {"id","cliente", "producto", "cantidad","fechaEntrega","total","idVendedor"};
    private String tabla = "entregas";

    public EntregasDS(Context context){

        dbHelper = new BaseDatos(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public Entrega insertarEntrega(String cliente, String producto, int cantidad, java.util.Date fechaEntrega,int total, int idVendedor){
        ContentValues values = new ContentValues();
        values.put(columnas[1], cliente);
        values.put(columnas[2], producto);
        values.put(columnas[3], cantidad);
        values.put(columnas[4], String.valueOf(fechaEntrega));
        values.put(columnas[5], total);
        values.put(columnas[6], idVendedor);
        long insertId = database.insert(tabla,null,values);
        Cursor cursor = database.query(tabla,columnas,columnas[0]+"="+insertId, null,null,null,null);
        cursor.moveToFirst();
        Entrega e = cursorToEntrega(cursor);
        cursor.close();
        return e;
    }

    public Entrega buscarEntregaPorId(int id){
        Cursor cursor = database.query(tabla,columnas, columnas[0]+"="+id,null,null,null,null);
        cursor.moveToFirst();
        Entrega e = cursorToEntrega(cursor);
        cursor.close();
        return e;
    }

    public void eliminarEntrega(Entrega entrega){
        long id = entrega.idEntrega;
        System.out.println("Entrega eliminada con id: " + id);
        database.delete(tabla,columnas[0]+"="+id,null);
    }

    public ArrayList<Entrega> traerTodasLasEntregas(){
        ArrayList<Entrega> entregas= new ArrayList<Entrega>();
        Cursor cursor = database.query(tabla,columnas,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Entrega e = cursorToEntrega(cursor);
            entregas.add(e);
            cursor.moveToNext();
        }
        cursor.close();
        return entregas;
    }
    public ArrayList<Entrega> traerMisEntregas(int idVendedor){
        ArrayList<Entrega> entregas = new ArrayList<Entrega>();
        Cursor cursor = database.query(tabla,columnas, columnas[6]+"="+idVendedor,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Entrega e = cursorToEntrega(cursor);
            entregas.add(e);
            cursor.moveToNext();
        }
        cursor.close();
        return entregas;
    }


    public Entrega cursorToEntrega(Cursor cursor){
        Entrega e = new Entrega();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        e.idEntrega = cursor.getInt(0);
        e.cliente=cursor.getString(1);
        e.producto=cursor.getString(2);
        e.cantidad=cursor.getInt(3);
        try {
            e.fechaEntrega= dateFormat.parse(cursor.getString(4));
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        e.total=cursor.getInt(5);
        e.idVendedor=cursor.getInt(6);
        return e;
    }

}
