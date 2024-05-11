package br.com.murilodev.rest.app;

import br.com.murilodev.rest.common.authentication.Protected;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("app/v1/login")
public class LoginEndpoint {

//    @Inject
//    LoginService loginService;
//    @GET
//    @Protected
//    public Response realizarLogin(){
//        return Response.ok(loginService.realizarLogin()).build();
//    }
}
