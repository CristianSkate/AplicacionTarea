package cl.cmt.unidad1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.cmt.unidad1.bd.BaseDatos;
import cl.cmt.unidad1.clases.Pedido;
import cl.cmt.unidad1.clases.Producto;

/**
 * Created by CMartinez on 17-07-2015.
 */
public class ProductosDS {

    private SQLiteDatabase database;
    private BaseDatos dbHelper;
    private String[] columnas = {"id","nombre", "precio"};
    private String tabla = "productos";

    public ProductosDS(Context context){

        dbHelper = new BaseDatos(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public Producto insertarProducto(String nombre, String precio){
        ContentValues values = new ContentValues();
        values.put(columnas[1], nombre);
        values.put(columnas[2], precio);
        long insertId = database.insert(tabla,null,values);
        Cursor cursor = database.query(tabla,columnas,columnas[0]+"="+insertId, null,null,null,null);
        cursor.moveToFirst();
        Producto p = cursorToProducto(cursor);
        cursor.close();
        return p;
    }

    public Producto buscarProductoPorId(String id){
        Cursor cursor = database.query(tabla,columnas, columnas[0]+"="+id,null,null,null,null);
        cursor.moveToFirst();
        Producto p = cursorToProducto(cursor);
        cursor.close();
        return p;
    }

    public void eliminarProducto(Producto producto){
        long id = producto.idProducto;
        System.out.println("Producto eliminado con id: "+id);
        database.delete(tabla,columnas[0]+"="+id,null);
    }

    public ArrayList<Producto> traerTodosLosProductos(){
        ArrayList<Producto> productos= new ArrayList<Producto>();
        Cursor cursor = database.query(tabla,columnas,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Producto p = cursorToProducto(cursor);
            productos.add(p);
            cursor.moveToNext();
        }
        cursor.close();
        return productos;
    }


    public Producto cursorToProducto(Cursor cursor){
        Producto p = new Producto();
        p.idProducto=cursor.getInt(0);
        p.nombreProducto=cursor.getString(1);
        p.precio=cursor.getInt(2);
        return p;
    }

}
