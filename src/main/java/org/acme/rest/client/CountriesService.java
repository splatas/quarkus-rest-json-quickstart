package org.acme.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Set;

//@Path("/v2")
@RegisterRestClient
public interface CountriesService {

    //@GET
    //@Path("name/{name}")
    Set<Country> getByName(@NotNull @PathParam("name") String name);
}
