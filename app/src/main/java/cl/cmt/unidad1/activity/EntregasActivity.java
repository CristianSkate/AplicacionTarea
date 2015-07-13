package cl.cmt.unidad1.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cl.cmt.unidad1.clases.Entrega;
import cl.cmt.unidad1.clases.Producto;
import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EntregasActivity extends Activity {
	public static ArrayList<Producto> prods = new ArrayList<Producto>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entregas);
		
		final EditText txtFechaEntrega = (EditText)findViewById(R.id.txtFechaEntrega);
		final EditText txtNombreCliente = (EditText)findViewById(R.id.txtClienteEntrega);
//		final Spinner spnProductos = (Spinner)findViewById(R.id.spnProductoEntrega);
		final EditText txtProducto = (EditText)findViewById(R.id.txtProducto);
		final EditText txtCantidad = (EditText)findViewById(R.id.txtCantEntrega);
//		final EditText txtPrecio = (EditText)findViewById(R.id.txtPrecioEntrega);
		final EditText txtTotal = (EditText)findViewById(R.id.txtTotalEntrega);
		Button btnGuardar = (Button)findViewById(R.id.btnGuardarEntrega);
		Button btnCancelar = (Button)findViewById(R.id.btnCancelarEntrega);
		
		
		Bundle extras = getIntent().getExtras();
		final int index = extras.getInt("index");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		final Date date = new Date();		
		txtFechaEntrega.setText(String.valueOf(dateFormat.format(date)));
		txtNombreCliente.setText(LoginActivity.pedidos.get(index).cliente);
		txtProducto.setText(LoginActivity.pedidos.get(index).producto);
		txtCantidad.setText(String.valueOf(LoginActivity.pedidos.get(index).cantidad));
		txtTotal.setText(String.valueOf(LoginActivity.pedidos.get(index).total));
		

		btnGuardar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				Entrega ent = new Entrega();
				
				if(txtCantidad.getText().toString().equals("") || txtTotal.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
				}else
				{
					ent.idEntrega=LoginActivity.entregas.size();
					ent.fechaEntrega = date;
					ent.cliente = txtNombreCliente.getText().toString();
					ent.producto = txtProducto.getText().toString();
					ent.cantidad = Integer.parseInt(txtCantidad.getText().toString());
//					ent.precio = Integer.parseInt(txtPrecio.getText().toString());
					ent.total = Integer.parseInt(txtTotal.getText().toString());
					LoginActivity.entregas.add(ent);
					LoginActivity.pedidos.remove(index);
					VerPedidosActivity.adapter.notifyDataSetChanged();
					Toast.makeText(getApplicationContext(), "Entrega registrada con éxito", Toast.LENGTH_SHORT).show();
					finish();
				}
			}
			
		});
		
		btnCancelar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				finish();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entregas, menu);
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
