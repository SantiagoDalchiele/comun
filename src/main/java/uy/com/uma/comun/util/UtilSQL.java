package uy.com.uma.comun.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 * Funciones utiles de manejo de la librería java.sql
 *
 * Creado Aug 10, 2007
 * @author Santiago Dalchiele
 */
public abstract class UtilSQL {

	/**
	 * Retorna TRUE si el tipo SQL es numerico 
	 */
	public static boolean esNumerico (int tipoSQL) {
	    
	    switch (tipoSQL) {
	    	case Types.BIGINT :
	    	case Types.DECIMAL :
	    	case Types.DOUBLE :
	    	case Types.FLOAT :
	    	case Types.INTEGER :
	    	case Types.NUMERIC :
	    	case Types.REAL :
	    	case Types.SMALLINT :
	    	case Types.TINYINT :
	    	    return true;
	    }
	    return false;
	}

	/**
	 * Retorna TRUE si el tipo SQL es real 
	 */
	public static boolean esReal (int tipoSQL) {        
	    switch (tipoSQL) {
	    	case Types.DECIMAL :
	    	case Types.DOUBLE :
	    	case Types.FLOAT :
	    	case Types.REAL :
	    	    return true;
	    }
	    return false;
	}

	/**
	 * Retorna TRUE si el tipo SQL es entero 
	 */
	public static boolean esEntero (int tipoSQL) {
	    
	    switch (tipoSQL) {
	    	case Types.BIGINT :
	    	case Types.INTEGER :
	    	case Types.SMALLINT :
	    	case Types.TINYINT :
	    	    return true;
	    }
	    return false;
	}

	/**
	 * Retorna TRUE si el tipo SQL es caracter 
	 */
	public static boolean esCaracter (int tipoSQL) {
	    
	    switch (tipoSQL) {
	    	case Types.CHAR :
	    	case Types.LONGVARCHAR :
	    	case Types.VARCHAR :
	    	    return true;
	    }
	    return false;
	}

	/**
	 * Retorna TRUE si el tipo SQL es booleano 
	 */
	public static boolean esBooleano (int tipoSQL) {
	    
	    switch (tipoSQL) {
	    	case Types.BIT :
	    	case Types.BOOLEAN :
	    	    return true;
	    }
	    return false;
	}

	/**
	 * Retorna TRUE si el tipo SQL es fecha 
	 */
	public static boolean esFecha (int tipoSQL) {
	    
	    switch (tipoSQL) {
	    	case Types.DATE :
	    	case Types.TIME :
	    	case Types.TIMESTAMP :
	    	    return true;
	    }
	    return false;
	}

	/**
	 * Cierra una conexion sin tirar excepcion
	 */
	public static void closeConnection (Connection c) {
		try { 
			if (c != null)
				c.close(); 
		} catch (SQLException e) {		
		}
	}

	/**
	 * Cierra un statment sin tirar excepcion
	 */
	public static void closeStatment (Statement s) {
		try { 
			if (s != null)
				s.close(); 
		} catch (SQLException e) {		
		}
	}

	/**
	 * Cierra un ResultSet sin tirar excepcion
	 */
	public static void closeResultSet (ResultSet rs) {
		try { 
			if (rs != null)
				rs.close(); 
		} catch (SQLException e) {		
		}
	}

	/**
	 * Cierra sin tirar excepcion
	 */
	public static void close (Connection c, Statement s, ResultSet rs) {
		closeResultSet (rs);
		closeStatment (s);
		closeConnection (c);
	}

	/**
	 * Cierra sin tirar excepcion
	 */
	public static void close (Connection c, Statement s) {
		close (c, s, null);
	}

}
