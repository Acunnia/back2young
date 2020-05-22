package aiss.api.model;

public class Contacto {
	
	
	private String id;
	private String name;
	private String surname;
	private String email;
	private String address;
	private String numero;
	
	public Contacto () {
		
	}
	
	public Contacto(String id, String name, String surname, String email, String addres,
			String numero) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.numero = numero;
	}

	public Contacto(String name, String surname, String email, String addres,
			String numero) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.address = address;
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
