package br.com.murilodev.rest;

import br.com.murilodev.dto.DadosUsuarioLoginDTO;
import br.com.murilodev.rest.common.exception.ServiceException;
import br.com.murilodev.services.LoginService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginEndpoint {

    @Inject
    LoginService loginService;

    @POST
    public Response logarViaWeb(@QueryParam("user_id") String userId, @QueryParam("password") String password) throws ServiceException {
        DadosUsuarioLoginDTO dadosUsuarioLoginDTO = new DadosUsuarioLoginDTO();
        dadosUsuarioLoginDTO.setUsuario(userId);
        dadosUsuarioLoginDTO.setSenha(password);
        return Response.ok(loginService.loginWeb(dadosUsuarioLoginDTO)).build();
    }
}
