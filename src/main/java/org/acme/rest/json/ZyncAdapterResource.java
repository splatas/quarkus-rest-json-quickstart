package org.acme.rest.json;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.rest.client.Client;
import org.acme.rest.client.ClientsService;
import org.acme.rest.client.CountriesService;
import org.eclipse.microprofile.rest.client.inject.RestClient;



@Path("/zync-adapter")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ZyncAdapterResource {
	
//	@Inject
//    @RestClient
//    CountriesService countriesService;
	
    @Inject
    @RestClient
    private ClientsService clientsService;
	
	private Set<ZyncAdapter> adapters = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

	public ZyncAdapterResource() {
		adapters.add(new ZyncAdapter("Juan", "Thin client"));
		adapters.add(new ZyncAdapter("Pedro", "Heavy client"));
	}

	@GET
    public Set<ZyncAdapter> list() {
		return adapters;
	}
	
	//	def get_client(clientId):
	//
	//		response = requests.get('{}/clients'.format(os.environ.get('IDP_ADMIN_REALM_URL')),
	//                verify=tls_verify(),
	//                headers={'Authorization': 'Bearer {}'.format(get_auth_token())})
	//
	//		clients = response.json()
	//		
	//		for client in clients:
	//			app.logger.info(client)
	//			if client['clientId'] == clientId:
	//				app.logger.info(client['id'])
	//				return client
	//		
	//		return None
	//
	@GET
	@Path("client/{clientId}")
    public Set<Client> getClient(@PathParam("clientId") String clientIdParam) {
		System.out.println("clientIdParam = " + clientIdParam);
		Set<Client> clients = clientsService.getById(getAuthorizationToken(), clientIdParam);
		
		for (Client client : clients) {
			System.out.println("Client => " + client);
			
			if (clientIdParam.equals(client.getId())) {
				System.out.println("Se encontró el clientId " + client.getId());
			}
		}

		return clients;
	}

	/**
	 * Autorization Token para obtener el cliente.
	 * @return
	 */
	private String getAuthorizationToken() {
		String token = null;

		String user = "tere-lucy@hotmail.com";
		String pass = "12345678";

		return token;
	}
	
	private Boolean tlsVerify() {
	    //if (os.environ.get('IDP_VERIFY_TLS') == 'false')
		
		if ("false".equals(getEnvParam("IDP_VERIFY_TLS")))
	        return Boolean.FALSE;

	    return Boolean.TRUE;
	}
	
	private String getEnvParam(String paramName) {

		Map<String, String> envParam = new HashMap<String, String>(); 
		
		// obtener las variables de entorno, por ahora las hardcodeamos
		envParam.put("IDP_VERIFY_TLS", "false");
		
		
		return envParam.get(paramName);
	}

	//@GET
    //@Path("name/{name}")
    public Response name(@NotNull @PathParam("clientId") String name) {
		
		System.out.println("ZyncAdapterResource.name-> name="+  name);
		return Response
				.status(200)
				.entity("getClient is called, clientId=" + name).build();
    }
	
	//	@app.route('/.well-known/openid-configuration', methods=['GET'])
	//	def get_openid_configuration():
	//	    response = requests.get('{}/.well-known/openid-configuration'.format(
	//	        os.environ.get('IDP_REALM_URL')), verify=tls_verify())
	//
	//	    return response.json()
//	@GET
//	@Path(".well-known/openid-configuration")
//    public ZyncAdapter getOpenidConfiguration() {
//		
//		System.out.println("getOpenidConfiguration->");
//
//		ZyncAdapter response = null;
//		
//		return response;
//	}
	
	//	@app.route('/clients/<clientId>', methods=['PUT'])
	//	def create_or_update(clientId):
	//	    data = request.json
	//
	//	    new_or_updated_client = {
	//	        'clientId': data['client_id'],
	//	        'secret': data['client_secret'],
	//	        'enabled': True,
	//	        'publicClient': False
	//	    }
	//
	//	    client = get_client(clientId)
	//
	//	    if client is not None:
	//	        requests.put('{}/clients/{}'.format(os.environ.get('IDP_ADMIN_REALM_URL'),
	//	                                            client['id']),
	//	                                json=new_or_updated_client,
	//	                                verify=tls_verify(),
	//	                                headers={'Authorization': 'Bearer {}'.format(get_auth_token())})
	//	        
	//	    else:
	//	        requests.post('{}/clients'.format(os.environ.get('IDP_ADMIN_REALM_URL')),
	//	                      json=new_or_updated_client,
	//	                      verify=tls_verify(),
	//	                      headers={'Authorization': 'Bearer {}'.format(get_auth_token())})
	//
	//	    return jsonify(success=True)
//	@PUT
//	@Path("clients/<clientId>")
//	public ZyncAdapter createOrUpdate(@QueryParam("clientId") String clientId) {
//		System.out.println("createOrUpdate-> clientId="+  clientId);
//		
//		
//		ZyncAdapter response = null;
//		
//		return response;
//	}
	
	
	//	@app.route('/clients/<clientId>', methods=['DELETE'])
	//	def delete(clientId):
	//	    client = get_client(clientId)
	//
	//	    requests.delete('{}/clients/{}'.format(os.environ.get('IDP_ADMIN_REALM_URL'),
	//	                                           client['id']),
	//	                    verify=tls_verify(),
	//	                    headers={'Authorization': 'Bearer {}'.format(get_auth_token())})
	//
	//	    return jsonify(success=True)
//	@DELETE
//	@Path("clients/<clientId>")
//	public ZyncAdapter delete(@QueryParam("clientId") String clientId) {
//		System.out.println("delete-> clientId="+  clientId);
//
//		ZyncAdapter response = null;
//		
//		return response;
//	}

}
