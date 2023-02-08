package br.ce.wcaquino.servicos.test;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)  // este metodo permite que os testes deguem em ordem alfabetica
public class OrdemTest {
	
	public static int contador = 0; //valor ser contido entre os testes

	@Test
	public void inicia(){
		contador = 1;     //inicia o contador
	}
	
	@Test
	public void verifica(){
		Assert.assertEquals(1, contador); //valor esperado seja 1 que vem do contador
	}
}
