package accesoDatos.db4o;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import modelo.SesionUsuario;
import modelo.Simulacion;

public class SesionesDAO implements OperacionesDAO {
	// Requerido por el Singleton
	private static SesionesDAO instancia = null;
	// Metodo de almacenamiento
	private static ObjectContainer db;
	

	/** 
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez
	 * @author Grupo 3 - Marcos Martínez 
	 */

	private SesionesDAO() {
	db= Conexion.getDB();
	try {
		obtener("Demo0");
	}
	catch (DatosException e) {
	//cargarPredeterminados(); Falta ejecutar 
	}
}

	/** 
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia
	 *  @author Grupo 3 - Marcos Martínez
	 */

	public static SesionesDAO getInstancia() {
		if (instancia == null) {
		instancia = new SesionesDAO();
	}
	return instancia;
	}
	
	/**
	 * Búsqueda de sesión por idSesion.
	 * @param idSesion - el idUsr+fecha a buscar.
	 * @return - la sesión encontrada. 
	 * @throws DatosException - si no existe. 
	 * @author Grupo 3 -- Juan Jesús Nicolás Agustín
	 */
	@Override
	public Object obtener(String idSesion) throws DatosException {
			assert idSesion!=null;
			Query consulta = db.query();
			ObjectSet<SesionesDAO> result = consulta.execute();
			consulta.constrain(SesionesDAO.class);
			consulta.descend("idSesion").constrain(idSesion).equal();
			
			if (result.size() > 0) {
				return result.get(0);
			} else {
					return null;
			}		
		}
	
	
	/**
	 * Búsqueda de Sesion dado un objeto, reenvía al método que utiliza idSesion.
	 * @param obj - la SesionUsuario a buscar.
	 * @return - la Sesion encontrada.
	 * @throws DatosException - si no existe
	 * @author Grupo 3 - Marcos Martínez Martínez
	 */
	@Override
	public Object obtener(Object obj) throws DatosException {
		return this.obtener(((SesionUsuario) obj).getIdSesion());
	}

	@Override
	public void alta(Object obj) throws DatosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object baja(String id) throws DatosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Object obj) throws DatosException {
		// TODO Auto-generated method stub
		
	}
	/**
	* Obtiene el listado de todos las sesiones almacenadas.
	* @return el texto con el volcado de datos.
	* @author Grupo 3 - Marcos Martínez
	*/
	
	@Override
	public String listarDatos() {
		StringBuilder listado = new StringBuilder();
		for (SesionUsuario sesiones: obtenerTodasSesiones()) {
			if (sesiones != null) {
				listado.append("\n" + sesiones);
			}
		}
		return listado.toString();
	}

	/**
     * Elimina todas las sesiones almacenadas.
     * @author Grupo 3 - Marcos Martínez
     */

	@Override
    public void borrarTodo() {
        // Elimina cada uno de los obtenidos
        
     	}
        
    
	
	/**
	 *  Cierra almacenes de datos.
	 */
	@Override
	public void cerrar() {
		// Método sin uso, a menos que haya persistencia.
		
	}
	
	/**
     * Obtiene todos las sesiones de usuario almacenadas.
     * @return - Se devuelve la lista con todas las sesiones.
     * @author Grupo 3 - Marcos Martínez
     */
    public List <SesionUsuario> obtenerTodasSesiones() {
        Query consulta = db.query();
        consulta.constrain(SesionUsuario.class);
        return consulta.execute();
    }

}
