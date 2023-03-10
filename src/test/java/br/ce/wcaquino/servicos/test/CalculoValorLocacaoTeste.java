package br.ce.wcaquino.servicos.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;


@RunWith(Parameterized.class) // testes desta classe sera tratado de um modo diferente  
public class CalculoValorLocacaoTeste {
	
	//colocar as variaveis que filme e valor locacao
	
	public LocacaoService service ;
	
	@Parameter
	public List<Filme> filmes;
	
	@Parameter(value=1)
	public Double valorLocacao;
	
	@Parameter(value=2)
	public String cenario;
	
	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	private static Filme filme1 = FilmeBuilder.umFilme().agora();
	private static Filme filme2 = FilmeBuilder.umFilme().agora();
	private static Filme filme3 = FilmeBuilder.umFilme().agora();
	private static Filme filme4 = FilmeBuilder.umFilme().agora();
	
	private static Filme filme5 = FilmeBuilder.umFilme().agora();
	private static Filme filme6 = FilmeBuilder.umFilme().agora();
	
	//novos cenarios nova fonte de dados
	@Parameters(name="{2}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object [][]{
			{Arrays.asList(filme1, filme2 , filme3), 11.0, "3 filmes 25%"},
			{Arrays.asList(filme1, filme2 , filme3, filme4), 13.0, "4 filmes 50%"},
			{Arrays.asList(filme1, filme2 , filme3, filme4,filme5), 14.0, "5 filmes 75%"},
			{Arrays.asList(filme1, filme2 , filme3, filme4,filme5,filme6), 14.0, "6 filmes 100%"},
	});
				
	}
	
		
	
	
	@ Test
	public void devoCalcularValorLocacaoComDescontos () throws FilmeSemEstoqueException, LocadoraException {
		
		Usuario usuario = new Usuario ();
		
		
		LocacaoService service = new LocacaoService();
		
		//acao
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verifica????o 4+4+3+2+1 =14
		assertThat(resultado.getValor(), is(valorLocacao));
		
	}
	

}
