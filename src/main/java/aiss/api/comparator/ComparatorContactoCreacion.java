package aiss.api.comparator;

import java.util.Comparator;

import aiss.api.model.Contacto;

public class ComparatorContactoCreacion implements Comparator<Contacto>{
	
	public int compare(Contacto c1, Contacto c2) {
		return c1.getId().compareTo(c2.getId());
	}

}
