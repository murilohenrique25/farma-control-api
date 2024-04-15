package br.com.murilodev.rest.common.dto;

import io.vertx.ext.auth.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Produces;

@RequestScoped
public class AuthenticatedUserProducer {

    @Produces
    @RequestScoped
    UserIdentity authenticatedUser;

    public void handleAuthenticationEvent(@Observes UserIdentity user){authenticatedUser = user;};
}
