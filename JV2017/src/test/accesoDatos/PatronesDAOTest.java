/** 
 *  Proyecto: Juego de la vida.
 *  Clase JUnit 4 para pruebas del DAO.
 *  @since: prototipo2.1
 *  @source: DatosTest.java 
 *  @version: 2.1 - 2018/06/04 
 *  @author: Grupo 2	Antonio Araez Moreno
 *  
 */

package test.accesoDatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import accesoDatos.Datos;
import accesoDatos.DatosException;
import modelo.ModeloException;
import modelo.Patron;

public class PatronesDAOTest {

	private static Datos fachada;
	private Patron patronPrueba;

	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 * @throws DatosException 
	 */
	@BeforeClass
	public static void crearFachadaDatos() {
		try {
			fachada = new Datos();
		} 
		catch (DatosException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que se ejecuta antes de cada @test.
	 * @throws ModeloException 
	 * @throws DatosException 
	 */
	@Before
	public void crearDatosPrueba() {
		try {
			patronPrueba = fachada.obtenerPatron("PatronDemo");
		} 
		catch (DatosException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que se ejecuta al terminar de cada @test.
	 */
	@After
	public void borraDatosPrueba() {
		fachada.borrarTodosPatrones();
		patronPrueba = null;
	}

	@Test
	public void testObtenerUsuarioId() {
		fail("No implenetado");
	}

	@Test
	public void testObtenerPatronString() {
		fail("No implenetado");
	}
	
	/**
	 *  Método testAltaPatron() que da de alta un patron y comprueba
	 *  @author GRUPO 2 DAM Antonio Araez Moreno
	 */
	
	@Test
	public void testAltaPatron() {
		try {
			fachada.altaPatron(patronPrueba);
			assertSame(patronPrueba, fachada.obtenerPatron(patronPrueba));
		}
		catch(DatosException e){
		}
	}

	@Test
	public void testBajaPatron() {
		fail("No implenetado");
	}
	
	@Test
	public void testObtenerPatronPatron() {
		fail("No implenetado");
	}

	@Test
	public void testActualizarPatron() {
		fail("No implenetado");
	}

	@Test
	public void testToStringDatosPatrones() {
		fail("No implenetado");
	}

} //class