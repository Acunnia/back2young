package aiss.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import aiss.api.resources.AgendaResource;
import aiss.api.resources.ContactoResource;

public class AgentaApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public AgentaApplication() {

		singletons.add(ContactoResource.getDatos());
		singletons.add(AgendaResource.getDatos());
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
