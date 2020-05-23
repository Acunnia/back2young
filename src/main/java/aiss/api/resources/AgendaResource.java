package aiss.api.resources;

import java.net.URI;
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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.api.comparator.ComparatorAgendaCreacion;
import aiss.api.comparator.ComparatorAgendaCreacionReverse;
import aiss.api.comparator.ComparatorAgendaName;
import aiss.api.comparator.ComparatorAgendaNameReverse;
import aiss.api.comparator.ComparatorContactoCreacion;
import aiss.api.comparator.ComparatorContactoName;
import aiss.api.model.Agenda;
import aiss.api.model.Contacto;
import aiss.api.repository.MapAgendaRepository;

@Path("/agendas")
public class AgendaResource {

	public static AgendaResource datos = null;
	MapAgendaRepository repository;
	
	public AgendaResource() {
		repository = MapAgendaRepository.getDatos();
	}
	
	public static AgendaResource getDatos() {
		if(datos == null) {
			datos = new AgendaResource();
		}
		return datos;
	}

	@GET
	@Produces("application/json")
	public Collection<Agenda> getAll(@QueryParam("order") String order,
			@QueryParam("isEmpty") Boolean isEmpty, @QueryParam("name") String name){
		
		List<Agenda> res = new ArrayList<Agenda>();
		
		for (Agenda a : repository.getAllAgendas()) {
			if(name == null || a.getName().equals(name)) {
				if(isEmpty == null || (isEmpty && (a.getlContactos() == null || a.getlContactos().size() == 0))
						|| (!isEmpty && (a.getlContactos() != null && a.getlContactos().size() > 0))) {
					res.add(a);
				}
			}
		}
		
		
		if(order != null) {
			if(order.equals("name")){
				Collections.sort(res, new ComparatorAgendaName());
			} else if(order.equals("creacion")) {
				Collections.sort(res, new ComparatorAgendaCreacion());
			} else if(order.equals("creacionReverse")) {
				Collections.sort(res, new ComparatorAgendaCreacionReverse());
			} else if(order.equals("nameReverse")) {
				Collections.sort(res, new ComparatorAgendaNameReverse());
			}
		}
		
		return res;
	
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addAgenda(@Context UriInfo uriInfo, Agenda a) {
		if (a.getName() == null || "".equals(a.getName()))
			throw new BadRequestException("El nombre de la agenda no puede ser nulo");
		
		if (a.getlContactos()!=null)
			throw new BadRequestException("La lista de contactos inicial debe estar vacía");

		repository.addAgenda(a);;

		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(a.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(a);			
		return resp.build();
	}

	@PUT
	@Consumes("application/json")
	public Response updateAgenda(Agenda a) {
		Agenda agendaAux = repository.getAgenda(a.getId());
		if(agendaAux == null) {
			throw new NotFoundException("La agenda con id " + a.getId() + " no se ha encontrado");
		}
		
		if(a.getName() != null) {
			agendaAux.setName(a.getName());
		}
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeAgenda(@PathParam("id") String id) {
		Agenda agendaEliminada = repository.getAgenda(id);
		
		if(agendaEliminada == null) {
			throw new NotFoundException("La agenda con id " + agendaEliminada.getId() + " no se ha encontrado");
		} else {
			repository.deleteAgenda(id);
		}
		
		return Response.noContent().build();
	}
	
	@GET
	@Path("/{id}")
	public Agenda getAgenda(@PathParam("id") String agendaId) {
		Agenda a = repository.getAgenda(agendaId);;
		
		if(a == null) {
			throw new NotFoundException("La agenda con id " + agendaId + ""
					+ "no se ha encontrado");
		}
		
		return a;
	}
	
	@POST
	@Path("/{agendaId}/{contactoId}")
	@Produces("application/json")
	public Response addContacto(@Context UriInfo uriInfo, @PathParam("agendaId") 
	String agendaId, @PathParam("contactoId") String contactoId) {
		
		Agenda a = repository.getAgenda(agendaId);
		Contacto c = repository.getContacto(contactoId);
		
		if(a == null) {
			throw new NotFoundException("La agenda con id " + a.getId() + " no se ha encontrado");
		}
		
		if(c == null) {
			throw new NotFoundException("El contacto con id " + c.getId() + " no se ha encontrado");
		}
		
		if(a.getlContactos().contains(c)) {
			throw new BadRequestException("El contacto con id " + c.getId() + " ya estaba agregado");
		}
		
		repository.addContactToAgenda(agendaId, contactoId);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(agendaId);
		ResponseBuilder resp = Response.created(uri);
		resp.entity(a);			
		return resp.build();
	}
	
	@DELETE
	@Path("/{agendaId}/{contactoId}")
	public Response deleteContacto(@PathParam("agendaId") String agendaId,
			@PathParam("contactoId") String contactoId) {
		Agenda a = repository.getAgenda(agendaId);
		Contacto c = repository.getContacto(contactoId);
		
		if(a == null) {
			throw new NotFoundException("La agenda con id " + a.getId() + " no se ha encontrado");
		}
		
		if(c == null) {
			throw new NotFoundException("El contacto con id " + c.getId() + " no se ha encontrado");
		}
		
		repository.deleteContacto(contactoId, agendaId);
		
		return Response.noContent().build();
	}
	
	
}
