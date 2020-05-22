package aiss.api.resources;

import aiss.api.*;
import aiss.api.model.Contacto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.api.repository.MapAgendaRepository;
import aiss.api.comparator.*;


@Path("/contactos")
public class ContactoResource {
	
	public static ContactoResource datos = null;
	MapAgendaRepository repository;
	
	public ContactoResource() {
		repository = MapAgendaRepository.getDatos();
	}
	
	public static ContactoResource getDatos() {
		if(datos == null) {
			datos = new ContactoResource();
		}
		return datos;
	}
	
	@GET
	@Produces("application/json")
	public Collection<Contacto> getAll(@QueryParam("q") String q, @QueryParam("order")
	String order) {
		
		List<Contacto> res = new ArrayList<Contacto>();
		
		for (Contacto c: repository.getAllContactos()) {
			if( q == null 
					|| c.getName().toLowerCase().contains(q.toLowerCase())
					|| c.getSurname().toLowerCase().contains(q.toLowerCase())
					|| c.getNumero().toLowerCase().contains(q.toLowerCase())) {
				res.add(c);
			}
		}

		if(order != null) {
			if(order.equals("name")){
				Collections.sort(res, new ComparatorContactoName());
			} else if(order.equals("creacion")) {
				Collections.sort(res, new ComparatorContactoCreacion());
			}  else if(order.equals("creacionReverse")) {
				Collections.sort(res, new ComparatorContactoCreacionReverse());
			}  else if(order.equals("nameReverse")) {
				Collections.sort(res, new ComparatorContactoNameReverse());
			}
		}
		
		return res;
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Contacto get(@PathParam("id") String contactoId) {
		
		Contacto c = repository.getContacto(contactoId);
		
		if(c == null) {
			throw new NotFoundException("El contacto con id " + contactoId + ""
					+ "no se ha encontrado");
		}
		
		return c;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Contacto c) {
		
		if(c.getName() == null || "".equals(c.getName())) {
			throw new BadRequestException("El nombre no puede ser nulo");
		}
	
		repository.addContacto(c);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(c.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(c);			
		return resp.build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateSong(Contacto c) {
		
		Contacto cAux = repository.getContacto(c.getId());
		if (cAux == null) {
			throw new NotFoundException("El contacto con id " + c.getId() + " no pudo ser "
					+ "encontrado");			
		}
		
		if (c.getName()!=null)
			cAux.setName(c.getName());
		
		if (c.getSurname()!=null)
			cAux.setSurname(c.getSurname());
		
		if (c.getNumero()!=null)
			cAux.setNumero(c.getNumero());
		
		if (c.getEmail()!=null)
			cAux.setEmail(c.getEmail());
		
		if (c.getAddress()!=null)
			cAux.setAddress(c.getAddress());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String constactoId) {
		Contacto eliminado = repository.getContacto(constactoId);;
		if (eliminado == null) {
			throw new NotFoundException("El contacto con id " + constactoId + " no pudo ser "
					+ "encontrado");	
		} else {
			repository.deleteContacto(constactoId);
		}
		
		return Response.noContent().build();
	}
	
}
