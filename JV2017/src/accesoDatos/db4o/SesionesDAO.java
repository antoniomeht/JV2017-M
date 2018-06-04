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
	 * Alta de una nueva SesionUsuario en orden y sin repeticiones según IdUsr + fecha. 
	 * Busca previamente la posición que le corresponde por búsqueda binaria.
	 * @param obj - la SesionUsuario a almacenar.
	 * @throws DatosException - si ya existe.
	 * @author Grupo 3 - Marcos Martínez Martínez
	 */
	@Override
	public Object obtener(Object obj) throws DatosException {
		return this.obtener(((SesionUsuario) obj).getIdSesion());
	}
	
	/**
	 * Alta de una nueva SesionUsuario en orden y sin repeticiones según IdUsr + fecha. 
	 * Busca previamente la posición que le corresponde por búsqueda binaria.
	 * @param obj - la SesionUsuario a almacenar.
	 * @throws DatosException - si ya existe.
	 * @author Grupo 3 - Marcos Martínez
	 */
	@Override
	public void alta(Object obj) throws DatosException {
		SesionUsuario sesionNueva = (SesionUsuario) obj;
        SesionUsuario sesionPrevia = null;
        
        try {
            sesionPrevia = (SesionUsuario) obtener(sesionNueva.getIdSesion());
        } catch (DatosException e) {
            db.store(sesionNueva);
            return;
        }
        
        throw new DatosException("Simulacion: " + sesionPrevia + "ya existente");
		
	}
	
	/**
	 * Elimina el objeto, dado el id utilizado para el almacenamiento.
	 * @param idSesion - identificador de la SesionUsuario a eliminar.
	 * @return - la SesionUsuario eliminada.
	 * @throws DatosException - si no existe.
	 * @author Grupo 3 - Marcos Martínez
	 */
	@Override
	public Object baja(String idSesion) throws DatosException {
		assert idSesion != null;
		
		try {
			SesionUsuario sesionActual = (SesionUsuario) obtener(idSesion);
			db.delete(sesionActual);
			return sesionActual;
		}
		catch (DatosException e) {
			throw new DatosException("Simulacion: " + idSesion + "no existe, o no se puede borrar");
		}
	}

	/**
	 *  Actualiza datos de una SesionUsuario reemplazando el almacenado por el recibido.
	 *	@param obj - SesionUsuario con las modificaciones.
	 * @throws DatosException - si no existe.
	 * @author Grupo3 - Juan Jesus Nicolas Agustin
	 */
	@Override
	public void actualizar(Object obj) throws DatosException {
		SesionUsuario sesionActualizada = (SesionUsuario) obj;
		SesionUsuario sesionPrevia = null;
		
		try {
			sesionPrevia = (SesionUsuario) obtener(sesionActualizada.getIdSesion());
			sesionPrevia.setUsr(sesionActualizada.getUsr());
			sesionPrevia.setFecha(sesionActualizada.getFecha());
			sesionPrevia.setEstado(sesionActualizada.getEstado());
			db.store(sesionPrevia);
		} catch (DatosException e) {
			throw new DatosException("Actualizar: " + sesionActualizada.getIdSesion() + "no existe");
		}
		
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
		for (SesionUsuario sim: obtenerTodasSesiones()) {
            db.delete(sim);
        }
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
