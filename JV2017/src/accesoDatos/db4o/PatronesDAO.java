/** 
 * Proyecto: Juego de la vida.
 * Resuelve todos los aspectos del almacenamiento del DTO Patron utilizando un ArrayList.
 * Colabora en el patron Fachada.
 * @since: prototipo2.0
 * @source: PatronesDAO.java 
 * @version: 2.1 - 2018/05/21
 * @author: Yeray Vera Martínez y Nilton César Vélez López
 */

package accesoDatos.db4o;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import modelo.Patron;

public class PatronesDAO implements OperacionesDAO {

	// Requerido por el Singleton 
	private static PatronesDAO instancia = null;
	
	// Elemento de almacenamiento. 
	private static ObjectContainer db;

	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	private PatronesDAO() {
		db= Conexion.getDB();
		try {
			obtener("Demo0");
		}
		catch (DatosException e) {
		cargarPredeterminados();
		}
	}

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia
	 */
	public static PatronesDAO getInstancia() {
		if (instancia == null) {
			instancia = new PatronesDAO();
		}
		return instancia;
	}

	/**
	 *  Método para generar datos predeterminados.
	 */
	private void cargarPredeterminados() {
		byte[][] esquemaDemo =  new byte[][]{ 
			{ 0, 0, 0, 0 }, 
			{ 1, 0, 1, 0 }, 
			{ 0, 0, 0, 1 }, 
			{ 0, 1, 1, 1 }, 
			{ 0, 0, 0, 0 }
		};
		Patron patronDemo = new Patron("PatronDemo", esquemaDemo);
		try {
		alta(patronDemo);
		}
		catch (DatosException e) {
			e.printStackTrace();
		}
	}

	//OPERACIONES DAO
	/**
	 * Búsqueda binaria de un Patron dado su nombre.
	 * @param nombre - el nombre del Patron a buscar.
	 * @return - el Patron encontrado.
	 * @throws DatosException - si no existe.
	 */	
	@Override
	public Patron obtener(String nombre) throws DatosException {
		Query consulta = db.query();
		
		//Consulta		
		consulta.constrain(Patron.class);
		consulta.descend("nombre").constrain(nombre).equal();
		
		//Resultado
		ObjectSet<Patron> resultado;
		resultado = consulta.execute();
		if (resultado.size() > 0) {
			return resultado.get(0);
		}
		else {
			throw new DatosException("Obtener: " + nombre + " no existe.");
		}
	}
	
	/**
	 * Búsqueda de Patron dado un objeto, reenvía al método que utiliza nombre.
	 * @param obj - el Patron a buscar.
	 * @return - el Patron encontrado.
	 * @throws DatosException - si no existe.
	 */
	@Override
	public Patron obtener(Object obj) throws DatosException  {
		return this.obtener(((Patron) obj).getNombre());
	}

	/**
	 *  Alta de un nuevo Patron en orden y sin repeticiones según el campo nombre. 
	 *  Busca previamente la posición que le corresponde por búsqueda binaria.
	 * @param obj - Patron a almacenar.
	 * @throws DatosException - si ya existe.
	 */
	@Override
	public void alta(Object obj) throws DatosException  {
		assert obj != null;
		Patron patron = (Patron) obj;	
		
		try {
			obtener(patron.getNombre());
		}
		catch  (DatosException e) {
			db.store(patron);
			return;
		}
			throw new DatosException("Alta: "+ patron.getNombre() + " ya existe");
		
	}

	/**
	 * Elimina el objeto, dado el id utilizado para el almacenamiento.
	 * @param nombre - el nombre del Patron a eliminar.
	 * @return - el Patron eliminado. 
	 * @throws DatosException - si no existe.
	 */
	@Override
	public Patron baja(String nombreP) throws DatosException  {
		assert nombreP != null;
		assert nombreP != "";
		assert nombreP != " ";
		Patron patron = null;
		try {
			obtener(nombreP);
			db.delete(patron);
			return patron;
		}
		catch (DatosException e) {
			throw new DatosException("Baja: "+ nombreP + " no existe");
		}	
	}

	/**
	 *  Actualiza datos de un Mundo reemplazando el almacenado por el recibido.
	 *	@param obj - Patron con las modificaciones.
	 *  @throws DatosException - si no existe.
	 */
	@Override
	public void actualizar(Object obj) throws DatosException  {
		assert obj != null;
		Patron patronActualizado = (Patron) obj;
		Patron patronAux = null;
		try {
			patronAux = obtener(patronActualizado.getNombre());
			patronAux.setNombre(patronActualizado.getNombre());
			patronAux.setEsquema(patronActualizado.getEsquema());
			db.store(patronAux);
			
		}
		catch(DatosException e) {
			throw new DatosException("Actualizar: "+ patronActualizado.getNombre() + " no existe");
			
		}
		
	}
	
	/**
	 * Obtiene el listado de todos los objetos Patron almacenados.
	 * @return el texto con los datos.
	 */
	@Override
	public String listarDatos() {
		StringBuilder listado = new StringBuilder();
		ObjectSet<Patron> result;
		Query consulta = db.query();
		consulta.constrain(Patron.class);
		result = consulta.execute();
		for(Patron patron:result) {
			listado.append("\n" + patron);
			
		}
		return listado.toString();
	}

	/**
	 * Obtiene todos los Identificadores de los patrones almacenados.
	 * @return El texto con los datos.
	 */
	public String listarId() {
		StringBuilder listado = new StringBuilder();
		ObjectSet<Patron> result;
		Query consulta = db.query();
		consulta.constrain(Patron.class);
		result = consulta.execute();
		for(Patron patron:result) {
			if(patron != null) {
				listado.append(patron.getNombre() + "\n");
			}
		}
		return listado.toString();
	}
	
	@Override
	public void borrarTodo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cerrar() {
		// TODO Auto-generated method stub
		
	}
}//class