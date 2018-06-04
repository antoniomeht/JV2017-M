package accesoDatos.db4o;

import java.util.ArrayList;
import java.util.Hashtable;

import com.db4o.ObjectContainer;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import modelo.Mundo;
import modelo.Patron;
import modelo.Posicion;

public class MundosDAO implements OperacionesDAO{
	// Requerido por el Singleton 
	private static PatronesDAO instancia;
	
	// Elemento de almacenamiento. 
	private static ObjectContainer db;
	
	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	
	private MundosDAO() {
		
		db = Conexion.getDB();
		try {
			obtener("Demo0");
		}
		catch (DatosException e) {
			cargarPredeterminados();
		}
			
	}
	/**
	 *  Método para generar de datos predeterminados.
	 */
	private void cargarPredeterminados() {
		// En este array los 0 indican celdas con célula muerta y los 1 vivas
		byte[][] espacioDemo =  new byte[][]{ 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0 }, //
			{ 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0 }, // 
			{ 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0 }, //
			{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0 }, // Given:
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Planeador
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // 1x Flip-Flop
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }  // 1x Still Life
		};
		
	
	}
	//OPERACIONES DAO
	/**
	 *  Alta de un objeto en el almacén de datos, 
	 *  sin repeticiones, según el campo id previsto. 
	 *	@param obj - Objeto a almacenar.
	 * @throws DatosException - si ya existe.
	 * Mariano Martinez Madrid
	 */
	
	
	public void alta(Object obj) throws DatosException {
		// TODO Auto-generated method stub
		Object mundoNuevo = (Mundo) obj;										// Para conversión cast 
		Object otroMundo = null;
		try {
			otroMundo = obtener(((Mundo) otroMundo).getNombre());
        } catch (DatosException e) {
            db.store(mundoNuevo);
            return;
        }
        
        throw new DatosException("Mundo: " + mundoNuevo + "ya existe");
	
	}
	@Override
	public Object obtener(String id) throws DatosException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object obtener(Object obj) throws DatosException {
		// TODO Auto-generated method stub
		return null;
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
	@Override
	public String listarDatos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void borrarTodo() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cerrar() {
		// TODO Auto-generated method stub
		
	}

}
