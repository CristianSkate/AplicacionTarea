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
import cl.cmt.unidad1.clases.Pedido;

/**
 * Created by CMartinez on 17-07-2015.
 */
public class PedidosDS {

    private SQLiteDatabase database;
    private BaseDatos dbHelper;
    private String[] columnas = {"id","cliente", "producto","cantidad","fechaPedido","fechaEntrega","total","idVendedor"};
    private String tabla = "pedidos";

    public PedidosDS(Context context){

        dbHelper = new BaseDatos(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public Pedido insertarCliente(String cliente, String producto, String cantidad, String fechaPedido, String fechaEntrega, String total, int idVendedor){
        ContentValues values = new ContentValues();
        values.put(columnas[1], cliente);
        values.put(columnas[2], producto);
        values.put(columnas[3], cantidad);
        values.put(columnas[4], fechaPedido);
        values.put(columnas[5], fechaEntrega);
        values.put(columnas[6], total);
        values.put(columnas[7], idVendedor);
        long insertId = database.insert(tabla,null,values);
        Cursor cursor = database.query(tabla,columnas,columnas[0]+"="+insertId, null,null,null,null);
        cursor.moveToFirst();
        Pedido p = cursorToPedido(cursor);
        return p;
    }

    public Pedido buscarPedidoPorId(String id){
        Cursor cursor = database.query(tabla,columnas, columnas[0]+"="+id,null,null,null,null);
        cursor.moveToFirst();
        Pedido p = cursorToPedido(cursor);
        return p;
    }

    public void eliminarPedido(Pedido pedido){
        long id = pedido.idPedido;
        System.out.println("Pedido eliminado con id: "+id);
        database.delete(tabla,columnas[0]+"="+id,null);
    }

    public ArrayList<Pedido> traerTodosLosPedidos(){
        ArrayList<Pedido> pedidos= new ArrayList<Pedido>();
        Cursor cursor = database.query(tabla,columnas,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Pedido p = cursorToPedido(cursor);
            pedidos.add(p);
            cursor.moveToNext();
        }
        cursor.close();
        return pedidos;
    }


    public Pedido cursorToPedido(Cursor cursor){
        Pedido p = new Pedido();
        p.idPedido=cursor.getInt(0);
        p.cliente=cursor.getString(1);
        p.producto=cursor.getString(2);
        p.cantidad=cursor.getInt(3);
        p.fechaPedido= Date.valueOf(cursor.getString(4));
        p.fechaEntrega=Date.valueOf(cursor.getString(5));
        p.total=cursor.getInt(6);
        p.idVendedor=cursor.getInt(7);
        return p;
    }

}
