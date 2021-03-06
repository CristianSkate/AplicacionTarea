package cl.cmt.unidad1.activity;

import java.sql.SQLException;
import java.util.ArrayList;

import cl.cmt.unidad1.clases.Cliente;
import cl.cmt.unidad1.clases.Entrega;
import cl.cmt.unidad1.clases.Pedido;
import cl.cmt.unidad1.clases.Ubicacion;
import cl.cmt.unidad1.clases.Usuario;
import cl.cmt.unidad1.dao.UsuariosDS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {
	public static ArrayList<Cliente> clientes;
	private UsuariosDS datasource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Aquí está la forma de obtener el control de un botón y asignarle una acción
        Button btn_ingresar = (Button)findViewById(R.id.btn_ingresar);
		SharedPreferences prefs= obtenerPreferencias();
		if(prefs.getInt("idVendedor", 0) != 0) {
			Intent intent = new Intent (LoginActivity.this, MantenedorPrincipal.class);
			LoginActivity.this.startActivity(intent);
		}
		btn_ingresar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// acción al presionar el boton
				validarLoginUsuario();
			}

		});
    }
    
    public void validarLoginUsuario(){
    	//función para validar un usuario
    	EditText txt_login = (EditText)findViewById(R.id.txt_usuario);
    	EditText txt_contrasena = (EditText)findViewById(R.id.txt_contrasena);
		SharedPreferences prefs= obtenerPreferencias();
		datasource = new UsuariosDS(this);
		try {
			datasource.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			Usuario usuario = datasource.loginUsuario(txt_login.getText().toString(), txt_contrasena.getText().toString());
			if (usuario != null) {
				//Toast.makeText(LoginActivity.this, "Usuario correcto", Toast.LENGTH_SHORT).show();
				prefs.edit().putInt("idVendedor", usuario.id_usuario).apply(); //para buscar clientes
				prefs.edit().putString("nombreVendedor", usuario.nombre_usuario).apply();
				Intent intent = new Intent(LoginActivity.this, MantenedorPrincipal.class);
				txt_login.setText("");
				txt_contrasena.setText("");
				LoginActivity.this.startActivity(intent);
			} else {
				Toast.makeText(LoginActivity.this, R.string.txtUsuarioPasswordIncorrectos, Toast.LENGTH_SHORT).show();
			}
    }

	public SharedPreferences obtenerPreferencias(){
		SharedPreferences prefs = this.getSharedPreferences("staticVars", Context.MODE_PRIVATE);
		return prefs;
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
