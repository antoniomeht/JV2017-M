/** 
 * Proyecto: Juego de la vida.
 * Prueba Junit4 de la clase SimulacionesDAO.
 * @since: prototipo 2.1
 * @source: UsuariosDAOTest.java 
 * @version: 2.1 - 2018.05.31
 * @author: DAM GRUPO 2 Antonio Araez Moreno, Carlos Carrion Martinez, 
 * Alvaro Martinez Martinez		
 */
package test.accesoDatos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import accesoDatos.Datos;
import accesoDatos.DatosException;
import modelo.ClaveAcceso;
import modelo.Correo;
import modelo.DireccionPostal;
import modelo.ModeloException;
import modelo.Nif;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class UsuariosDAOTest {

	private static Datos fachada;
	private Usuario usuarioPrueba;


	/**
	 * Método que se ejecuta una sola vez al principio del conjunto pruebas.
	 * @throws DatosException 
	 * @author GRUPO 2 DAM Antonio Araez Moreno
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
	 * Se ejecuta antes de cada @test.
	 * @throws ModeloException 
	 * @throws DatosException 
	 * @author GRUPO 2 DAM Carlos Carrion Martinez
	 */
	@Before
	public void crearDatosPrueba() {
		
		try {
			usuarioPrueba =  new Usuario(new Nif("00000000T"), "Carlitos",
					"Carrion Martinez", new DireccionPostal("AntonioBernal", "101", "30835", "Murcia"), 
					new Correo("correo@correo.com"), new Fecha(1994, 10, 9), 
					new Fecha(2015, 11, 4), new ClaveAcceso("Miau#0"), RolUsuario.NORMAL);
		} 
		
		catch (ModeloException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que se ejecuta al terminar de cada @test.
	 * @author GRUPO 2 DAM Carlos Carrion Martinez
	 */
	
	@After
	public void borraDatosPrueba() {
		fachada.borrarTodosUsuarios();
		usuarioPrueba = null;
	}
	
	
	
	/**
	 * Metodos Test
	 * @author GRUPO 2 DAM Antonio Araez Moreno
	 */
	
	@Test
	public void testObtenerUsuario() {
		try {
			//Cambio a alta
			fachada.altaUsuario(usuarioPrueba);
			assertSame(usuarioPrueba, fachada.obtenerUsuario(usuarioPrueba));
		}
		catch (DatosException e) {
			
		}
	}
	
	@Test
	public void testObtenerUsuarioId() {
		fail("No implenetado");
	}

	@Test
	public void testObtenerEquivalenciaId() {
		fail("No implenetado");
	}
	
	/**
	 * Metodos Test
	 * @author GRUPO 2 DAM Antonio Araez Moreno
	 */
	
	@Test
	public void testAltaUsuario() {
		try {
			fachada.altaUsuario(usuarioPrueba);
			assertSame (usuarioPrueba, fachada.obtenerUsuario(usuarioPrueba) );
		}
		catch (DatosException e) {
			
		}
	}

	@Test
	public void testBajaUsuario() {
		try {
			fachada.bajaUsuario(usuarioPrueba.getIdUsr());
			assertSame(usuarioPrueba.getIdUsr(), fachada.bajaUsuario(usuarioPrueba.getIdUsr()));
		}
		catch(DatosException e){
		}
	}

	@Test
	public void testActualizarUsuario() {
		fail("No implenetado");
	}

	@Test
	public void testToStringDatosUsuarios() {
		fail("No implenetado");
	}

	

} //class