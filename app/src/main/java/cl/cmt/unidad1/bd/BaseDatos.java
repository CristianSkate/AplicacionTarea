package cl.cmt.unidad1.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


/**
 * Created by CMartinez on 17-07-2015.
 */
public class BaseDatos extends SQLiteOpenHelper{
    public static final String DB_PATH="./";
    public static final String DB_NAME="bd_app.db";
    public static final int VERSION_DB=1;
    private String CREATE_TABLE_USUARIOS = "create table usuarios(id integer primary key autoincrement," +
            "nombreUsuario text, loginUsuario text, contrasena text)";
    private String CREATE_TABLE_CLIENTES = "create table clientes(id integer primary key autoincrement," +
            "nombre text, nombreNegocio text, direccion text, telefono text, idVendedor integer, FOREIGN KEY (idVendedor) references usuarios(id))";
    private String CREATE_TABLE_ENTREGAS = "create table entregas(id integer primary key autoincrement," +
            "cliente text, producto text, cantidad text, fechaEntrega date, total integer, idVendedor integer, FOREIGN KEY (idVendedor) references usuarios(id))";
    private String CREATE_TABLE_PEDIDOS = "create table pedidos(id integer primary key autoincrement," +
            "cliente text, producto text, cantidad text, fechaPedido date, fechaEntrega date, total integer, idVendedor integer, FOREIGN KEY (idVendedor) references usuarios(id))";
    private String CREATE_TABLE_PRODUCTOS = "create table productos(id integer primary key autoincrement," +
            "nombre text, precio text)";
    private String CREATE_TABLE_UBICACIONES = "create table ubicaciones(id integer primary key autoincrement," +
            "idUsuario text, latitud text, longitud text, ip text, direccion text, FOREIGN KEY (idUsuario) REFERENCES usuarios(id))";
    private String INSERTAR_USUARIOS= "insert into usuarios(id, nombreUsuario, loginUsuario, contrasena) values (1,'Cristian Martínez','cmartinez','abc123'),(2,'Juan Perez','juan','juan')";
    private String INSERTAR_PRODUCTOS="insert into productos(nombre, precio) values ('ramitas','200'), ('cheetos','350'), ('papas fritas','500'), ('mani salado','150'), ('doritos','400'), ('snack mix','500')";
    private String INSERTAR_CLIENTES="insert into clientes(idVendedor, nombre, nombreNegocio, direccion, telefono) values " +
            "(1,'Pedro Sandoval','Minimarket La avenida','avenida el palomar #1287, Copiapó','8767876')," +
            "(1,'Carlos Martinez','Bazar A la vuelta','angel pimentel #0738, Puente alto','9728398')," +
            "(1,'Miranda Zuñiga','Carillon','pedro aguirre cerda #0285, Puente alto','228378791')," +
            "(1,'Rosa Astorga','Donde la tia Rosa','concha y toro #4890, Puente alto','227873982')," +
            "(1,'Carlos Moraga','Bazar de carlitos','el cipres #2021, La florida','9728398')," +
            "(1,'Marta Alvarez','Bazar de Martita','la colmena #3452, La pintana','8978765')," +
            "(2,'Lita de Achondo','Panadería Cositas Ricas','nemesio vikuña #8000, Puente alto','8390498')," +
            "(2,'David Peñasco','Botillería San carlos','avda san carlos #1520, Puente alto','73847623')," +
            "(2,'Marisol Vargas','Restobar Mar & Sol','los guindos #20000, La laja','2378736')," +
            "(2,'Carmen Flores','El kioskito','jose luis coo #3025, Puente alto','283928364')," +
            "(2,'Elsa Godoy','El Ratatui','colombia #2555, La florida','938293874')," +
            "(2,'Carmela Carvajal','La tia Carmela','santa rosa #3600, La pintana','8978765'),";
    private String UPDATE_DB="";
    private final Context context;

    public BaseDatos(Context context, CursorFactory factory){
        super(context, DB_NAME, factory, VERSION_DB);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            if(db.isReadOnly()){
                db=getWritableDatabase();
            }
        db.execSQL(CREATE_TABLE_USUARIOS);
        db.execSQL(CREATE_TABLE_CLIENTES);
        db.execSQL(CREATE_TABLE_PRODUCTOS);
        db.execSQL(CREATE_TABLE_UBICACIONES);
        db.execSQL(CREATE_TABLE_PEDIDOS);
        db.execSQL(CREATE_TABLE_ENTREGAS);
        db.execSQL(INSERTAR_USUARIOS);
        db.execSQL(INSERTAR_CLIENTES);
        db.execSQL(INSERTAR_PRODUCTOS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            db.execSQL(UPDATE_DB);
        }

    }
}
