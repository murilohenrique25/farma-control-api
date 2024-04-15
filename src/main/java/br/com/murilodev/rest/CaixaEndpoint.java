package br.com.murilodev.rest;

import br.com.murilodev.dto.DadosCaixaEntregadorDTO;
import br.com.murilodev.rest.common.exception.ServiceException;
import br.com.murilodev.services.CaixaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("caixa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CaixaEndpoint {

    @Inject
    CaixaService caixaService;

    @POST
    public Response salvar(DadosCaixaEntregadorDTO caixaDTO) throws ServiceException {
        caixaService.salvar(caixaDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, DadosCaixaEntregadorDTO caixaDTO){
        return caixaService.atualizar(id, caixaDTO);
    }

    @GET
    public Response listarTodos(){
        return caixaService.listarTodos();
    }

    @DELETE
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id){
        return caixaService.remover(id);
    }

}
