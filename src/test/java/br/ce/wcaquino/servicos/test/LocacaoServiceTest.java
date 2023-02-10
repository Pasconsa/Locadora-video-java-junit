package br.ce.wcaquino.servicos.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import java.security.Provider.Service;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule  // utilizado na terceira teste nova
	public ExpectedException exception = ExpectedException.none();
	
	
	
	
	@Test
	public void deveAlugarFilme() throws Exception {
		assumeFalse(DataUtils.verificarDiaSemana(new Date (), Calendar.SATURDAY));  //o teste não sera verificado no sabado
		
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
	}
	
	
	
	@Test(expected= FilmeSemEstoqueException.class)  //01 Teste elegante com a classe exception
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 5.0));
		//acao
		service.alugarFilme(usuario, filmes);
	}
	
	
	@Test   // teste robusto com classe exception
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		//cenario
		LocacaoService service = new LocacaoService();
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 5.0));
		
		//acao
		
			try {
				service.alugarFilme(null,  filmes);
				Assert.fail();
			} catch (LocadoraException e) {
				// TODO Auto-generated catch block
				assertThat(e.getMessage(), is("Usuario vazio"));
			}
		
	}
	
	
	@Test  // Teste forma nova , teste que valida o filme
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException{
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilme(usuario, null);
	}
	
	@ Test
	public void devoPagar75pctNoFilme3 () throws FilmeSemEstoqueException, LocadoraException {
		
		//cenario vou precisar de um usuario para alugar e 3 filmes para dar desconto e alocacao filme
		
		Usuario usuario = new Usuario ();
		
		List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2 , 4.0), new Filme("Filme2", 2 , 4.0),new Filme("Filme3", 2 , 4.0));
		
		LocacaoService service = new LocacaoService();
		
		//acao
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verificação 4+4+3 =11
		assertThat(resultado.getValor(), is(11.0));
		
	}
	
	@ Test
	public void devoPagar50pctNoFilme4 () throws FilmeSemEstoqueException, LocadoraException {
		
		//cenario vou precisar de um usuario para alugar e 4 filmes para dar desconto e alocacao filme
		
		Usuario usuario = new Usuario ();
		
		List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2 , 4.0), new Filme("Filme2", 2 , 4.0),new Filme("Filme3", 2 , 4.0),new Filme("Filme4", 2 , 4.0));
		
		LocacaoService service = new LocacaoService();
		
		//acao
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verificação 4+4+3+2 =13
		assertThat(resultado.getValor(), is(13.0));
		
	}
	
	
	@ Test
	public void devoPagar25pctNoFilme5 () throws FilmeSemEstoqueException, LocadoraException {
		
		//cenario vou precisar de um usuario para alugar e 5 filmes para dar desconto e alocacao filme
		
		Usuario usuario = new Usuario ();
		
		List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2 , 4.0), new Filme("Filme2", 2 , 4.0),new Filme("Filme3", 2 , 4.0),new Filme("Filme4", 2 , 4.0) 
				,new Filme("Filme5", 2 , 4.0));
		
		LocacaoService service = new LocacaoService();
		
		//acao
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verificação 4+4+3+2+1 =14
		assertThat(resultado.getValor(), is(14.0));
		
	}
	
	@ Test
	public void devoPagar0pctNoFilme6 () throws FilmeSemEstoqueException, LocadoraException {
		
		//cenario vou precisar de um usuario para alugar e 5 filmes para dar desconto e alocacao filme
		
		Usuario usuario = new Usuario ();
		
		List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2 , 4.0), new Filme("Filme2", 2 , 4.0),new Filme("Filme3", 2 , 4.0),new Filme("Filme4", 2 , 4.0) 
				,new Filme("Filme5", 2 , 4.0),new Filme("Filme6", 2 , 4.0));
		
		LocacaoService service = new LocacaoService();
		
		//acao
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		//verificação 4+4+3+2+1+0 =14
		assertThat(resultado.getValor(), is(14.0));
		
	}
	
	@Test  //
	public void deveDevolverNaSegundaAoAlugarNoSabado () throws FilmeSemEstoqueException, LocadoraException {
		assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		Usuario usuario = new Usuario ("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme1", 2 , 4.0));
		
		LocacaoService service = new LocacaoService();
		Locacao retorno = service.alugarFilme(usuario, filmes);
		
		boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
		Assert.assertTrue(ehSegunda);
	}

}
