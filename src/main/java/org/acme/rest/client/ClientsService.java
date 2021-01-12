package org.acme.rest.client;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v2")
@RegisterRestClient(baseUri="https://restcountries.eu/rest")
public interface ClientsService {

	@GET
    @Path("name/{clientId}")
    Set<Client> getById(@HeaderParam("Authorization") String authorization, @NotNull @PathParam("clientId") String clientId);
	
}
