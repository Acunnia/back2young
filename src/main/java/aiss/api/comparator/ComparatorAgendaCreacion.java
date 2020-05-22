package aiss.api.comparator;

import java.util.Comparator;

import aiss.api.model.Agenda;


public class ComparatorAgendaCreacion implements Comparator<Agenda>{

	public int compare(Agenda a1, Agenda a2) {
		return a1.getId().compareTo(a2.getId());
	}
}
