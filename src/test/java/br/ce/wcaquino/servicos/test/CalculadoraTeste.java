package br.ce.wcaquino.servicos.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTeste {
	
	private Calculadora calc; //calculadora fica global em todos cenarios
	
	@Before
	public void setup() {
		calc =new Calculadora();
	}
	
	@Test
	public void somarDoisValores() {
		
		//cenario  = 2 valores a serem somados , 1 instancia da classe para testar
		int a = 3;
		int b = 4;
		
		//acao = somar 2 valores
		
		int resultado =	calc.somar(a,b);
		
		
		//verificação = capturar se a soma esta correta , captura resultado da soma
		
		Assert.assertEquals(7, resultado);
	}
	
	@Test
	public void subtrairDoisValores() {
		//cenario
		int a = 7;
		int b = 3;
		
		
		//acao
		
		int resultado = calc.subtrair(a,b);
		
		//verificacao
		
		Assert.assertEquals(4, resultado);
	}
	
	@Test
	public void divisaoDoisValores() throws NaoPodeDividirPorZeroException {
		
		int a = 12;
		int b = 4;
		
		int resultado = calc.dividir(a,b);
		
		Assert.assertEquals(3, resultado);
 		
	}
	
	
	//definindo comportamento não pode dividir por zero
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void excessaoDividirPorZero () throws NaoPodeDividirPorZeroException {
		
		//cenario
		
		int a = 4;
		int b = 0;
		
		
		//acao
		
		calc.dividir(a,b);
		
	}
	
	
	
	
	
}
