package br.com.murilodev.rest;

import br.com.murilodev.dto.DadosCaixaEntregadorDTO;
import br.com.murilodev.rest.common.exception.ServiceException;
import br.com.murilodev.services.EntregadorService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("entregador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntregadorEndpoint {

    @Inject
    EntregadorService entregadorService;

    @POST
    public Response salvar(DadosCaixaEntregadorDTO entregadorDTO) throws ServiceException {
        entregadorService.salvar(entregadorDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, DadosCaixaEntregadorDTO entregadorDTO){
        return Response.ok(entregadorService.atualizar(id, entregadorDTO)).build();
    }

    @GET
    public Response listarTodos(){
        return Response.ok(entregadorService.listarTodos()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id){

        return entregadorService.remover(id);
    }

    @GET
    @Path("/logar")
    public Response logarViaApp(@QueryParam("user_id") String userId, @QueryParam("password") String password) throws ServiceException {
        return Response.ok(entregadorService.logarApp(userId, password)).build();
    }

}
