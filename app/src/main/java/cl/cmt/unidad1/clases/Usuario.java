package cl.cmt.unidad1.clases;

import java.util.ArrayList;

import cl.cmt.unidad1.dao.UsuariosDS;

public class Usuario {

	
	public int id_usuario;
	public String nombre_usuario;
	public String login_usuario;
	public String contrasena;

	
	//Este metodo realiza la validacion del login del usuario
	
	/*public boolean validarUsuario(String login, String contrasena){

		Usuario usuario;
		ArrayList<Usuario> usuarios = ();
		int largo = usuarios.size();
		for(int i=0; i< largo; i++){
			usuario = usuarios.get(i);
			if(usuario.login_usuario.equals(login) && usuario.contrasena.equals(contrasena)){
				return true;
			}
		}
		return false;
	}
	*/
	
	//Este metodo deuelve la forma string de la clase
	
	public String toString(){
		return String.valueOf(this.id_usuario) + " : " + this.nombre_usuario + " (" + this.login_usuario +")";
	}
	
}
