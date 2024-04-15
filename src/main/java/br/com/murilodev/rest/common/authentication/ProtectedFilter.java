package br.com.murilodev.rest.common.authentication;

import br.com.murilodev.entity.Entregador;
import br.com.murilodev.rest.common.dto.UserIdentity;
import br.com.murilodev.services.EntregadorService;
import jakarta.annotation.Priority;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Protected
@Priority(Priorities.AUTHENTICATION)
public class ProtectedFilter implements ContainerRequestFilter {

    @Inject
    Event<UserIdentity> authenticatedUserEvent;

    @Inject
    EntregadorService entregadorService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try{
            autenticate(requestContext);
        }catch (Exception e){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void autenticate(ContainerRequestContext requestContext) {
        String userId = requestContext.getHeaderString("user_id");
        Entregador entregador = entregadorService.logarFilter(userId);
        authenticatedUserEvent.fire(new UserIdentity(entregador.getUsuario()));
    }
}
