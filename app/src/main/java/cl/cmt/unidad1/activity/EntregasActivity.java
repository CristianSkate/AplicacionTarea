package cl.cmt.unidad1.activity;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cl.cmt.unidad1.clases.Entrega;
import cl.cmt.unidad1.clases.Pedido;
import cl.cmt.unidad1.clases.Producto;
import cl.cmt.unidad1.dao.EntregasDS;
import cl.cmt.unidad1.dao.PedidosDS;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EntregasActivity extends Activity {
	public static ArrayList<Producto> prods = new ArrayList<Producto>();
	public Pedido pedido;
	public PedidosDS datasourcePe;
	public EntregasDS datasourceEn;
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
		final int idPedido = extras.getInt("idPedido");
		datasourcePe = new PedidosDS(this);
		datasourceEn = new EntregasDS(this);
		try {
			datasourcePe.open();
			datasourceEn.open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SharedPreferences prefs = getSharedPreferences("staticVars", Context.MODE_PRIVATE);
		final int idVendedor = prefs.getInt("idVendedor", 0);
		pedido = datasourcePe.buscarPedidoPorId(idPedido);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		final Date date = new Date();		
		txtFechaEntrega.setText(String.valueOf(dateFormat.format(date)));
		txtNombreCliente.setText(pedido.cliente);
		txtProducto.setText(pedido.producto);
		txtCantidad.setText(String.valueOf(pedido.cantidad));
		txtTotal.setText(String.valueOf(pedido.total));
		

		btnGuardar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				Entrega ent = new Entrega();
				
				if(txtCantidad.getText().toString().equals("") || txtTotal.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
				}else
				{
					ent.fechaEntrega = date;
					ent.cliente = txtNombreCliente.getText().toString();
					ent.producto = txtProducto.getText().toString();
					ent.cantidad = Integer.parseInt(txtCantidad.getText().toString());
//					ent.precio = Integer.parseInt(txtPrecio.getText().toString());
					ent.total = Integer.parseInt(txtTotal.getText().toString());
					Entrega en = datasourceEn.insertarEntrega(ent.cliente,ent.producto,ent.cantidad,ent.fechaEntrega,ent.total,idVendedor);
					datasourcePe.eliminarPedido(pedido);
					//LoginActivity.entregas.add(ent);
					//LoginActivity.pedidos.remove(index);
					VerPedidosActivity.adapter.notifyDataSetChanged();
					if(en !=null) {
						Toast.makeText(getApplicationContext(), "Entrega registrada con éxito", Toast.LENGTH_SHORT).show();
						finish();
					}else
					{
						Toast.makeText(getApplicationContext(), "Algo pasó con la conexión", Toast.LENGTH_SHORT).show();
					}
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
