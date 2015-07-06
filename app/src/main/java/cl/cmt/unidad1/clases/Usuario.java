package cl.cmt.unidad1.clases;

import java.util.ArrayList;

public class Usuario {

	
	public int id_usuario;
	public String nombre_usuario;
	public String login_usuario;
	public String contrasena;
	
	
	//El codigo a continuacion genera una lista y la devuelve.
	
	public ArrayList<Usuario> listaUsuarios(){
		
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		Usuario usuario = new Usuario();
		usuario.id_usuario = 1;
		usuario.nombre_usuario = "Juan Perez";
		usuario.login_usuario = "juan";
		usuario.contrasena  = "juan";
		
		lista.add(usuario);
		
		usuario = new Usuario();
		usuario.id_usuario = 2;
		usuario.nombre_usuario = "Cristian Martinez";
		usuario.login_usuario = "cmartinez";
		usuario.contrasena = "abc123";
		
		lista.add(usuario);
		return lista;
		
		
	}
	
	//Este metodo realiza la validacion del login del usuario
	
	public boolean validarUsuario(String login, String contrasena){
		
		Usuario usuario;
		ArrayList<Usuario> usuarios = listaUsuarios();
		int largo = usuarios.size();
		for(int i=0; i< largo; i++){
			usuario = usuarios.get(i);
			if(usuario.login_usuario.equals(login) && usuario.contrasena.equals(contrasena)){
				return true;
			}
		}
		return false;
	}
	
	//Este metodo deuelve la forma string de la clase
	
	public String toString(){
		return String.valueOf(this.id_usuario) + " : " + this.nombre_usuario + " (" + this.login_usuario +")";
	}
	
}
