package cl.cmt.unidad1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.SQLException;
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
    private String[] columnas = {"id","cliente", "producto", "cantidad","fechaEntrega","idVendedor"};
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
    public Entrega insertarEntrega(String cliente, String producto, String cantidad, String fechaEntrega, int idVendedor){
        ContentValues values = new ContentValues();
        values.put(columnas[1], cliente);
        values.put(columnas[2], producto);
        values.put(columnas[3], cantidad);
        values.put(columnas[4], fechaEntrega);
        values.put(columnas[5], idVendedor);
        long insertId = database.insert(tabla,null,values);
        Cursor cursor = database.query(tabla,columnas,columnas[0]+"="+insertId, null,null,null,null);
        cursor.moveToFirst();
        Entrega e = cursorToEntrega(cursor);
        return e;
    }

    public Entrega buscarEntregaPorId(String id){
        Cursor cursor = database.query(tabla,columnas, columnas[0]+"="+id,null,null,null,null);
        cursor.moveToFirst();
        Entrega e = cursorToEntrega(cursor);
        return e;
    }

    public void eliminarEntrega(Entrega entrega){
        long id = entrega.idEntrega;
        System.out.println("Entrega eliminada con id: "+id);
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


    public Entrega cursorToEntrega(Cursor cursor){
        Entrega e = new Entrega();
        e.idEntrega = cursor.getInt(0);
        e.cliente=cursor.getString(1);
        e.producto=cursor.getString(2);
        e.cantidad=cursor.getInt(3);
        e.fechaEntrega= Date.valueOf(cursor.getString(4));
        e.idVendedor=cursor.getInt(5);
        return e;
    }

}
