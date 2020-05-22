package aiss.api.comparator;

import java.util.Comparator;

import aiss.api.model.Agenda;

public class ComparatorAgendaNameReverse implements Comparator<Agenda>{

	public int compare(Agenda a1, Agenda a2) {
		return a2.getName().compareTo(a1.getName());
	}

	
}
