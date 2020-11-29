package Practica4Jardineria.Practica4Jardineria;

import org.junit.Test;
import static org.junit.Assert.*;


import errores.EmailNoExiste;
import Builder.BuilderCliente;
import JardineriaTest.cliente;

public class TestCliente {
	
	@Test
	public void Emailnofunciona() {
		try {
			BuilderCliente.buildClient(12, "Dario", "Alex", "123491523", cliente.DocumentType.DNI, "12312367F", "123@iona.com", "nofunciona");
		} catch (Exception e) {
			assertTrue(e instanceof EmailNoExiste);
			return;
		}
		fail("Excepcion");
	}
	
	
	public void Emailfunciona() {
		try {
			BuilderCliente.buildClient(12, "Dario", "Alex", "123491523", cliente.DocumentType.DNI, "12397123R", "123@gmail.com", "funciona");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void lenghtDNI_shouldNotWork() {
		
	}
	
	
	public void lenghtDNI_shouldWork() {
			
	}
	
	
	
	
	public void wordDNI_shouldNotWork() {
			
	}
	
	
	public void wordDNI_shouldWork() {
			
	}
	
	
	
	public void lenghtNIE_shouldNotWork() {
		
	}
	
	
	public void lenghtNIE_shouldWork() {
			
	}
	
	
	

}
