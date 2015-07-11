package cl.cmt.unidad1.activity;

import java.util.ArrayList;

import cl.cmt.unidad1.clases.Cliente;
import cl.cmt.unidad1.clases.Entrega;
import cl.cmt.unidad1.clases.Pedido;
import cl.cmt.unidad1.clases.Usuario;
import android.app.Activity;
import android.content.Intent;
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
	public static ArrayList<Cliente> eliminados = new ArrayList<Cliente>();
	public static ArrayList<Entrega> entregas = new ArrayList<Entrega>();
	public static ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Aquí está la forma de obtener el control de un botón y asignarle una acción
        Button btn_ingresar = (Button)findViewById(R.id.btn_ingresar);
        
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
    	
    	Usuario usuario = new Usuario();
    	if(usuario.validarUsuario(txt_login.getText().toString(), txt_contrasena.getText().toString())){
    		Toast.makeText(LoginActivity.this, "Usuario correcto", Toast.LENGTH_SHORT).show();
    		String login = txt_login.getText().toString();
    		Intent intent = new Intent(LoginActivity.this, MantenedorPrincipal.class);
    		Cliente cliente = new Cliente();
    		if(login.equals("cmartinez")){
    			clientes= cliente.listaClientes();
    		}else
    		{
    			clientes= cliente.listaClientes1();
    		}
    		//intent.putExtra("login", login);
    		txt_login.setText("");
        	txt_contrasena.setText("");
        	//Agregamos una variable a nuestro intent para validar usuario	
        		
        	LoginActivity.this.startActivity(intent);
    			
    		   		
    		
    				
    	}else{
    		Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    	}
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
