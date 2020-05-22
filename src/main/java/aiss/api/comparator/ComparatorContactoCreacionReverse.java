package aiss.api.comparator;

import java.util.Comparator;

import aiss.api.model.Contacto;

public class ComparatorContactoCreacionReverse implements Comparator<Contacto>{
	
	public int compare(Contacto c1, Contacto c2) {
		return c2.getId().compareTo(c1.getId());
	}

}