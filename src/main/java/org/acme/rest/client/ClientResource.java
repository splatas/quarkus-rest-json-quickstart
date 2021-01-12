package org.acme.rest.client;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/client")
public class ClientResource {
	
	@Inject
    @RestClient
    ClientsService clientService;


    //@GET
    //@Path("name/{name}")
    public Set<Client> name(@NotNull @PathParam("name") String name) {
    	
    	String authorization = null;
    	
    	System.out.println("ClientResource.name(¿?) --> " + name);
        return clientService.getById(authorization,name);
    }

}
