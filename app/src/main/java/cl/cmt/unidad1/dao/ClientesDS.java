package cl.cmt.unidad1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

import cl.cmt.unidad1.bd.BaseDatos;
import cl.cmt.unidad1.clases.Cliente;
import cl.cmt.unidad1.clases.Usuario;

/**
 * Created by CMartinez on 17-07-2015.
 */
public class ClientesDS {

    private SQLiteDatabase database;
    private BaseDatos dbHelper;
    private String[] columnas = {"id","nombre", "nombreNegocio", "direccion","telefono","idVendedor"};
    private String tabla = "clientes";

    public ClientesDS(Context context){

        dbHelper = new BaseDatos(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public Cliente insertarCliente(String nombre, String nombreNegocio, String direccion, String telefono, int idVendedor){
        ContentValues values = new ContentValues();
        values.put(columnas[1], nombre);
        values.put(columnas[2], nombreNegocio);
        values.put(columnas[3], direccion);
        values.put(columnas[4], telefono);
        values.put(columnas[5], idVendedor);
        long insertId = database.insert(tabla,null,values);
        Cursor cursor = database.query(tabla,columnas,columnas[0]+"="+insertId, null,null,null,null);
        cursor.moveToFirst();
        Cliente c = cursorToCliente(cursor);
        cursor.close();
        return c;
    }

    public Cliente insertarClienteEliminado(String nombre, String nombreNegocio, String direccion, String telefono, int idVendedor){
        ContentValues values = new ContentValues();
        values.put(columnas[1], nombre);
        values.put(columnas[2], nombreNegocio);
        values.put(columnas[3], direccion);
        values.put(columnas[4], telefono);
        values.put(columnas[5], idVendedor);
        long insertId = database.insert("clientes_eliminados",null,values);
        Cursor cursor = database.query("clientes_eliminados",columnas,columnas[0]+"="+insertId, null,null,null,null);
        cursor.moveToFirst();
        Cliente c = cursorToCliente(cursor);
        cursor.close();
        return c;
    }

    public Cliente actualizarCliente(int idCliente, String nombre, String nombreNegocio, String direccion, String telefono, int idVendedor){
        ContentValues values = new ContentValues();
        values.put(columnas[1], nombre);
        values.put(columnas[2], nombreNegocio);
        values.put(columnas[3], direccion);
        values.put(columnas[4], telefono);
        values.put(columnas[5], idVendedor);
        database.update(tabla, values, columnas[0] + "=" + idCliente, null);
        Cursor cursor = database.query(tabla,columnas,columnas[0]+"="+idCliente,null,null,null,null);
        cursor.moveToFirst();
        Cliente c = cursorToCliente(cursor);
        cursor.close();
        return c;
    }

    public Cliente buscarClientePorId(String id){
        Cursor cursor = database.query(tabla,columnas, columnas[0]+"="+id,null,null,null,null);
        cursor.moveToFirst();
        Cliente c = cursorToCliente(cursor);
        cursor.close();
        return c;
    }

    public void eliminarCliente(Cliente cliente){
        long id = cliente.id_cliente;
        System.out.println("Cliente eliminado con id: " + id);
        database.delete(tabla, columnas[0] + "=" + id, null);
    }

    public ArrayList<Cliente> traerTodosLosClientes(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cursor cursor = database.query(tabla,columnas,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Cliente c = cursorToCliente(cursor);
            clientes.add(c);
            cursor.moveToNext();
        }
        cursor.close();
        return clientes;
    }
    public ArrayList<Cliente> traerMisClientesEliminados(int idVendedor){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cursor cursor = database.query("clientes_eliminados",columnas,columnas[5]+"="+idVendedor,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Cliente c = cursorToCliente(cursor);
            clientes.add(c);
            cursor.moveToNext();
        }
        cursor.close();
        return clientes;
    }

    public ArrayList<Cliente> traerMisClientes(int idVendedor){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cursor cursor = database.query(tabla,columnas,columnas[5]+"="+idVendedor,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Cliente c = cursorToCliente(cursor);
            clientes.add(c);
            cursor.moveToNext();
        }
        cursor.close();
        return clientes;
    }


    public Cliente cursorToCliente(Cursor cursor){
        Cliente c = new Cliente();
        c.id_cliente=cursor.getInt(0);
        c.nombre_cliente=cursor.getString(1);
        c.nombre_negocio=cursor.getString(2);
        c.direccion_cliente=cursor.getString(3);
        c.telefono_cliente=cursor.getString(4);
        c.id_Vendedor=cursor.getInt(5);
        return c;
    }

}
