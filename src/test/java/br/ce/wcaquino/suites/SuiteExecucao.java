package br.ce.wcaquino.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.servicos.test.CalculadoraTeste;
import br.ce.wcaquino.servicos.test.CalculoValorLocacaoTeste;

//@RunWith(Suite.class) //start Suite

//determinar todos os testes que vão ser executados pelo suite
@SuiteClasses({
		CalculadoraTeste.class,
		CalculoValorLocacaoTeste.class,
		CalculoValorLocacaoTeste.class
		
		
})   
public class SuiteExecucao {

}
