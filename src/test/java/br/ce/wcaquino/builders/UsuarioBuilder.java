package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Usuario;

public class UsuarioBuilder {
	//refatoração
	private Usuario usuario;
	
	private UsuarioBuilder()  {}
	
	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario ();
		builder.usuario.setNome("Usuario 1");
		return builder;
	}
	
	public Usuario agora() {   //porta de entrada para um usuário , objeto localizado em um unico ponto
		return usuario;
	}

}
