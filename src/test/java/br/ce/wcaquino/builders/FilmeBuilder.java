package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

public class FilmeBuilder {
	
	private Filme filme;
	
	private FilmeBuilder() {}
	
	public static FilmeBuilder umFilme() {
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filme();
		builder.filme.setEstoque(2);
		builder.filme.setNome("Filme 1");
		builder.filme.setPrecoLocacao(4.0);
		return builder ;
	}
	
	public FilmeBuilder semEstoque() {  //criar sem estoque
		filme.setEstoque(0);
		return this;
	}
	
	public Filme agora () {//filme agora
		return filme;
	}

}
