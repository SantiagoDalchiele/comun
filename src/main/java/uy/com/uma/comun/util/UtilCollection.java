package uy.com.uma.comun.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * Funciones de utileria relacionado con arreglos y colecciones
 *
 * Creado Aug 10, 2007
 * @author Santiago Dalchiele
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class UtilCollection {
	

	/**
	 * Retorna una coleccion con el reverso de la coleccion ordenada
	 */		
	public static Collection reverse (SortedMap c) {
		
		List l = new ArrayList();
		
		while (! c.isEmpty()) {
			Object clave = c.lastKey();    		
			l.add (c.get (clave));
			c.remove (clave);
		}
		
		return l;
	}

	
	
	/**
	 * Retorna una coleccion con el reverso de la coleccion ordenada
	 */
	public static Collection reverse (Collection c) {    	
		SortedMap l = new TreeMap();
		long i = Long.MAX_VALUE;
		
		for (Iterator it = c.iterator(); it.hasNext(); i--)
			l.put (Long.valueOf(i), it.next());
		
		return l.values();
	}

	
	
	/**
	 * Retorna TRUE si a está incluido en b
	 */
	public static boolean incluido (Collection a, Collection b) {
		
		if ((a == null) || (b == null) || (a.size() > b.size()))
			return false;
		
		if (a.size() == 0)
			return true;
		
		Map bM = new HashMap();
		
		for (Object o : b)
			bM.put (o, o);
		
		for (Object o : a)
			if (! bM.containsKey (o))
				return false;
		
		return true;
	}

	
	
	/**
	 * Retorna TRUE si todos los elementos de a están en b
	 * y viceversa
	 */
	public static boolean equals (Collection a, Collection b) {
		if ((a == null) && (b == null))
			return true;
		else if (((a == null) && (b != null)) ||
				((a != null) && (b == null)))
			return false;
		else if ((a != null) && (b != null) && (a.size() != b.size()))
			return false;
		else
			return incluido (a, b);
	}

	
	
	/**
	 * Retorna una coleccion con todos los datos pasados como parámetro
	 */
	public static Collection getCollection (Object [] datos) {
		Collection ret = new ArrayList();
		
		if (datos != null)
			for (Object elem : datos)
				ret.add (elem);
		
		return ret;
	}

	
	
	/**
	 * Agrega al Map todos los valores de a, tanto como clave como elemento
	 */
	public static void putAll (Collection a, Map b) {
		if (a == null) return;
		
		for (Object o : a)
			b.put (o, o);
	}

	
	
	/**
	 * Retorna TRUE si todos los elementos del vector son TRUE
	 */
	public static boolean sonTodosVerdadero (boolean [] vec) {
	    for (boolean elem : vec)
	        if (! elem) 
	        	return false;
	    
	    return true;
	}
}
