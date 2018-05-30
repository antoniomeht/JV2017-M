package accesoDatos.db4o;

import java.util.Hashtable;

import com.db4o.ObjectContainer;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import modelo.Mundo;

public class MundosDAO implements OperacionesDAO{
	// Requerido por el Singleton 
	private static PatronesDAO instancia;
	
	// Elemento de almacenamiento. 
	private static ObjectContainer db;
	
	/**
	 * Constructor por defecto de uso interno que solo se ejecutará una vez
	 */
	
	private MundosDAO() {
		
		db = Conexion.getDB();
		
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
