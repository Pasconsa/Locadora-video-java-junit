package br.ce.wcaquino.servicos.test;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {

	@Test
	public void test () {
		
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals(1, 1);  //resultado esperado -- resultado atual
		Assert.assertEquals(1.01 , 1.02 , 0.1); //coloca uma margem de ero para considerar o valor que quero
		
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 2");
		Usuario u3 = null;
		
		Assert.assertNotEquals(u1 , u2);  // para objeto as intancias são diferentes para igualar é necessário colocar equals em cada objeto
		Assert.assertNull(u3);
		
		Assert.assertEquals("voce errou ",1, 1);  //´possivel adicionar uma menssagem quando a erro na comparação

	}
	
}
