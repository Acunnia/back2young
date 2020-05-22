package aiss.api.comparator;

import java.util.Comparator;

import aiss.api.model.Contacto;

public class ComparatorContactoNameReverse implements Comparator<Contacto>{

	public int compare(Contacto c1, Contacto c2) {
		return c2.getName().compareTo(c1.getName());
	}

}
