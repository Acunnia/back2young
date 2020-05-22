package aiss.api.model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
	
	private String id;
	private String name;
	private List<Contacto> lContactos;
	
	public Agenda() {
		
	}
	
	public Agenda (String id, String name, List<Contacto> lContactos) {
		this.id = id;
		this.name = name;
		this.lContactos = lContactos;
	}
	
	public Agenda (String name, List<Contacto> lContactos) {
		this.name = name;
		this.lContactos = lContactos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Contacto> getlContactos() {
		return lContactos;
	}

	public void setlContactos(List<Contacto> lContactos) {
		this.lContactos = lContactos;
	}
	
	public void addContacto(Contacto c) {	
		if(lContactos == null) {
			lContactos = new ArrayList<Contacto>();
		}
		this.lContactos.add(c);
	}
}
