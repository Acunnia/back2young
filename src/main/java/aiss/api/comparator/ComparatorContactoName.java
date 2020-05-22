package aiss.api.comparator;

import java.util.Comparator;

import aiss.api.model.Contacto;

public class ComparatorContactoName implements Comparator<Contacto>{

	public int compare(Contacto c1, Contacto c2) {
		return c1.getName().compareTo(c2.getName());
	}

}

