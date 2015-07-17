package cl.cmt.unidad1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.cmt.unidad1.bd.BaseDatos;
import cl.cmt.unidad1.clases.Usuario;

/**
 * Created by CMartinez on 17-07-2015.
 */
public class UsuariosDS {

    private SQLiteDatabase database;
    private BaseDatos dbHelper;
    private String[] columnas = {"id","nombreUsuario", "loginUsuario", "contrasena"};
    private String tabla = "usuarios";

    public UsuariosDS(Context context, CursorFactory factory){
        dbHelper = new BaseDatos(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public Usuario insertarUsuario(String nombreUsuario, String loginUsuario, String contrasena){
        ContentValues values = new ContentValues();
        values.put(columnas[1], nombreUsuario);
        values.put(columnas[2], loginUsuario);
        values.put(columnas[3], contrasena);
        long insertId = database.insert(tabla,null,values);
        Cursor cursor = database.query(tabla,columnas,columnas[0]+"="+insertId, null,null,null,null);
        cursor.moveToFirst();
        Usuario u = cursorToUsuario(cursor);
        return u;
    }
    public Usuario loginUsuario(String loginUsuario, String contrasena){
        Cursor cursor = database.query(tabla,columnas,columnas[2]+"="+loginUsuario+" and "+columnas[3]+"="+contrasena,null,null,null,null);
        cursor.moveToFirst();
        Usuario u = cursorToUsuario(cursor);
        return u;
    }

    public void eliminarUsuario(Usuario usuario){
        long id = usuario.id_usuario;
        System.out.println("Usuario eliminado con id: "+id);
        database.delete(tabla,columnas[0]+"="+id,null);
    }

    public List<Usuario> traerTodosLosUsuarios(){
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Cursor cursor = database.query(tabla,columnas,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Usuario u = cursorToUsuario(cursor);
            usuarios.add(u);
            cursor.moveToNext();
        }
        cursor.close();
        return usuarios;
    }


    public Usuario cursorToUsuario(Cursor cursor){
        Usuario u = new Usuario();
        u.id_usuario=cursor.getInt(0);
        u.nombre_usuario=cursor.getString(1);
        u.login_usuario=cursor.getString(2);
        u.contrasena=cursor.getString(3);
        return u;
    }

}
