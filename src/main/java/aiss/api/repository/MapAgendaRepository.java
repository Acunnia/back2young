package aiss.api.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aiss.api.model.*;

public class MapAgendaRepository {
	
	
	/*Guardaremos los contactos y las Agendas en mapas con su ID como clave,
	 * además usaremos de partida indice 0 que iremos incrementando conforme
	 * se añadan elementos a nuestros datos
	 * */
	
	
	Map<String, Agenda> agendaMap;
	Map<String, Contacto> contactoMap;
	private static MapAgendaRepository datos = null;
	private int iC = 0;
	private int iA = 0;

	
	public static MapAgendaRepository getDatos() {
		if(datos == null) {
			datos = new MapAgendaRepository();
			datos.init();
		}
		
		return datos;
	}
	
	public void init() {
		agendaMap = new HashMap<String, Agenda>();
		contactoMap = new HashMap<String, Contacto>();
	
		Contacto c1 = new Contacto();
		c1.setName("Juan");
		c1.setSurname("García");
		c1.setEmail("c1@example.com");
		c1.setNumero("111-111-111");
		c1.setAddress("C/Example1");
		addContacto(c1);
		
		Contacto c2 = new Contacto();
		c2.setName("Manu");
		c2.setSurname("Perez");
		c2.setEmail("c2@example.com");
		c2.setNumero("222-222-222");
		c2.setAddress("C/Example2");
		addContacto(c2);
		
		Contacto c3 = new Contacto();
		c3.setName("Laura");
		c3.setSurname("Sanchez");
		c3.setEmail("c3@example.com");
		c3.setNumero("333-333-333");
		c3.setAddress("C/Example3");
		addContacto(c3);
		
		Contacto c4 = new Contacto();
		c4.setName("Miriam");
		c4.setSurname("Avila");
		c4.setEmail("c4@example.com");
		c4.setNumero("444-444-444");
		c4.setAddress("C/Example4");
		addContacto(c4);
		
		Contacto c5 = new Contacto();
		c5.setName("Pepe");
		c5.setSurname("Castellanos");
		c5.setEmail("c5@example.com");
		c5.setNumero("555-555-555");
		c4.setAddress("C/Example5");
		addContacto(c5);
	
		Agenda a1 = new Agenda();
		a1.setName("Alumnos");
		addAgenda(a1);
		
		Agenda a2 = new Agenda();
		a2.setName("Trabajo");
		addAgenda(a2);
		
		datos.addContactToAgenda("a1", "c1");
		
		
	}

	/* Agregar los ID's a los mapas y los elementos */
	
	public void addContacto(Contacto c) {
		String id = "c" + iC++;
		c.setId(id);
		contactoMap.put(id, c);
	}
	
	public void addAgenda(Agenda a) {
		String id = "a" + iA++;
		a.setId(id);
		agendaMap.put(id, a);
	}
	
	/* Obtener elementos de los mapas */
	
	public Agenda getAgenda(String agendaId) {
		return agendaMap.get(agendaId);
	}
	
	public Contacto getContacto(String contactoId) {
		return contactoMap.get(contactoId);
	}
	
	/* Introducir las contactos a las agendas */
	
	public void addContactToAgenda(String agendaId, String contactoId) {
		Agenda a = getAgenda(agendaId);
		a.addContacto(getContacto(contactoId));
		//Este add contacto es de la clase Agenda
	}
	
	/* Eliminar elementos */
	
	public void deleteContacto(String idContacto) {
		contactoMap.remove(idContacto);
		for (Map.Entry<String, Agenda> entry : agendaMap.entrySet()) {
			String id = entry.getKey();
			Agenda a = entry.getValue();
			List<Contacto> laux = a.getlContactos();
			for (int i = 0; i < laux.size(); i++) {
				if (laux.get(i).getId() == idContacto) {
					laux.remove(i);
					a.setlContactos(laux);
				}
			}
			agendaMap.put(id, a);
		}
	}
	
	public void deleteAgenda(String idAgenda) {
		agendaMap.remove(idAgenda);
	}

	public void deleteContacto(String idContacto, String idAgenda) {
		Agenda a = getAgenda(idAgenda);
		List<Contacto> laux = a.getlContactos();
		for (int i = 0; i < laux.size(); i++) {
			if (laux.get(i).getId() == idContacto) {
				laux.remove(i);
				a.setlContactos(laux);
			}
		}
		agendaMap.put(idAgenda, a);
	}
	
	/* Actualizar */
	
	public void updateContacto(String idContacto, String name, String surname, String email, String addres,
			String numero) {
		Contacto c = new Contacto(name, surname,email, addres, numero);
		c.setId(idContacto);
		contactoMap.put(idContacto, c);
		
		for (Map.Entry<String, Agenda> entry : agendaMap.entrySet()) {
			String id = entry.getKey();
			Agenda a = entry.getValue();
			List<Contacto> laux = a.getlContactos();
			for (int i = 0; i < laux.size(); i++) {
				if (laux.get(i).getId() == idContacto) {
					laux.remove(i);
					laux.add(c);
					a.setlContactos(laux);
				}
			}
			agendaMap.put(id, a);
		}
	}
	
	public void updateAgenda(String idAgenda, String nombre) {
		getAgenda(idAgenda).setName(nombre);
	}
	
	/* Obtener todo */
	
	public Collection<Agenda> getAllAgendas() {
		return agendaMap.values();
	}
	
	public Collection<Contacto> getAllContactos() {
		return contactoMap.values();
	}
	
	public List<Contacto> getAllContactos(String idAgenda) {
		return agendaMap.get(idAgenda).getlContactos();
	}
	
}

